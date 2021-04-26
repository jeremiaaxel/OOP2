package Entities;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class WildEngimon implements Serializable {
    private ArrayList<Engimon> wildEngimonList;
    private int capacity;
    private Map map;

    private long delayToMove;
    private long moveStartTime;
    private long delayToLevelUp;
    private long levelUpStartTime;

    public WildEngimon(Map map){
        wildEngimonList = new ArrayList<>();
        capacity = 12;
        this.map = map;
        delayToMove = 100;
        delayToLevelUp = 5000;
        init();
    }
    public WildEngimon(int capacity, Map map){
        wildEngimonList = new ArrayList<>();
        this.capacity = capacity;
        this.map = map;
        delayToMove = 800;
        delayToLevelUp = 10000;
        init();
    }

    public WildEngimon(WildEngimon wildEngimonin, Map map) {
        wildEngimonList = new ArrayList<>();
        this.capacity = wildEngimonin.capacity;
        this.map = map;
        delayToMove = 800;
        delayToLevelUp = 10000;
        init(wildEngimonin.wildEngimonList);
    }

    public void init(){
        Engimon wild1 = new Blaziken("wild blaziken", new Parent(), map.getTile(3,13),map);
        Engimon wild2 = new Ampharos("wild ampharos", new Parent(), map.getTile(1,3),map);
        Engimon wild3 = new Aggron("wild aggron", new Parent(), map.getTile(5,5),map);
        Engimon wild4 = new Araquanid("wild araquanid", new Parent(),map.getTile(12,2),map);
        Engimon wild5 = new Eiscue("wild eiscue", new Parent(),map.getTile(10,12),map);
        addWildEngimon(wild1);
        addWildEngimon(wild2);
        addWildEngimon(wild3);
        addWildEngimon(wild4);
        addWildEngimon(wild5);
        moveStartTime = System.nanoTime();
        levelUpStartTime = System.nanoTime();
    }

    public void init(List<Engimon> engimons) {
        for (int i = 0; i < engimons.size(); i++) {
            this.addWildEngimon(engimons.get(i));
        }
        moveStartTime = System.nanoTime();
        levelUpStartTime = System.nanoTime();
    }

    public int getNumberOfWildEngimon(){
        return wildEngimonList.size();
    }

    public void addWildEngimon(Engimon other){
        if (getNumberOfWildEngimon() < capacity){
            other.setWild(true);
            wildEngimonList.add(other);
        }
    }
    public void deleteNthWildEngimon(int n) throws Exception{
        if (n < 0 || n >= getNumberOfWildEngimon()){
            throw new IndexOutOfBoundsException();
        }
        this.getNthEngimon(n).getCurrentPosition().setOccupier(' ');
        wildEngimonList.remove(n);
    }
    public void randomMove(){
        if (getNumberOfWildEngimon() == 0){ return; }
        long elapsed = (System.nanoTime() - moveStartTime)/ 1000000;
        if (elapsed > delayToMove){
            Random rand = new Random(new Date().getTime());
            int engimon_id = rand.nextInt(100) % this.getNumberOfWildEngimon(), i = 0, directionId;

            directionId = rand.nextInt(100) % 4;

            switch (directionId){
                case 0:
                    this.getNthEngimon(engimon_id).setUp(true);
                    break;
                case 1:
                    this.getNthEngimon(engimon_id).setRight(true);
                    break;
                case 2:
                    this.getNthEngimon(engimon_id).setDown(true);
                    break;
                case 3:
                    this.getNthEngimon(engimon_id).setLeft(true);
                    break;
                default:
                    break;
            }
            this.getNthEngimon(engimon_id).move();

            switch (directionId){
                case 0:
                    this.getNthEngimon(engimon_id).setUp(false);
                    break;
                case 1:
                    this.getNthEngimon(engimon_id).setRight(false);
                    break;
                case 2:
                    this.getNthEngimon(engimon_id).setDown(false);
                    break;
                case 3:
                    this.getNthEngimon(engimon_id).setLeft(false);
                    break;
                default:
                    break;
            }

            moveStartTime = System.nanoTime();
        }
    }

    public Engimon getNthEngimon(int n){
        if (n < 0 || n >= getNumberOfWildEngimon()){
            throw new IndexOutOfBoundsException();
        }
        return wildEngimonList.get(n);
    }

    public boolean isFull(){
        return capacity == getNumberOfWildEngimon();
    }

    public void update(Player player){
        giveExp(100);
        randomMove();
        ArrayList<Engimon> engToDelete = new ArrayList<>();
        for (Engimon eng : wildEngimonList){
            eng.update();
            if (eng.getEngimonLevel() > player.getActiveEngimon().getEngimonLevel()){
                eng.setWidth(map.getTilesize());
                eng.setHeight(map.getTilesize());
            }
            if (eng.isdead){
                engToDelete.add(eng);
            }
        }
        for (Engimon eng : engToDelete){
            map.setTileOcc(eng.y_map,eng.x_map, Map.NO_OCCUPIER);
            wildEngimonList.remove(eng);
            eng = null;
        }
    }

    public void giveExp(int N){
        long elapsed = (System.nanoTime() - levelUpStartTime)/ 1000000;
        if (elapsed > delayToLevelUp){
            for (Engimon e:
                 wildEngimonList) {
                e.addExp(N);
            }
            levelUpStartTime = System.nanoTime();
        }
    }

    public void draw(Graphics2D g){
        for (Engimon eng : wildEngimonList){
            eng.draw(g);
        }
    }

    public int getIdOfEngimon(Engimon eng){
        return wildEngimonList.indexOf(eng);
    }

    public Engimon engimonOnTheTile(Tile posisyen){
        for (Engimon e : wildEngimonList){
            if (e.getCurrentPosition().isSame(posisyen)){
                return e;
            }
        }
        return null;
    }

    public ArrayList<Engimon> getWildEngimonList() {
        return wildEngimonList;
    }

    public void draw_WildEngimon(Graphics2D g){
        g.setColor(new Color(255,217,179));
        g.setFont(new Font("MicrosoftYaHei",Font.BOLD,35));
        g.drawString("Status",map.getNumberOfColumn()*map.getTilesize()+20,50);

        g.setColor(new Color(255,217,179));
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));

        g.drawString("* Daftar Wild Engimon :",map.getNumberOfColumn()*map.getTilesize()-20,100);
        ArrayList<Engimon> b = this.getWildEngimonList();
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
        for (int i =0; i < this.getWildEngimonList().size(); i++){
            g.drawString("- "+b.get(i).getEngimonName(),map.getNumberOfColumn() * map.getTilesize()+20,125+(50*i));
            g.drawString(String.format("Level : %d | Exp : %d",b.get(i).getEngimonLevel(), b.get(i).getEngimonExp()),map.getNumberOfColumn()*map.getTilesize()+20,150+(50*i));
        }

    }
}
