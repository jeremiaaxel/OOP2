package Entities;

import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Entity;
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
    public static BufferedImage backgroundImage;

    private void println(Object obj) {
        System.out.println(obj);
    }

    private transient ArrayList<BufferedImage[]> sprites;

    // animation actions
    private static final int IDLE = 0;
    private static final int WALKING_DOWN = 1;
    private static final int WALKING_UP = 2;
    private static final int WALKING_LEFT = 3;
    private static final int WALKING_RIGHT = 4;

    private boolean vertical;

    /* ************ PLAYER ************ */
    public Player(String name, Entities.Map map) {
        super(map);
        this.name = name;
        this.currentPosition.setAbsis(7);
        this.currentPosition.setOrdinat(5);
        this.ownedEngimon = new Inventory<Engimon>();
        this.ownedSkill = new Inventory<Skill>();
        this.skillCounter = new ArrayList<Integer>();
        this.setPositionByMap(this.currentPosition.getAbsis(), this.currentPosition.getOrdinat());
        moveDistance = 3;
    }

    public Player(Player playerin, Entities.Map map) {
        super(map);
        this.name = playerin.name;
        this.setPlayerPosition(playerin.getPlayerPosition());
        this.ownedEngimon = new Inventory<Engimon>();
        // masukin engimon
        for (int i = 0; i < playerin.getOwnedEngimonSize(); i++) {
            this.addEngimon(playerin.getEngimon(i));
        }

        this.ownedSkill = new Inventory<Skill>();
        this.skillCounter = new ArrayList<Integer>();
        // masukin skill item
        for (int i = 0; i < playerin.getOwnedSkillItemSize(true); i++) {
            for (int j = 0; j < playerin.getSkillItemQuantity(i); j++) {
                this.addSkillItem(playerin.getSkillItem(i));
            }
        }

        this.setPositionByMap(this.currentPosition.getAbsis(), this.currentPosition.getOrdinat());
        moveDistance = 3;

        this.setActiveEngimonPosition(playerin.getActiveEngimon().getCurrentPosition());
        this.setActiveEngimonPosition(playerin.getActiveEngimon().getCurrentPosition());

        // load sprites
        loadSprites();
    }

    public List<String> getOwnedSkillNames() {
        List<String> owned = new ArrayList<>();
        for (int i = 0; i < this.getOwnedSkillItemSize(true); i++) {
            owned.add(i + " - " + this.getSkillItem(i).getName() + " - Quantity : " + this.getSkillItemQuantity(i));
        }
        return owned;
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

    public String getPlayerName() { return this.name; }

    public Tile getPlayerPosition() { return this.currentPosition; }

    public void showCommands(Graphics2D g, int x, int y) {
        g.setColor(new Color(255,217,179));
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
        g.drawString("Command List : ",x,y);

        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        g.drawString("1    :  List Skill Player", x + 15,y+30);
        g.drawString("2    :  Player Info", x + 15,y+50);
        g.drawString("3    :  Wild Engimon List", x + 15,y+70);
        g.drawString("4    :  Owned Engimon List", x + 15,y+90);
        g.drawString("5    :  Switch Active Engimon", x + 15,y+110);
        g.drawString("6    :  Rename Engimon", x + 15,y+130);
        g.drawString("7    :  Drop Inventory Items", x + 15,y+150);
        g.drawString("8    :  Engimon Details", x + 15,y+170);
        g.drawString("9    :  Breeding", x + 15,y+190);
        g.drawString("10   :  List Skill Player", x + 15,y+210);
        g.drawString("s    :  Save Game", x + 15,y+230);
        g.drawString("h    :  Command List", x + 15,y+250);
        g.drawString("i    :  Interact", x + 15,y+270);
        g.drawString("esc  :  Quit", x + 15,y+290);
        g.drawString("space:  Battle", x + 15,y+310);

    }

    /* ************ ENGIMON ************ */
    public void addEngimon(Engimon eng) {
        try {
            eng.setSymbol('x');
            eng.setWild(false);
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

    public void switchActiveEngimon(int new_eng_id) {
        if (new_eng_id != activeEngimonId){
            Tile oldActiveEngTile = new Tile();
            this.getEngimon(new_eng_id).setPosition(getActiveEngimon().x,getActiveEngimon().y);
            oldActiveEngTile.set(this.getActiveEngimon().getCurrentPosition());
            this.setActiveEngimonId(new_eng_id);
            this.getActiveEngimon().setTilePosition(oldActiveEngTile);
        }
    }

    public void setActiveEngimonId(int id) {
        this.activeEngimonId = id;
    }

    public int getActiveEngimonId() {
        return this.activeEngimonId;
    }

    public void setActiveEngimonPosition(Tile tile) {
        // set currentPosition jadi tile
        this.getActiveEngimon().setTilePosition(tile);
    }

    public void interact(Graphics2D g, int x, int y) {
        g.setColor(new Color(255,217,179));
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        g.drawString("Interact with active engimon",x,y);
        g.drawString("[" + this.getActiveEngimon().getEngimonName() + "] : \n" + this.getActiveEngimon().getMessageUnik(),x,y+30);
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

    public void breed(int id1, int id2, Player player) {
        Breeding breed = new Breeding(this.getEngimon(id1), this.getEngimon(id2), this.getPlayerPosition(), player);

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

    public void useSkillItem(Skill item) throws Exception {
        int itemIndex = isSkillOwned(item);
        if (itemIndex != -1) {
            this.getActiveEngimon().addSkill(item);
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

    public int getOwnedSkillItemSize(boolean distinct) {
        if (distinct) {
            return this.ownedSkill.size();
        } else {
            return getOwnedSkillItemSize();
        }
    }

    public int getSkillItemQuantity(int index) { return this.skillCounter.get(index); }

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

    public synchronized void nextPosition(){
        if (left){
            xtemp -= moveDistance;
            ytemp = y;
            getActiveEngimon().xtemp = x + width;
            if (vertical){
                getActiveEngimon().ytemp = ytemp;
                vertical = false;
            }
        } else if (right){
            xtemp += moveDistance;
            ytemp = y;
            getActiveEngimon().xtemp = x - getActiveEngimon().width;
            if (vertical){
                getActiveEngimon().ytemp = ytemp;
                vertical = false;
            }
        } else if (up){
            xtemp = x;
            ytemp -= moveDistance;
            getActiveEngimon().ytemp = y + width;
            if (!vertical){
                getActiveEngimon().xtemp = xtemp;
                vertical = true;
            }
        } else if (down){
            xtemp = x;
            ytemp += moveDistance;
            getActiveEngimon().ytemp = y - getActiveEngimon().height;
            if (!vertical){
                getActiveEngimon().xtemp = xtemp;
                vertical = true;
            }
        }

        if (isOutOfMap(xtemp,ytemp)){
            xtemp = x;
            ytemp = y;
            getActiveEngimon().xtemp = getActiveEngimon().x;
            getActiveEngimon().ytemp = getActiveEngimon().y;
        }
        if (isOutOfMap(getActiveEngimon().xtemp,getActiveEngimon().ytemp)){
            getActiveEngimon().xtemp = xtemp;
            getActiveEngimon().ytemp = ytemp;
        }
    }

    public boolean isOutOfMap(double x, double y){
        return x<0 || y <0
                || y >= map.getTilesize()*(map.getNumberOfRow()-3)
                || x >= map.getTilesize()*(map.getNumberOfColumn()-3);
    }

    public void setPlayerPosition(Tile tile){
        map.setTileOcc(tile,map.OCCUPIED);
        map.setTileOcc(currentPosition,map.NO_OCCUPIER);
        currentPosition = tile;
    }

    public void updatePosition(double xplayer, double yplayer, double xactiveEng, double yactiveEng){
        if (xplayer != -1 && xactiveEng !=-1){
//            setPlayerPosition(map.getTile(getMapRowFromOrd(yplayer),getMapColFromAbsis(xplayer)));
            setPosition(xplayer,yplayer);
            currentPosition = map.getTile(y_map,x_map);
//            setActiveEngimonPosition(map.getTile(getMapRowFromOrd(yactiveEng),getMapColFromAbsis(xactiveEng)));
            getActiveEngimon().setPosition(xactiveEng,yactiveEng);
            getActiveEngimon().setTilePosition(map.getTile(y_map,x_map));
        }
    }

    public synchronized void update(WildEngimon w){
        // update semua engimon player
        ArrayList<Engimon> engToDelete = new ArrayList<>();
        Engimon eng;
        for (int i = 0; i < getOwnedEngimonSize(); i++){
            eng = getEngimon(i);
            eng.update();
            if (eng.isdead){
                engToDelete.add(eng);
            }
        }
        for (Engimon e: engToDelete) {
            eng = removeEngimon(e);
        }

        // update position
        nextPosition();
        handleCollision(w);

        // update position
        updatePosition(xtemp,ytemp,getActiveEngimon().xtemp,getActiveEngimon().ytemp);

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
        // draw player
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
        // draw active engimon
        g.drawImage(getActiveEngimon().animation.getImage(), (int) getActiveEngimon().x,
                (int) getActiveEngimon().y,
                getActiveEngimon().width,getActiveEngimon().height, null);
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
        this.getActiveEngimon().setTilePosition(playerTile);

        // newTile -> playerPos
        newTile.setOccupier('P');
        map.setTileOcc(newTile.getOrdinat(), newTile.getAbsis(), 'P');
        this.setPlayerPosition(newTile);
    }

    public void handleCollision(WildEngimon w){
        if (checkCollision(w)){
            println("occupied");
            xtemp = x;
            ytemp = y;
            getActiveEngimon().xtemp = getActiveEngimon().x;
            getActiveEngimon().ytemp = getActiveEngimon().y;
        }
    }

    public boolean checkCollision(WildEngimon w){
//        boolean result = false;
//        for (int i = 0 ; i < w.getNumberOfWildEngimon(); i ++){
//           if (w.getNthEngimon(i).x > xtemp && (w.getNthEngimon(i).x < (xtemp + width))){
//               return true;
//           } else if (w.getNthEngimon(i).x < xtemp && (w.getNthEngimon(i).x > (xtemp - width))){
//               return true;
//           } else if (w.getNthEngimon(i).y < ytemp && (w.getNthEngimon(i).y > (ytemp - height))){
//               return true;
//           } else if (w.getNthEngimon(i).y > ytemp && (w.getNthEngimon(i).y < (ytemp + height))){
//               return true;
//           }
//        }
        return map.isOccupied(getMapRowFromOrd(ytemp), getMapColFromAbsis(xtemp))
                && !(getMapRowFromOrd(ytemp) == y_map && getMapColFromAbsis(xtemp) == x_map) &&
                !(getMapRowFromOrd(ytemp) == getActiveEngimon().y_map && getMapColFromAbsis(xtemp) == getActiveEngimon().x_map);

//        if (left) {
//            result = map.isOccupied(getMapRowFromOrd(ytemp), getMapColFromAbsis(xtemp));
//        } else if (right){
//            result = map.isOccupied(getMapRowFromOrd(ytemp), getMapColFromAbsis(xtemp));
//        } else if (up){
//            result = map.isOccupied(getMapRowFromOrd(ytemp),getMapColFromAbsis(xtemp));
//        } else if (down){
//            result = map.isOccupied(getMapRowFromOrd(ytemp),getMapColFromAbsis(xtemp));
////        }
//        return map.isOccupied(getMapRowFromOrd(ytemp), getMapColFromAbsis(xtemp)) &&
//                !(getMapRowFromOrd(ytemp) == y_map && getMapColFromAbsis(xtemp) == x_map) &&
//                !(getMapRowFromOrd(ytemp) == getActiveEngimon().ytemp && getMapColFromAbsis(xtemp) == xtemp);
    }

    public Inventory<Engimon> getOwnedEngimon(){
        return this.ownedEngimon;
    }

    public void draw_listEngimon(Graphics2D g) {
        int x = (map.getNumberOfColumn()-2) * map.getTilesize();

        g.setColor(new Color(255, 217, 179));
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));

        g.drawString("Daftar Engimon Player :", x + 20, 100);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        for (int i = 0; i < getOwnedEngimonSize(); i++) {
            g.drawString(" ID : " + i + " - "+getOwnedEngimon().getItem(i).getEngimonName(),
                    x + 35, 125 + (25 * i));
        }
    }

    public void draw_Player(Graphics2D g){
        g.setColor(new Color(255,217,179));
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));

        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
        g.drawString("* Player :  ",map.getNumberOfColumn()*map.getTilesize()-20,125);
        g.drawString(this.getPlayerName(),map.getNumberOfColumn()*map.getTilesize(),150);
        g.drawString("* Active Engimon :  ",map.getNumberOfColumn()*map.getTilesize()-20,200);
        g.drawString(this.getActiveEngimon().getEngimonName(),map.getNumberOfColumn()*map.getTilesize(),225);
        g.drawString("* Level :  ",map.getNumberOfColumn()*map.getTilesize()-20,275);
        g.drawString(String.format("%d",this.getActiveEngimon().getEngimonLevel()),map.getNumberOfColumn()*map.getTilesize(),300);

        g.drawString("* Exp :  ",map.getNumberOfColumn()*map.getTilesize()-20,350);
        g.drawString(String.format("%d",this.getActiveEngimon().getEngimonExp()),map.getNumberOfColumn()*map.getTilesize(),375);
    }

    public void draw_Skill(Graphics2D g){
        g.setColor(new Color(255,217,179));
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));

        g.drawString("* Active Engimon :",map.getNumberOfColumn()*map.getTilesize()-20,100);
        g.drawString(this.getActiveEngimon().getEngimonName(),map.getNumberOfColumn()*map.getTilesize(),125);
        g.drawString("* Skill:",map.getNumberOfColumn()*map.getTilesize()-20,175);
        Skill[] c = this.getActiveEngimon().engimonSkill;
        try{
            String backgroundPath = "resources/firee_10.png";
            backgroundImage = ImageIO.read(new FileInputStream(backgroundPath));
        } catch (Exception e){
            e.printStackTrace();
        }

        for(int i=0; i <this.getActiveEngimon().getNumberOfSkill();i++){
            g.drawString(c[i].getName(),map.getNumberOfColumn()*map.getTilesize(),200+(50*i));
            g.drawString(String.format("%d",c[i].getMasteryLevel()),map.getNumberOfColumn()*map.getTilesize(),225+(50*i));
            if(c[i].getMasteryLevel() == 10) {
                g.drawImage(backgroundImage, map.getNumberOfColumn()*map.getTilesize()-20,225+(50*i),40,40,null);
            }
            if(c[i].getMasteryLevel() == 15) {

            }
            if(c[i].getMasteryLevel() == 20) {

            }
        }
    }

    public Object[] getListIdEng(){
        Object[] list = new Object[getOwnedEngimonSize()];
        for (int i = 0; i < getOwnedEngimonSize(); i++) {
            list[i] = i;
        }
        return list;
    }

    public Engimon getEnemeyArround(WildEngimon w){
        Engimon eng = w.engimonOnTheTile(map.getTileOnleft(currentPosition));
        if (eng != null){
            return eng;
        }
        eng = w.engimonOnTheTile(map.getTileOnRight(currentPosition));
        if (eng != null){
            return eng;
        }
        eng = w.engimonOnTheTile(map.getTileOnTop(currentPosition));
        if (eng != null){
            return eng;
        }
        eng = w.engimonOnTheTile(map.getTileBelow(currentPosition));
        if (eng != null){
            return eng;
        }
        return null;
    }
}
