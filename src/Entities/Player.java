package Entities;

import java.util.*;
import java.util.Map;

public class Player {
    private String name;
    private Tile currentPosition = new Tile();
    private Inventory<Engimon> ownedEngimon = new Inventory<>();
    private Inventory<Skill> ownedSkill = new Inventory<>();
    private List<Integer> skillCounter = new ArrayList<>();
    private int activeEngimonId;

    private void println(String string) {
        System.out.println(string);
    }
    
    /* ************ PLAYER ************ */
    public Player(String name) {
        this.name = name;
        this.currentPosition.setAbsis(7);
        this.currentPosition.setOrdinat(5);
        this.ownedEngimon = new Inventory<Engimon>();
        this.ownedSkill = new Inventory<Skill>();
        this.skillCounter = new ArrayList<Integer>();
        this.activeEngimonId = 0;
    }

    public Tile getPlayerPosition() {
        return this.currentPosition;
    }

    public void setPlayerPosition(Tile tile) {
        // set occupier in map
        // map.setTile(x, y, 'P')
        
        // set currentPosition jadi tile
        // this.currentPosition = map.getTile(x, y)
    }

    public void move(Entities.Map map, char movecode) {
        Tile newTile = new Tile();
        if (movecode == 'w') {
            newTile.set(map.getTileOnTop(this.currentPosition));
        } else if (movecode == 'a') {
            newTile.set(map.getTileOnleft(this.currentPosition));
        } else if (movecode == 's') {
            newTile.set(map.getTileBelow(this.currentPosition));
        } else if (movecode == 'd') {
            newTile.set(map.getTileOnRight(this.currentPosition));
        } else {
            return;
        }

        if (!newTile.isMoveable()) {
            return;
        }
        
        // clear unused tile
        Tile engTile = new Tile();
        engTile.set(this.getActiveEngimon().getCurrentPosition());

        map.setTileOcc(engTile.getAbsis(), engTile.getOrdinat(), ' ');

        // playerpos -> activeEngPos
        Tile playerTile = new Tile();
        playerTile.set(this.getPlayerPosition());
        playerTile.setOccupier('X');
        this.getActiveEngimon().setPosition(map, playerTile);

        // newTile -> playerPos
        newTile.setOccupier('P');
        map.setTileOcc(newTile.getOrdinat(), newTile.getAbsis(), 'P');
        this.setPlayerPosition(newTile);
    }

    /* ************ ENGIMON ************ */
    public void addEngimon(Engimon eng) {
        eng.setSymbol('X');
        this.ownedEngimon.add(eng);
    }

    public void removeEngimon(Engimon eng) {
        if (this.ownedEngimon.contains(eng)) {
            this.ownedEngimon.remove(eng);
        }
    }

    public Engimon getActiveEngimon() {
        return this.ownedEngimon.getItem(this.activeEngimonId);
    }

    public Engimon getEngimon(int id) {
        return this.ownedEngimon.getItem(id);
    }

    public void switchActiveEngimon(Entities.Map map, int new_eng_id) {
        Tile oldActiveEngTile = new Tile();
        oldActiveEngTile.set(this.getActiveEngimon().getCurrentPosition());
        this.setActiveEngimonId(new_eng_id);
        this.getActiveEngimon().setPosition(map, oldActiveEngTile);
    }

    public void setActiveEngimonId(int id) {
        this.activeEngimonId = id;
    }

    public int getActiveEngimonId() {
        return this.activeEngimonId;
    }

    public void setActiveEngimonPosition(java.util.Map map, Tile tile) {
        // set occupier in map
        // map.setTile(x, y, 'X')
        // Ehg8j9this.getActiveEngimon()
        // set currentPosition jadi tile
        // this.currentPosition = map.getTile(x, y)
    }

    public void interact() {
        println("[" + this.getActiveEngimon().getEngimonName() + "] : " + this.getActiveEngimon().getMessageUnik());
    }

    public void showOwnedEngimons() {
        println("Owned engimon(s):");
        for (int i = 0; i < this.ownedEngimon.size(); i++) {
            println("(" + i + ") " + this.ownedEngimon.getItem(i).getEngimonName());
        }
    }

    public void breed(Engimon eng1, Engimon eng2) {
        // Breed
    }

    public Tile isThereAnyEnemyAround(Entities.Map map) throws Exception {
        Tile[] surroundingTile = map.getSurroundingTile(this.currentPosition);
        for (Tile tile : surroundingTile) {
            if (tile.getOccupierCode() != ' ' && tile.getOccupierCode() != 'X') {
                return tile;
            }
        }
        String noEnemy = "Tidak ada engimon musuh di sekitarmu!\n";
        throw new Exception(noEnemy);
    }

    /* ************ SKILL ************ */
    public void addSkillItem(Skill item) {
        // if Skill udah punya
        if (this.ownedSkill.contains(item)) {
            int itemIndex = this.skillCounter.indexOf(item);
            this.skillCounter.add(itemIndex, (int)this.skillCounter.get(itemIndex)+1);
        } else {
            this.ownedSkill.add(item);
            this.skillCounter.add(1);
        }
    }

    public void useSkillItem(Skill item) {
        if (this.ownedSkill.contains(item)) {
            // this.getActiveEngimon().useSkill(item)
            int itemIndex = this.skillCounter.indexOf(item);
            this.skillCounter.set(itemIndex, (int)this.skillCounter.get(itemIndex)-1);
            this.ownedSkill.decrUsed();

            if (this.skillCounter.get(itemIndex) == 0) {
                this.skillCounter.remove(itemIndex);
                this.ownedSkill.remove(itemIndex);
            }
        }
    }

    public void showSkillItem() {
        println("Owned engimon(s):");
        for (int i = 0; i < this.ownedSkill.size(); i++) {
            println("(" + i + ") " + this.ownedSkill.getItem(i).getName() + "Qnty : " + this.skillCounter.get(i));

        }
    }

    // public static void main(String[] args) {
        
    // }
}
