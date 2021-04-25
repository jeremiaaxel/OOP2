package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/* Checklist Player
 * Menampilkan list dari command : showCommands;
 * Bergerak : move; tested
 * Menampilkan list ownedEngimon : showOwnedEngimons; tested
 * Menampilkan data lengkap suatu engimon di inventory : showEngimonDetails; belum sesuai spek
 * Mengecek activeEngimon : showActiveEngimonDetails; depend on showEngimonDetails
 * Mengganti active engimon : switchActiveEngimon; tested
 * Menampilkan list skill item : showSkillItem; belum sesuai spek, perlu informasi skill nya
 * Menggunakan skill item pada suatu engimon : useSkillItem; tested
 * Breeding : breed; belum, koordinasi sama yang buat (Bang Hadi (?))
 * Battle : ?
 * membuang x amount skill item : throwSomeSkillItems;
 * melepas engimon : freeTheEngimon; tested;
 * mengganti nama suatu engimon : changeEngimonName; tested
 * */

public class Player extends MapObject implements Serializable {
    private String name;
    private Tile currentPosition = new Tile();
    private Inventory<Engimon> ownedEngimon = new Inventory<Entities.Engimon>();
    private Inventory<Skill> ownedSkill = new Inventory<Entities.Skill>();
    private List<Integer> skillCounter = new ArrayList<>();
    private int activeEngimonId;

    private void println(Object obj) {
        System.out.println(obj);
    }

    private ArrayList<BufferedImage[]> sprites;

    // animation actions
    private static final int IDLE = 0;
    private static final int WALKING_DOWN = 1;
    private static final int WALKING_UP = 2;
    private static final int WALKING_LEFT = 3;
    private static final int WALKING_RIGHT = 4;


    /* ************ PLAYER ************ */
    public Player(String name, Entities.Map map) {
        super(map);
        this.name = name;
        this.currentPosition.setAbsis(7);
        this.currentPosition.setOrdinat(5);
        this.ownedEngimon = new Inventory<Engimon>();
        this.ownedSkill = new Inventory<Skill>();
        this.skillCounter = new ArrayList<Integer>();
        this.activeEngimonId = 0;
        this.setPositionByMap(7, 5);
        moveDistance = 1;


        // load sprites
        loadSprites();
    }

