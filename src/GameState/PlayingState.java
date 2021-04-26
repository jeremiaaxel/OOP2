package GameState;

import Entities.*;
import Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class PlayingState extends GameState {

    private Map map = new Map();
    private Player player;
    private WildEngimon wildEngimon;
    private Battle battle;
    private boolean newgame;

    private final int panelSize = 300;
    private final long spawnDelay = 8000;
    private long startTime;
    private int panelstate;

    private int intInput;
    private String stringInput;


    public PlayingState(GameStateManager gameStateManager, boolean newgame){
        this.gameStateManager = gameStateManager;
        this.newgame = newgame;
        this.startTime = System.nanoTime();
    }


    private void print(Object obj, Object end) {
        System.out.print(obj);
        System.out.print(end);
    }
    private void print(Object obj) {
        System.out.println(obj);
    }

    @Override
    public synchronized void init(){

        try {
            String map_text = map.parse("data/map.txt");
            this.map = new Map(15, 19, map_text);
            this.map.setTileSize((GamePanel.WIDTH*GamePanel.SCALE - panelSize)/ (map.getNumberOfColumn()-2));
            System.out.println("size" + this.map.getTilesize());

            if (newgame) {
                this.player = new Player("New Player", map);
                this.wildEngimon = new WildEngimon(10,map);
                playerInit();
            } else {
                playerLoad();

                // load resources
                for (int i = 0; i < wildEngimon.getNumberOfWildEngimon(); i++) {
                    wildEngimon.getNthEngimon(i).loadImg();
                }
                player.getActiveEngimon().loadImg();
            }

            player.loadSprites();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

     public synchronized void playerInit(){
        Engimon eng1 = new Blaziken("my blaziken", new Parent(), player.getPlayerPosition(),map);
        Engimon eng2 = new Ampharos("my ampharos", new Parent(), player.getPlayerPosition(),map);
        Engimon eng3 = new Aggron("my aggron", new Parent(), player.getPlayerPosition(),map);
        Engimon eng4 = new Araquanid("my araquanid", new Parent(),player.getPlayerPosition(),map);
        Engimon eng5 = new Eiscue("my eiscue", new Parent(),player.getPlayerPosition(),map);
        this.player.addEngimon(eng1);
        this.player.addEngimon(eng2);
        this.player.addEngimon(eng3);
        this.player.addEngimon(eng4);
        this.player.addEngimon(eng5);
        this.player.switchActiveEngimon(0);
    }

    public synchronized void playerLoad(){
        String dataPath = "data";
        print("Nama file penyimpanan : ", "");
        Scanner scanner = new Scanner(System.in);
//        String dirpath = scanner.nextLine();
        String dirpath = "test1";
        dataPath = dataPath.concat("/"+dirpath);
        try {
            // Make player load
            FileInputStream fileIn = new FileInputStream(dataPath.concat("/" + "player.txt"));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.player = (Player) in.readObject();
            in.close();
            fileIn.close();

            print("Player done");

            // Make wild engimon load
            fileIn = new FileInputStream(dataPath.concat("/" + "wildengimons.txt"));
            in = new ObjectInputStream(fileIn);
            wildEngimon = (WildEngimon) in.readObject();
            in.close();
            fileIn.close();
            print("Wild Engimon done");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void update(){
        while (player == null) {
            System.out.println("Something went wrong player");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (wildEngimon == null) {
            System.out.println("Something went wrong wild engimon");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        player.update(wildEngimon);
        wildEngimon.update(player);
        if (wildEngimon.getNumberOfWildEngimon() == 0){
            System.out.println("GAME OVER!!!");
            gameStateManager.setGameStates(gameStateManager.MENUSTATE);
        }
        spawnRandowmWildEngimon();
//        map.displayMap();
    }

    public void viewPanel(Graphics2D g){
        int panelWidth = GamePanel.WIDTH*GamePanel.SCALE - map.getTilesize()* (map.getNumberOfColumn()-2);
        int panelHeight =(map.getNumberOfRow()-2)*map.getTilesize();

        Graphics2D g2 = g;
        g.setColor(new Color(102,51,0));
        g.fillRect((map.getNumberOfColumn()-2)*map.getTilesize(),0,panelWidth,panelHeight);

        switch (panelstate){
            case 1 :
                player.draw_Skill(g);
                break;
            case 2 :
                player.draw_Player(g);
                break;
            case 3 :
                wildEngimon.draw_WildEngimon(g);
                break;
            case 4 :
                player.draw_listEngimon(g);
                break;
            case 5 :
                player.getEngimon(intInput).draw_infoengimon(g);
                break;
            case 6 :
                battle.draw_Battle(g, panelWidth, panelHeight);
                break;
            default:
                break;
        }
    }

    public synchronized void keyPressed(int key){
        switch (key) {
            case KeyEvent.VK_SPACE:
                battle = new Battle(player.getActiveEngimon(), player.getActiveEngimon(), map, player, true); //this.battle.setBattle(true);
                battle();
                panelstate = 6;
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
                commandQuit();
                break;
            case KeyEvent.VK_1:
                panelstate = 1;
                break;
            case KeyEvent.VK_2:
                panelstate = 2;
                break;
            case KeyEvent.VK_3:
                panelstate =3;
                break;
            case KeyEvent.VK_4:
                panelstate = 4;
                break;
            case KeyEvent.VK_5:
                //switch active engimon
                panelstate = 2;
                intInput = getIntInput(player.getListIdEng(),
                        "Masukkan ID engimon","Switch Active Engimon");
                player.switchActiveEngimon(intInput);
                break;
            case KeyEvent.VK_6:
                // rename engimon
                intInput = getIntInput(player.getListIdEng(),
                        "Masukkan ID engimon","Rename Engimon");
                String new_name = getStrInput("Masukkan nama baru untuk engimon mu",
                        "Rename Engimon");
                player.getEngimon(intInput).setEngimonName(new_name);
                break;
            case KeyEvent.VK_7:
                // melepas skill/engimon
                intInput = getIntInput(new Object[]{1,2},
                        "Pilih jenis item yang akan dilepas\n1 : Skill\n2 : Engimon)",
                        "Show Engimon Details");
                if (intInput == 1){
                    // tampilin option list id skill dulu
//                    player.throwSomeSkillItems(player.getSkillItem(),id);
                } else{
                    intInput = getIntInput(player.getListIdEng(),
                            "Pilih ID Engimon yang akan dilepas\n1 : Skill\n2 : Engimon)",
                            "Show Engimon Details");
                    player.freeTheEngimon(intInput);
                }
                break;
            case KeyEvent.VK_8:
                // detail suatu engimon
                intInput = getIntInput(player.getListIdEng(),
                        "Masukkan ID engimon","Show Engimon Details");
                panelstate = 5;
                break;
            case KeyEvent.VK_9:
                //Breeding
                int id1 = getIntInput(player.getListIdEng(),"Masukkan ID engimon Pertama yang ingin di breed","Breeding");
                int id2 = getIntInput(player.getListIdEng(),"Masukkan ID engimon Kedua yang ingin di breed","Breeding");
                this.player.getEngimon(id1).setLevel(5);
                this.player.getEngimon(id2).setLevel(5);
                player.breed(id1, id2, this.player);
                break;

            case KeyEvent.VK_ESCAPE:
                commandQuit();
            default:
                break;
        }
    }

    public int getIntInput(Object[] possibilities, String message, String title){
        player.setIdle(true);
        JFrame f = new JFrame();
        int s = (int) JOptionPane.showInputDialog(
                f,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "");
        return s;
    }

    public String getStrInput(String message, String title){
        player.setIdle(true);
        JFrame f = new JFrame();
        String s = (String) JOptionPane.showInputDialog(
                f,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        return s;
    }

    public synchronized void commandQuit() {
        this.gameStateManager.setGameStates(GameStateManager.MENUSTATE);
    }

    public synchronized void spawnRandowmWildEngimon(){
        long elapsed = (System.nanoTime() - startTime)/ 1000000;
        if (elapsed > spawnDelay && !wildEngimon.isFull()) {
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
                    new_eng = new Blaziken("Wild Blaziken",new Parent(),map.getEmptyTileInRowNWithType(1,'m'),map);
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

    public synchronized void keyReleased(int key){
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
            case KeyEvent.VK_SPACE:
                this.battle.setBattle(false);
            default:
                break;
        }
    }

    public synchronized void draw(Graphics2D g){
        if (player == null) {
            print("Something went wrong [draw]");
        } else {
            // clear screen
//            g.setColor(Color.BLACK);
//            int panelWidth = GamePanel.WIDTH*GamePanel.SCALE - map.getTilesize()* (map.getNumberOfColumn()-2);
//            int panelHeight =(map.getNumberOfRow()-2)*map.getTilesize();
//            g.fillRect((map.getNumberOfColumn()-2) * map.getTilesize(),0,panelWidth, panelHeight);

            // draw map
            map.drawMap(g);
            wildEngimon.draw(g);
            player.draw(g);
            viewPanel(g);
        }
    }

    public synchronized  void battle(){
        try {
            Engimon enemy = player.getEnemeyArround(wildEngimon);
            boolean state = this.battle.getbattlestate();
            if (enemy != null){
                this.battle = new Battle(this.player.getActiveEngimon(), enemy, this.map, this.player, state);
                if (battle.getbattlestate() == true) {
                    //battle.displatBattleInfo(this.player,this.wildEngimon);
                    battle.doBattle(this.map, this.player, this.wildEngimon);
                    System.out.println("Battle succeed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void savegame() {
        String dataPath = "data";
        print("Nama file penyimpanan : ", "");
        Scanner scanner = new Scanner(System.in);
//        String dirpath = scanner.nextLine();
        String dirpath = "test1";
        dataPath = dataPath.concat('/' + dirpath);
        print(dataPath);

        try {
            // Make folder
            File folderOut = new File(dataPath);
            boolean dirCreated = folderOut.mkdir();

            // Make player save
            FileOutputStream fileOut = new FileOutputStream(dataPath.concat("/" + "player.txt"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.close();
            fileOut.close();
            print("Player done");

            // Make wild engimon save
            fileOut = new FileOutputStream(dataPath.concat("/" + "wildengimons.txt"));
            out = new ObjectOutputStream(fileOut);
            out.writeObject(wildEngimon);
            out.close();
            fileOut.close();
            print("Wild Engimons done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
