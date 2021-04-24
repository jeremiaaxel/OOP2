package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;

public class Player extends MapObject {
    private String name;
    private Tile currentPosition = new Tile();
    private Inventory<Engimon> ownedEngimon = new Inventory<>();
    private Inventory<Skill> ownedSkill = new Inventory<>();
    private List<Integer> skillCounter = new ArrayList<>();
    private int activeEngimonId;

    private void println(String string) {
        System.out.println(string);
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
        this.setPosition(7 * map.getTilesize(), 5 * map.getTilesize());
        moveDistance = 0.8;


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

    public void nextPosition(){
        if (left){
            xtemp -= moveDistance;
//            xtemp = x - moveDistance;
            ytemp = y;
        } else if (right){
            xtemp += moveDistance;
//            xtemp = x + moveDistance;
            ytemp = y;
        } else if (up){
            xtemp = x;
            ytemp -= moveDistance;
//            ytemp = y - moveDistance;
        } else if (down){
            xtemp = x;
            ytemp += moveDistance;
//            ytemp = y + moveDistance;
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
//        // update position
        nextPosition();
//        checkCollision();
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
            //IDLE
            g.drawImage(animation.getImage(),
                    (int) x, (int) y, width, height, null);
        }
    }
}
