package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public abstract class Engimon extends MapObject{
    protected char engimonSymbol;
    protected String engimonName;
    protected String engimonSpesies;
    protected Parent engimonParent;
    protected Skill[] engimonSkill;
    protected String[] engimonElement;
    protected int engimonLevel;
    protected final int engimonLife = 3;
    protected int engimonExperience;
    protected int engimonCumulativeExp;
    protected Tile currentPosition;
    protected int numberOfSkill;
    protected int numberOfElement;
    protected String messageUnik;
    protected final int maxCumExp = 500;
    public boolean isdead = false;
    protected BufferedImage image;

    protected boolean wild = false;

    public Engimon(char engimonSymbol, String engimonName, String engimonSpesies, Parent parent, Tile position,Map map){
        super(map);
        this.engimonSymbol = engimonSymbol;
        this.engimonName = engimonName;
        this.engimonSpesies = engimonSpesies;
        this.engimonParent = parent;
        this.currentPosition = position;
        this.engimonLevel = 1;
        this.engimonExperience = 0;
        this.engimonCumulativeExp = 0;
        this.engimonSkill = new Skill[4];
        this.engimonElement = new String[2];
        this.numberOfSkill = 0;
        this.numberOfElement = 0;

        this.setTilePosition(position);
        loadImg();
    }

    public void loadImg(){
        try {
            animation = new Animation();
            BufferedImage[] b = new BufferedImage[1];
            b[0] = ImageIO.read(new FileInputStream("resources/engimon.gif"));
            animation.setFrames(b);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setWild(boolean wild){
        this.wild = wild;
    }

    // getter
    public String getEngimonName(){
        return engimonName;
    }
    public char getEngimonSymbol(){ return engimonSymbol; }
    public String getEngimonSpesies() {
        return engimonSpesies;
    }
    public int getEngimonLife(){ return this.engimonLife; }
    public int getEngimonLevel(){
        return engimonLevel;
    }
    public int getEngimonExp(){
        return engimonExperience;
    }
    public int getCumulativeExp(){
        return engimonCumulativeExp;
    }
    public int getNumberOfSkill(){
        return numberOfSkill;
    }
    public int getNumberOfElement(){
        return numberOfElement;
    }
    public float getPower(Engimon other) throws Exception {
        return engimonLevel*getAdvantage(other) + getSkillTotalPower();
    }
    public Tile getCurrentPosition(){
        return this.currentPosition;
    }
    public int getSkillTotalPower(){
        int sum = 0;
        for (int i = 0; i < numberOfSkill; i++){
            sum+= engimonSkill[i].getBasePower() * engimonSkill[i].getMasteryLevel();
        }
        return sum;
    }
    public String getMessageUnik(){
        return this.messageUnik;
    }
    public Skill getNthEngimonSkill(int n){
        if (n > numberOfSkill || n <= 0){
            throw new IndexOutOfBoundsException();
        }
        return engimonSkill[n-1];
    }
    public String getNthEngimonElement(int n){
        if (n > numberOfElement || n <= 0){
            throw new IndexOutOfBoundsException();
        }
        return engimonElement[n-1];
    }

    public abstract float getAdvantage(Engimon other) throws Exception;

    // Setter
    public void setSymbol(char c){ this.engimonSymbol = c; }
    public void setLevel(int lvl){
        this.engimonLevel = lvl;
    }
    public void setLife(int life){
        this.engimonLevel = life;
    }
    public void setMessageUnik(String msg) {
        this.messageUnik = msg;
    }
    public void setTilePosition(Tile t){
        setPositionByMap(t.getAbsis(),t.getOrdinat());
        this.currentPosition = t;
    }

    // Other method
    public void delSkill(int idx) throws IndexOutOfBoundsException{
        int i;
        // Menghapus skill dengan id 'idx'
        if (idx<0 || idx>=numberOfSkill){
            throw new IndexOutOfBoundsException();
        }
        for (i = idx; i < this.numberOfSkill -1; i++){
            this.engimonSkill[i] = this.engimonSkill[i+1];
        }
        this.numberOfSkill--;
    }
    public void addSkill(Skill s) throws Exception {
        if (numberOfSkill == 4) {
            throw new Exception("Engimon ini sudah mencapai maksimum banyak skill");
        }
        engimonSkill[numberOfSkill] = s;
        numberOfSkill++;
    }
    public void addElement(String elmt) {
        if (numberOfElement < 2) {
            engimonElement[numberOfElement] = elmt;
            numberOfElement++;
        }
    }

    public void addExp(int exp){
        engimonExperience+= exp;
        engimonCumulativeExp += exp;
    }

    public void updateExistence(){
        if (engimonCumulativeExp > maxCumExp ){
            isdead = true;
        }
    }

    public void levelUp(){
        if (engimonExperience >= 100){
            int levelplus = engimonExperience / 100;
            engimonLevel += levelplus;
            engimonExperience %= 100;
        }
    }
    public void displayAllEngimonSkill(){
        for (int i = 0; i < numberOfSkill; i++){
            System.out.println("- Skill " + (i+1));
            try {
                engimonSkill[i].displaySkillInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void displayAllEngimonSkillName(){
        if (numberOfSkill > 0){
            System.out.print(engimonSkill[0].getName());
            for (int i = 1; i < numberOfSkill; i++) {
                System.out.print(", " + engimonSkill[i].getName());
            }
        } else{
            System.out.print("-");
        }
        System.out.println();
    }

    public void move(){
        moveDistance = this.map.getTilesize();
        if (left){
            System.out.println("left");
            xtemp -= moveDistance;
        } else if (right){
            System.out.println("right");
            xtemp += moveDistance;
        } else if (up){
            System.out.println("up");
            ytemp -= moveDistance;
        }  else if (down){
            System.out.println("down");
            ytemp += moveDistance;
        }

        if (xtemp<0 || ytemp <0
                || ytemp >= map.getTilesize()*(map.getNumberOfRow()-3)
                || xtemp >= map.getTilesize()*(map.getNumberOfColumn()-3)){
            xtemp = x;
            ytemp = y;
        }

        if (wild){
            System.out.println("map :" + currentPosition.getOrdinat() +"," + currentPosition.getAbsis());
            System.out.println("map :" + getMapRowFromOrd(ytemp) +"," + getMapColFromAbsis(xtemp));
            System.out.println("type :" + map.getTile(getMapRowFromOrd(ytemp),
                    getMapColFromAbsis(xtemp)).getType());
            displayAllEngimonElement();
            System.out.println(isTileTypeCompatible(map.getTile(getMapRowFromOrd(ytemp),
                    getMapColFromAbsis(xtemp)).getType()));
            if (isTileTypeCompatible(map.getTile(getMapRowFromOrd(ytemp),
                    getMapColFromAbsis(xtemp)).getType())){
                System.out.println("masuk");
                setPosition(xtemp,ytemp);
            };
        } else {
            setPosition(xtemp,ytemp);
        }
    }

    public Boolean isTileTypeCompatible(char type){
        if (engimonElement[0] == "Water"){
            return type == 's';
        } else if (engimonElement[0] == "Electrical"){
            return type == 'g';
        } else if (engimonElement[0] == "Fire"){
            return type == 'm';
        } else if (engimonElement[0] == "Ground"){
            return type == 'g';
        } else if (engimonElement[0] == "Ice"){
            return type == 't';
        }
        return false;
    }

    public void displayAllEngimonElement(){
        int counter = 0;
        for (String el: engimonElement) {
            if (el != null) {
                System.out.print(el);
                if (counter != numberOfElement-1){
                    System.out.print(",");
                }
                counter++;
            }
        }
    }

    public void displayEngimonInfo(){
        System.out.println("Name                   : " + getEngimonName());
        System.out.println("Parent                 : "); engimonParent.displayInfo();
        System.out.println("Skill                  : "); displayAllEngimonSkillName();;
        System.out.println("Element                : "); displayAllEngimonElement();
        System.out.println("Level                  : " + getEngimonLevel());
        System.out.println("Experience             : " + getEngimonExp());
        System.out.println("Cumulative Experience  : " + getCumulativeExp());
        System.out.println("Life                   : " + getEngimonLife());
    }

    public void setEngimonName(String name) {
        this.engimonName = name;
    }

    public void draw(Graphics2D g){
            g.drawImage(animation.getImage(),
                    (int) x, (int) y, width, height, null);
    }

    public void update(){
        this.levelUp();
        this.updateExistence();
    }
}
