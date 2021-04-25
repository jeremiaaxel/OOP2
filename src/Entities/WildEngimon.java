package Entities;

import java.awt.*;
import java.util.*;

public class WildEngimon{
    private ArrayList<Engimon> wildEngimonList;
    private int capacity;
    private Map map;

    private long delayToMove;
    private long moveStartTime;
    private long delayToLevelUp;
    private long levelUpStartTime;

    public WildEngimon(Map map){
        capacity = 12;
        wildEngimonList = new ArrayList<>();
        this.map = map;
        delayToMove = 800;
        delayToLevelUp = 5000;
        init();
    }

    public WildEngimon(int capacity, Map map){
        this.capacity = capacity;
        wildEngimonList = new ArrayList<>();
        this.map = map;
        delayToMove = 800;
        delayToLevelUp = 10000;
        init();
    }

    public void init(){
        Engimon wild1 = new Blaziken("wild blaziken", new Parent(), map.getTile(3,15));
        Engimon wild2 = new Ampharos("wild ampharos", new Parent(), map.getTile(1,3));
        Engimon wild3 = new Aggron("wild aggron", new Parent(), map.getTile(5,5));
        Engimon wild4 = new Araquanid("wild araquanid", new Parent(),map.getTile(12,2));
        Engimon wild5 = new Eiscue("wild eiscue", new Parent(),map.getTile(10,14));
        wild1.setMap(map);
        wild2.setMap(map);
        wild3.setMap(map);
        wild4.setMap(map);
        wild5.setMap(map);
        addWildEngimon(wild1);
        addWildEngimon(wild2);
        addWildEngimon(wild3);
        addWildEngimon(wild4);
        addWildEngimon(wild5);
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
                    System.out.println("right");
                    this.getNthEngimon(engimon_id).setRight(true);
                    break;
                case 2:
                    this.getNthEngimon(engimon_id).setDown(true);
                    break;
                case 3:
                    this.getNthEngimon(engimon_id).setLeft(true);
                    break;
                default:
//                    System.out.println("random move default");
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

    public void update(){
        giveExp(100);
        randomMove();
        ArrayList<Engimon> engToDelete = new ArrayList<>();
        for (Engimon eng : wildEngimonList){
            eng.update();

            if (eng.isdead){
                engToDelete.add(eng);
            }
        }
        for (Engimon eng : engToDelete){
            wildEngimonList.remove(eng);
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
}
