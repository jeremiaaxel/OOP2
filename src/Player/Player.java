import java.util.ArrayList;
import java.util.List;

public class Player {
    private Tile currentPosition;
    private Inventory<Engimon> ownedEngimon;
    private Inventory<Skill> ownedSkill;
    private List<Integer> skillCounter;
    private int activeEngimonId;
    private void println(String string) {
        System.out.println(string);
    }
    
    /* ************ PLAYER ************ */
    public Player() {
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

    public void move(char movecode) {
        if (movecode == 'w') {
            Tile newTile = map.getTileOnTop(this.currentPosition);
        } else if (movecode == 'a') {
            Tile newTile = map.getTileOnleft(this.currentPosition);
        } else if (movecode == 's') {
            Tile newTile = map.getTileBelow(this.currentPosition);
        } else if (movecode == 'd') {
            Tile newTile = map.getTileOnRight(this.currentPosition);
        } else {
            return;
        }

        if (!T.isMoveable()) {
            return;
        }
        
        // clear unused tile
        Tile engTile = this.activeEngimon().getCurrentPosition();
        map.setTileOcc(engTile.getAbsis(), engTile.getOrdinat(), ' ');

        // playerpos -> activeEngPos
        Tile playerTile = this.getCurrentPosition();
        playerTile.setOccupier('X');
        this.getActiveEngimon().setPosition(playerTile);

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

    public void switchActiveEngimon(int new_eng_id) {
        Tile old_activeEng_tile = this.getActiveEngimon().getCurrentPosition();
        this.setActiveEngimonId(new_eng_id);
        this.getActiveEngimon().setPosition(old_activeEng_tile);
    }

    public void setActiveEngimonId(int id) {
        this.activeEngimonId = id;
    }

    public int getActiveEngimonId() {
        return this.activeEngimonId;
    }

    public void setActiveEngimonPosition(int x, int y) {
        // set occupier in map
        // map.setTile(x, y, 'X')
        
        // set currentPosition jadi tile
        // this.currentPosition = map.getTile(x, y)
    }

    public void interact() {
        println("[" + this.getActiveEngimon().getEngimonName() + "] : " + this.getActiveEngimon().getMessageUnik());
    }

    public void showOwnedEngimons() {
        int counter = 0;
        println("Owned engimon(s):");
        for (Engimon eng : this.ownedEngimon) {
            println("(" + counter + ") " + eng.getName());
            counter += 1;
        }
    }

    public void breed(Engimon eng1, Engimon eng2) {
        // Breed
    }

    public Tile isThereAnyEnemyAround() {
        // getSurroundingTile
        // for (Tile tile : surroundingTile) {
        //     if (surrTile[i].getOccupierCode() != ' ' && surrTile[i].getOccupierCode() != 'X') {
        //         return surrTile[i];
        //     }
        // }
        String noEnemy = "Tidak ada engimon musuh di sekitarmu!\n";
        throw new Exception(noEnemy);
    }

    /* ************ SKILL ************ */
    public void addSkillItem(Skill item) {
        // if Skill udah punya
        if (this.ownedSkill.contains(item)) {
            int itemIndex = this.skillCounter.indexOf(item);
            this.skillCounter.add(itemIndex, this.skillCounter.get(itemIndex)+1);
        } else {
            this.ownedSkill.add(item);
            this.skillCounter.add(1);
        }
    }

    public void useSkillItem(Skill item) {
        if (this.ownedSkill.contains(item)) {
            // this.getActiveEngimon().useSkill(item)
            int itemIndex = this.skillCounter.indexOf(item);
            this.skillCounter.set(itemIndex, this.ownedSkill.getItem(itemIndex)-1);
            this.ownedSkill.decrUsed();

            if (this.skillCounter.get(itemIndex) == 0) {
                this.skillCounter.remove(itemIndex);
                this.ownedSkill.remove(itemIndex);
            }
        }
    }

    public void showSkillItem() {
        int counter = 0;
        println("Owned engimon(s):");
        for (Skill item : this.ownedSkill) {
            println("(" + counter + ") " + item.getName() + "Qnty : " + this.skillCounter.get(this.ownedSkill.getIndex(item)));
            counter += 1;
        }
    }

    
}
