package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Tile {
    private int absis;
    private int ordinat;
    private char type;
        /* g : Grassland
           m : Mountains
           t : Tundra
           s : Sea */
    private char occupierCode;

    private BufferedImage tileImg;

    public Tile() {
        //
        this.absis = -1;
        this.ordinat = -1;
        this.type = '#';
        this.occupierCode = ' ';
    }

    public Tile(int x, int y, char type, char occupierCode) {
        //
        this.absis = x;
        this.ordinat = y;
        this.type = type;
        this.occupierCode = occupierCode;
        loadTile();
    }

    public void loadTile() {
        if (type != '#'){
            String imgpath = null;
            if (type == 'm'){
                imgpath = "resources/mountains.jpg";
            } else if (type == 's'){
                imgpath = "resources/sea.png";
            } else if (type == 'g'){
                imgpath = "resources/grassland.jpg";
            } else if (type == 't'){
                imgpath = "resources/tundra.jpg";
            }
            try {
                tileImg = ImageIO.read(new FileInputStream(imgpath));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void set(Tile tile) {
        this.absis = tile.absis;
        this.ordinat = tile.ordinat;
        this.type = tile.type;
        this.occupierCode = tile.occupierCode;
    }
    
    public int getAbsis() {
        //
        return this.absis;
    }

    public int getOrdinat() {
        //
        return this.ordinat;
    }

    public char getType() {
        //
        return this.type;
    }

    public char getOccupierCode() {
        //
        return this.occupierCode;
    }

    public BufferedImage getImage() {
        return tileImg;
    }

    public void setAbsis(int x) {
        //
        this.absis = x;
    }

    public void setOrdinat(int y) {
        //
        ordinat = y;
    }

    public void setType(char newtype) {
        //
        this.type = newtype;
    }   

    public void setOccupier(char occCode) {
        //
        this.occupierCode = occCode;
    }

    public void printCoordinate() {
        //
        System.out.println("(" + this.absis + "," + this.ordinat + ")");
    }

    public Boolean isSame(Tile other){
        return absis == other.absis && ordinat == other.ordinat;
    }

    public Boolean isMoveable() {
        //
        return this.absis >= 0 && this.absis <= 14 && this.ordinat >= 0 && this.ordinat <= 12;
    }

}
