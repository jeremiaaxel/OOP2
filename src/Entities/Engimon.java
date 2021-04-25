package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;

public abstract class Engimon extends MapObject implements Serializable {
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
    protected final int maxCumExp = 800;
    public boolean isdead = false;
    private transient GradientPaint paint;

    protected boolean wild = true;

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

        width -= 5;
        height -= 5;

        this.setTilePosition(position);
        loadImg();
    }

    public String describe() throws Exception {
        String result = "";
        result.concat("ENGIMONPARENT\n");
        result.concat(this.getEngimonParent().describe());
        result.concat("ENGIMON\n");
        result.concat(this.getEngimonSymbol() + "\n");
        result.concat(this.getEngimonName() + "\n");
        result.concat(this.getEngimonSpesies() + "\n");
        result.concat(this.getEngimonLevel() + "\n");
        result.concat(this.getEngimonExp() + "\n");
        for (int j = 0; j < this.getNumberOfSkill(); j++) {
            result.concat("ENGIMONSKILL " + j + "\n");
            result.concat(this.getNthEngimonSkill(j).describe());
        }
        for (int i = 0; i < this.getNumberOfElement(); i++) {
            result.concat("ENGIMONELMT " + i + "\n");
            result.concat(this.getNthEngimonElement(i) + "\n");
        }
        result.concat(this.getCurrentPosition().getAbsis() + "," + this.getCurrentPosition().getOrdinat() + "\n");
        result.concat(this.getEngimonLife() + "\n");
        return result;
    }
    public Parent getEngimonParent() { return this.engimonParent; }

    public void loadImg(){
        try {
            animation = new Animation();
            BufferedImage[] b = new BufferedImage[1];
            if (wild) {
                b[0] = ImageIO.read(new FileInputStream("resources/engimon.gif"));
            } else{
                b[0] = ImageIO.read(new FileInputStream("resources/player-eng.gif"));
            }
            animation.setDelay(500);
            animation.setFrames(b);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setWild(boolean wild){
        if (this.wild != wild){
            this.wild = wild;
            loadImg();
        }
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
        map.setTileOcc(currentPosition,map.NO_OCCUPIER);
        map.setTileOcc(t.getOrdinat(),t.getAbsis(),map.OCCUPIED);
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
            xtemp -= moveDistance;
        } else if (right){
            xtemp += moveDistance;
        } else if (up){
            ytemp -= moveDistance;
        }  else if (down){
            ytemp += moveDistance;
        }

        if (xtemp<0 || ytemp <0
                || ytemp >= map.getTilesize()*(map.getNumberOfRow()-3)
                || xtemp >= map.getTilesize()*(map.getNumberOfColumn()-3)){
            xtemp = x;
            ytemp = y;
        }

        if (wild){
            /*System.out.println("map :" + currentPosition.getOrdinat() +"," + currentPosition.getAbsis());
            System.out.println("map :" + getMapRowFromOrd(ytemp) +"," + getMapColFromAbsis(xtemp));
            System.out.println("type :" + map.getTile(getMapRowFromOrd(ytemp),
                    getMapColFromAbsis(xtemp)).getType());
            displayAllEngimonElement();
            System.out.println(isTileTypeCompatible(map.getTile(getMapRowFromOrd(ytemp),
                    getMapColFromAbsis(xtemp)).getType()));*/
            if (isTileTypeCompatible(map.getTile(getMapRowFromOrd(ytemp),
                    getMapColFromAbsis(xtemp)).getType())){
                // System.out.println("masuk");
                setTilePosition(map.getTile(getMapRowFromOrd(ytemp),getMapColFromAbsis(xtemp)));
            };
        } else {
            setTilePosition(map.getTile(getMapRowFromOrd(ytemp),getMapColFromAbsis(xtemp)));
        }
    }

    public Boolean isTileTypeCompatible(char type){
        if (engimonElement[0] == "Water"){
            return type == 's';
        } else if (engimonElement[0] == "Electric"){
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
        System.out.print("Parent                 : "); engimonParent.displayInfo();
        System.out.print("Skill                  : "); displayAllEngimonSkillName();;
        System.out.print("Element                : "); displayAllEngimonElement();System.out.println();
        System.out.println("Level                  : " + getEngimonLevel());
        System.out.println("Experience             : " + getEngimonExp());
        System.out.println("Cumulative Experience  : " + getCumulativeExp());
        System.out.println("Life                   : " + getEngimonLife());
    }

    public void setEngimonName(String name) {
        this.engimonName = name;
    }

    public void draw(Graphics2D g){
        graphIndicatingElmt();
        g.drawImage(animation.getImage(),
                    (int) x, (int) y, width, height, null);
        g.setPaint( paint );
        g.setStroke( new BasicStroke( 3 ) );
        g.drawRect( (int) x, (int) y, width, height );
        g.setStroke( new BasicStroke() );
    }

    public void update(){
        this.levelUp();
        this.updateExistence();
    }

    public void graphIndicatingElmt(){
        if (getNthEngimonElement(1) == "Water"){
            paint = new GradientPaint( (int) x, (int) y, Color.BLUE, (int) x + width, (int) y + height, Color.WHITE );
        } else if (getNthEngimonElement(1) == "Electric"){
            paint = new GradientPaint( (int) x, (int) y, Color.GRAY, (int) x + width, (int) y + height, Color.BLACK );
        } else if (getNthEngimonElement(1) == "Fire"){
            paint = new GradientPaint( (int) x, (int) y, Color.red, (int) x + width, (int) y + height, Color.orange );
        } else if (getNthEngimonElement(1) == "Ice"){
            paint = new GradientPaint( (int) x, (int) y, Color.cyan, (int) x + width, (int) y + height, Color.white );
        } else{ //ground
            paint = new GradientPaint( (int) x, (int) y, Color.green, (int) x + width, (int) y + height, Color.yellow );
        }

        if (getNumberOfElement() > 1){
            if (getNthEngimonElement(1) == "Water" && getNthEngimonElement(2) == "Ice"){
                paint = new GradientPaint( (int) x, (int) y,Color.cyan, (int) x + width, (int) y + height, Color.blue );
            } else if (getNthEngimonElement(1) == "Water" && getNthEngimonElement(2) == "Ground"){
                paint = new GradientPaint( (int) x, (int) y, Color.CYAN, (int) x + width, (int) y + height, Color.gray );
            } else if (getNthEngimonElement(1) == "Fire" && getNthEngimonElement(2) == "Electric"){
                paint = new GradientPaint( (int) x, (int) y, Color.GRAY, (int) x + width, (int) y + height, Color.RED );
            }
        }
    }
}