    public void loadSprites(){
        try
        {
            sprites = new ArrayList<>();

            BufferedImage[] img_buffer_list = new BufferedImage[1];
            img_buffer_list[0] = ImageIO.read(new FileInputStream("resources/player.png"));

            sprites.add(img_buffer_list);

            BufferedImage[] img_buffer_list1 = new BufferedImage[2];
            img_buffer_list1[0] = ImageIO.read(new FileInputStream("resources/player.png"));
            img_buffer_list1[1] = ImageIO.read(new FileInputStream("resources/player_2.png"));

            sprites.add(img_buffer_list1);

            BufferedImage[] img_buffer_list2 = new BufferedImage[2];
            img_buffer_list2[0] = ImageIO.read(new FileInputStream("resources/player_back.png"));
            img_buffer_list2[1] = ImageIO.read(new FileInputStream("resources/player_back_2.png"));

            sprites.add(img_buffer_list2);

            BufferedImage[] img_buffer_list3 = new BufferedImage[2];
            img_buffer_list3[0] = ImageIO.read(new FileInputStream("resources/player_left.png"));
            img_buffer_list3[1] = ImageIO.read(new FileInputStream("resources/player_left_2.png"));

            sprites.add(img_buffer_list3);

            BufferedImage[] img_buffer_list4 = new BufferedImage[2];
            img_buffer_list4[0] = ImageIO.read(new FileInputStream("resources/player_right.png"));
            img_buffer_list4[1] = ImageIO.read(new FileInputStream("resources/player_right_2.png"));

            sprites.add(img_buffer_list4);
        } catch(Exception e){
            e.printStackTrace();
        }

        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(currentAction));
        animation.setDelay(800);
    }

    public Tile getPlayerPosition() { return this.currentPosition; }

    public void setPlayerPosition(Entities.Map map, Tile tile) {
//         set occupier in map
        map.setTileOcc(this.currentPosition, 'P');
        
//         set currentPosition jadi tile
        this.currentPosition = map.getTile(tile);
    }

    public void showCommands() {
        println("commandnya apa weh");
    }

    /* ************ ENGIMON ************ */
    public void addEngimon(Engimon eng) {
        try {
            eng.setSymbol('X');
            this.ownedEngimon.add(eng);
        } catch (Exception e) {
            println(e.getMessage());
        }

    }

    private Engimon removeEngimon(Engimon eng) {
        this.ownedEngimon.remove(eng);
        return eng;
    }

    public Engimon removeEngimon(int id) {
        Engimon eng = removeEngimon(this.getEngimon(id));
        return eng;
    }

    public Engimon getActiveEngimon() {
        return this.ownedEngimon.getItem(this.activeEngimonId);
    }

    public Engimon getEngimon(int id) { return this.ownedEngimon.getItem(id); }

    public void switchActiveEngimon(Entities.Map map, int new_eng_id) {
        Tile oldActiveEngTile = new Tile();
        oldActiveEngTile.set(this.getActiveEngimon().getCurrentPosition());
        this.setActiveEngimonId(new_eng_id);
        this.getActiveEngimon().setTilePosition(map, oldActiveEngTile);
    }

    private void setActiveEngimonId(int id) {
        this.activeEngimonId = id;
    }

    public int getActiveEngimonId() {
        return this.activeEngimonId;
    }

    public void setActiveEngimonPosition(Entities.Map map, Tile tile) {
        // set occupier in map
        map.setTileOcc(this.getActiveEngimon().getCurrentPosition(), ' ');

        // set currentPosition jadi tile
        tile.setOccupier('X');
        this.getActiveEngimon().setTilePosition(map, tile);
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

    public void showEngimonDetails(int index) {
        this.ownedEngimon.getItem(index).displayEngimonInfo();
    }

    public void showActiveEngimonDetails() {
        showEngimonDetails(this.activeEngimonId);
    }

    public void breed(int id1, int id2) {
        Engimon eng1 = this.getEngimon(id1);
        Engimon eng2 = this.getEngimon(id2);
        Breeding breed = new Breeding(eng1, eng2, this.getPlayerPosition());
//        breed.

    }

    public void freeTheEngimon(int id) {
        Engimon eng = this.removeEngimon(id);
        println(eng.getEngimonName() + " telah dibebaskan!");
    }

    public void changeEngimonName(int id, String name) {
        this.getEngimon(id).setEngimonName(name);
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

    public int getOwnedEngimonSize() { return this.ownedEngimon.size(); }

    /* ************ SKILL ************ */
    private int isSkillOwned(Skill item) {
        for (int i = 0; i < this.ownedSkill.size(); i++) {
            if (item.getName() == this.ownedSkill.getItem(i).getName()) {
                return i;
            }
        }
        return -1;
    }

    private void incrSkillCounter(int index) {
        this.skillCounter.set(index, this.skillCounter.get(index) + 1);
    }

    private void decrSkillCounter(int index) {
        this.skillCounter.set(index, this.skillCounter.get(index) - 1);
    }

    public void addSkillItem(Skill item) {
        // if Skill udah punya
        int itemIndex = this.isSkillOwned(item);
        if (itemIndex != -1) {
            incrSkillCounter(itemIndex);
        } else {
            try {
                this.ownedSkill.add(item);
                this.skillCounter.add(1);
            } catch (Exception e) {
                println(e.getMessage());
            }

        }
    }

    public Skill getSkillItem(int index) {
        return this.ownedSkill.getItem(index);
    }

    public void useSkillItem(Skill item) {
        int itemIndex = isSkillOwned(item);
        if (itemIndex != -1) {
            try {
                this.getActiveEngimon().addSkill(item);
            } catch (Exception e) {
                println(e.getMessage());
            }
            decrSkillCounter(itemIndex);
            this.ownedSkill.decrUsed();

            if (this.skillCounter.get(itemIndex) == 0) {
                removeSkillItem(itemIndex);
            }
        }
    }

    private void removeSkillItem(int index) {
        this.skillCounter.remove(index);
        this.ownedSkill.remove(index);
    }

    public void showSkillItem() throws Exception {
        println("Owned skill(s):");
        for (int i = 0; i < this.ownedSkill.size(); i++) {
            println("(" + i + ") " + this.ownedSkill.getItem(i).getName() + " - Qnty : " + this.skillCounter.get(i));
            this.ownedSkill.getItem(i).displaySkillInfo();
        }
    }

    public int getOwnedSkillItemSize() {
        int size = 0;
        for (int i = 0; i < this.skillCounter.size(); i++) {
            size += this.skillCounter.get(i).intValue();
        }
        return size;
    }

    public void throwSomeSkillItems(Skill skill, int amount) {
        int itemIndex = isSkillOwned(skill);
        // Kalau punya
        if (itemIndex == -1) {
            // Ga punya
            println("Kamu tidak memiliki item tersebut!");
            return;
        } else {
            if (amount > this.skillCounter.get(itemIndex)) {
                // amount > kuantitas yang dimiliki
                println("Kamu tidak memiliki item sebanyak itu");
                return;
            } else if (amount < this.skillCounter.get(itemIndex)){
                // lancar jaya
                this.skillCounter.set(itemIndex, this.skillCounter.get(itemIndex) - amount);
            } else {
                removeSkillItem(itemIndex);
            }
        }

    }

    public void nextPosition(){
        if (left){
            xtemp -= moveDistance;
            ytemp = y;
        } else if (right){
            xtemp += moveDistance;
            ytemp = y;
        } else if (up){
            xtemp = x;
            ytemp -= moveDistance;
        } else if (down){
            xtemp = x;
            ytemp += moveDistance;
        } else {
            xtemp = x;
            ytemp = y;
        }

        if (xtemp<0 || ytemp <0
                || ytemp >= map.getTilesize()*(map.getNumberOfRow()-3)
                || xtemp >= map.getTilesize()*(map.getNumberOfColumn()-3)){
            xtemp = x;
            ytemp = y;
        }
    }

    public void update(){
        // update semua engimon player

        // update position
        nextPosition();
        handleCollision();
        setPosition(xtemp,ytemp);

        // set animation
        if (currentAction == IDLE && (left || down || right || up)){
            if (left){
                currentAction = WALKING_LEFT;
                animation.setFrames(sprites.get(currentAction));
                animation.setDelay(100);
            } else if (right){
                currentAction = WALKING_RIGHT;
                animation.setFrames(sprites.get(currentAction));
                animation.setDelay(100);
            } else if (up){
                currentAction = WALKING_UP;
                animation.setFrames(sprites.get(currentAction));
                animation.setDelay(100);
            } else{
                currentAction = WALKING_DOWN;
                animation.setFrames(sprites.get(currentAction));
                animation.setDelay(100);
            }
        } else if (!(left || down || right || up)){
                currentAction = IDLE;
                animation.setFrames(sprites.get(currentAction));
                animation.setDelay(500);
        }

        animation.update();
    }

    public void draw(Graphics2D g){
        if (left || right || up || down){
            g.drawImage(animation.getImage(),
                    (int) (x),
                    (int) (y),
                    width,
                    height,
                    null);
        } else {
            // IDLE
            g.drawImage(animation.getImage(),
                    (int) x, (int) y, width, height, null);
        }
    }

    public void move(Entities.Map map, char movecode) {
        Entities.Tile newTile = new Tile();
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
        this.getActiveEngimon().setTilePosition(map, playerTile);

        // newTile -> playerPos
        newTile.setOccupier('P');
        map.setTileOcc(newTile.getOrdinat(), newTile.getAbsis(), 'P');
        this.setPlayerPosition(map, newTile);
    }

    public void handleCollision(){
        if (map.isOccupied(getMapRowFromOrd(ytemp),getMapColFromAbsis(xtemp)) &&
                !(getMapRowFromOrd(ytemp) == y_map && getMapColFromAbsis(xtemp) == x_map)){
            System.out.println("occupied : " + getMapRowFromOrd(ytemp)+","+getMapColFromAbsis(xtemp));
            xtemp = x;
            ytemp = y;
        }
    }
}
