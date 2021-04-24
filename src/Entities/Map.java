package Entities;

import Game.GamePanel;
import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Map {
    private Tile[][] map;
    private int row_size;
    private int col_size;
    private int tilesize;

    public static BufferedImage mountain_tile;
    public static BufferedImage grassland_tile;
    public static BufferedImage tundra_tile;
    public static BufferedImage sea_tile;

    public Map() {
        //
        this.row_size = 0;
        this.col_size = 0;

        loadTileImg();
    }

    public Map(int banyak_baris, int banyak_kolom, String map_entry) {
        //
        char type;
        this.row_size = banyak_baris;
        this.col_size = banyak_kolom;
        this.tilesize = (GamePanel.WIDTH*GamePanel.SCALE - 200)/(col_size);
        this.map = new Tile[row_size][col_size];
        loadTileImg();
        
        for (int i = 0; i < row_size; i++) {
            for (int j = 0; j < col_size; j++) {
                type = map_entry.charAt((i * (col_size)) + j);

                if (type != '#') {
                    if (type == 'm') {
                        map[i][j] = new Tile(i, j, type, ' ', mountain_tile);
                    } else if (type == 's') {
                        map[i][j] = new Tile(i, j, type, ' ', sea_tile);
                    } else if (type == 'g') {
                        map[i][j] = new Tile(i, j, type, ' ', grassland_tile);
                    } else if (type == 't') {
                        map[i][j] = new Tile(i, j, type, ' ', tundra_tile);
                    }
                }
            }
        }
    }

    public void loadTileImg(){
        try{
            mountain_tile = ImageIO.read(new FileInputStream("resources/mountains.jpg"));
            grassland_tile = ImageIO.read(new FileInputStream("resources/grassland.jpg"));
            tundra_tile = ImageIO.read(new FileInputStream("resources/tundra.jpg"));
            sea_tile = ImageIO.read(new FileInputStream("resources/sea.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void drawMap(Graphics2D g){
        for (int i = 1; i < row_size-1; i++){
            for (int j = 1; j < col_size-1; j++){
                g.drawImage(
                        map[i][j].getImage(),
                        (j-1) * tilesize,
                        (i-1) * tilesize,
                        tilesize,
                        tilesize,
                        null
                );
            }
        }
    }

    public int getNumberOfRow() {
        return this.row_size;
    }

    public int getNumberOfColumn() {
        return this.col_size;
    }

    public Tile getTile(int nobaris, int nokolom) {
        //
        return this.map[nobaris][nokolom];
    }

    public Tile[] getSurroundingTile(Tile tile) {
        //
        Tile surrTile[] = new Tile[4];
        surrTile[0].set(getTileOnTop(tile));
        surrTile[1].set(getTileOnRight(tile));
        surrTile[2].set(getTileBelow(tile));
        surrTile[3].set(getTileOnleft(tile));
        return surrTile;
    }

    public Tile getTileOnTop(Tile tile) {
        //
        return this.map[tile.getOrdinat()-1][tile.getAbsis()];
    }

    public Tile getTileOnleft(Tile tile) {
        //
        return this.map[tile.getOrdinat()][tile.getAbsis()-1];
    }

    public Tile getTileOnRight(Tile tile) {
        //
        return this.map[tile.getOrdinat()][tile.getAbsis()+1];
    }

    public Tile getTileBelow(Tile tile) {
        //
        return this.map[tile.getOrdinat()+1][tile.getAbsis()];
    }

    public Tile getEmptyTileInRowNWithType(int N, char type) {
        int i = N;
        int j = 1;
        boolean found = false;
        while (!found && i < row_size-1)
        {
            j = 1;
            while (!found && j < col_size-1)
            {
                if (map[i][j].getType() == type && map[i][j].getOccupierCode() == ' ')
                {
                    found = true;
                }
                else
                {
                    j++;
                }
            }
            if (!found)
            {
                i++;
            }
        }
        return map[i][j];
    }

    public void setTileOcc(int nobaris, int nokolom, char occCode) { //Set occ di tile di titik tertent 
        //
        this.map[nobaris][nokolom].setOccupier(occCode);
    }

    public void displayMap() {
        //
        for (int i = 1; i < row_size-1; i++) {
            for (int j = 1; j < col_size-1; j++){
                if (map[i][j].getOccupierCode() == ' ') {
                    System.out.print(map[i][j].getType() + " ");
                } else {
                    System.out.print(map[i][j].getOccupierCode() + " ");
                }
            }
            System.out.println();
        }
    }


    
    public String parse(String filepath) throws IOException {
        String text = "";

        Scanner scanner = null;
        try (FileInputStream fis = new FileInputStream(filepath)) {
            scanner = new Scanner(fis);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                text = text.concat(line);
            }
            
            return text;
        } catch (IOException e) {
            throw e;
            
        } finally {
            scanner.close();
        }
    }

    public void getSurroundingTile(final Tile t, Tile[] surrTile)
    {
        if (surrTile.length < 4){
            throw new ArrayIndexOutOfBoundsException();
        }
        surrTile[0] = getTileOnTop(t);
        surrTile[1] = getTileOnRight(t);
        surrTile[2] = getTileBelow(t);
        surrTile[3] = getTileOnleft(t);
    }

    
    // Kalau mau nyoba, "javac Map.java && java Map"
    // public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);
    //     String filepath = scanner.nextLine();

    //     Map map = new Map();
    //     try {
    //         String map_text = map.parse(filepath);
    //         map = new Map(12, 14, map_text);
    //         map.displayMap();
    //     } catch (IOException e) {
    //         System.out.println(e.getMessage());
    //         System.out.println("a");
    //     } finally {
    //         scanner.close();
    //     }
        
    // }
}