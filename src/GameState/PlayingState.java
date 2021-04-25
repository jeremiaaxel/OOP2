package GameState;

import Entities.*;
import Game.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class PlayingState extends GameState{

    private Map map = new Map();
    private Player player;
    private WildEngimon wildEngimon;
    private boolean newgame;

    private final int panelSize = 300;
    private final long spawnDelay = 8000;
    private long startTime;

    public PlayingState(GameStateManager gameStateManager, boolean newgame){
        this.gameStateManager = gameStateManager;
        this.newgame = newgame;
        this.startTime = System.nanoTime();
        init();
    }

    private void print(Object obj, Object end) {
        System.out.print(obj);
        System.out.print(end);
    }
    private void print(Object obj) {
        System.out.println(obj);
    }

    public void init(){
        if (newgame){
            try {
                String map_text = map.parse("data/map.txt");
                this.map = new Map(16, 20, map_text);
                this.map.setTileSize((GamePanel.WIDTH*GamePanel.SCALE - panelSize)/ (map.getNumberOfColumn()-2));
                player = new Player("New Player", map); // NANTI MINTA NAMA DULU
                wildEngimon = new WildEngimon(10,map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            load();
        }
    }

    public void load(){
        print("Belum ada");
    }

    public void update(){
        player.update();
        wildEngimon.update(player);
        if (wildEngimon.getNumberOfWildEngimon() == 0){
            System.out.println("GAME OVER!!!");
            gameStateManager.setGameStates(gameStateManager.MENUSTATE);
        }
        spawnRandowmWildEngimon();
    }

    public void keyPressed(int key){
        switch (key) {
            case KeyEvent.VK_SPACE:
//                doBattle();
                break;
            case KeyEvent.VK_UP:
                this.player.setUp(true);
                break;
            case KeyEvent.VK_DOWN:
                this.player.setDown(true);
                break;
            case KeyEvent.VK_LEFT:
                this.player.setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                this.player.setRight(true);
                break;
            case KeyEvent.VK_S:
                savegame();
                break;
            case KeyEvent.VK_1:
//                commandPlayer1
                break;
            case KeyEvent.VK_2:
//                commandPlayer12
                break;
            case KeyEvent.VK_3:
                //switch active engimon
                int test = 2;
                player.switchActiveEngimon(2);
                player.getActiveEngimon().displayEngimonInfo();
                break;
            case KeyEvent.VK_4:
//                commandPlayer4
                break;
            case KeyEvent.VK_5:
//                commandPlayer5
                break;
            case KeyEvent.VK_ESCAPE:
//                commandQuit
            default:
                break;
        }
    }

    public void spawnRandowmWildEngimon(){
        long elapsed = (System.nanoTime() - startTime)/ 1000000;
        if (elapsed > spawnDelay) {
            Random rand = new Random(new Date().getTime());
            int random_speciesid = rand.nextInt(100) % 5;
            Engimon new_eng;
            switch (random_speciesid)
            {
                case 0:
                    new_eng = new Aggron("Wild Aggron",new Parent(),map.getEmptyTileInRowNWithType(3,'g'),map);
                    break;
                case 1:
                    new_eng = new Ampharos("Wild Ampharos",new Parent(),map.getEmptyTileInRowNWithType(5,'g'),map);
                    break;
                case 2:
                    new_eng = new Araquanid("Wild Araquanid",new Parent(),map.getEmptyTileInRowNWithType(7,'s'),map);
                    break;
                case 3:
                    new_eng = new Blaziken("Wild Blaziken",new Parent(),map.getEmptyTileInRowNWithType(0,'m'),map);
                    break;
                default:
                    new_eng = new Eiscue("Wild Eiscue",new Parent(),map.getEmptyTileInRowNWithType(10,'t'),map);
            };
            if (new_eng.isTileTypeCompatible(new_eng.getCurrentPosition().getType()))
            {
                wildEngimon.addWildEngimon(new_eng);
            }
            startTime = System.nanoTime();
        }
    }

    public void keyReleased(int key){
        switch (key) {
            case KeyEvent.VK_UP:
                this.player.setUp(false);
                break;
            case KeyEvent.VK_DOWN:
                this.player.setDown(false);
                break;
            case KeyEvent.VK_LEFT:
                this.player.setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                this.player.setRight(false);
                break;
            default:
                break;
        }
    }

    public void draw(Graphics2D g){
        // clear screen
        g.setColor(Color.BLACK);
        int panelWidth = GamePanel.WIDTH*GamePanel.SCALE - map.getTilesize()* (map.getNumberOfColumn()-2);
        int panelHeight =(map.getNumberOfRow()-2)*map.getTilesize();
        g.fillRect((map.getNumberOfColumn()-2) * map.getTilesize(),0,panelWidth, panelHeight);

        // draw map
        map.drawMap(g);
        player.draw(g);
        wildEngimon.draw(g);
    }

    public void savegame() {
        print("Nama file penyimpanan : ", "");
        Scanner scanner = new Scanner(System.in);
        String filepath = scanner.nextLine();
        String dataPath = "data/";

        try {
            File saveFile = new File(dataPath+filepath+".txt");
            boolean isExist = saveFile.createNewFile();
            FileOutputStream outFile = new FileOutputStream(saveFile, false);

            // nyimpen data-data yang perlu.
            /* DATA-DATA YANG PERLU DISAVE?
            https://www.tutorialspoint.com/java/java_serialization.htm
            * */

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
