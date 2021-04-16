abstract class Engimon {
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

    public Engimon(char engimonSymbol, String engimonName,String engimonSpesies, Parent parent, Tile position){
        this.engimonSymbol = engimonSymbol;
        this.engimonName = engimonName;
        this.engimonSpesies = engimonSpesies;
        this.engimonParent = parent;
        this.currentPosition = position;
        position.setOccupier(getEngimonSymbol());
        this.engimonLevel = 1;
        this.engimonExperience = 0;
        this.engimonCumulativeExp = 0;
        this.engimonSkill = new Skill[4];
        this.engimonElement = new String[2];
        this.numberOfSkill = 0;
        this.numberOfElement = 0;
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
    public float getPower(Engimon other){
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
        if (n >= numberOfSkill || n < 0){
            throw new IndexOutOfBoundsException();
        }
        return engimonSkill[n];
    }
    public String getNthEngimonElement(int n){
        if (n >= numberOfElement || n < 0){
            throw new IndexOutOfBoundsException();
        }
        return engimonElement[n];
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
    public void setPosition(Map map, Tile t){
        map.setTileOcc(t.getOrdinat(),t.getAbsis(),t.getOccupierCode());
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
    public void addElement(String elmt) throws Exception{
        if (numberOfElement == 2){
            throw new Exception("Engimon ini sudah mencapai maksimum elemen");
        }
        engimonElement[numberOfElement] = elmt;
        numberOfElement++;
    }
    public void addExp(int exp){
        engimonExperience+= exp;
        engimonCumulativeExp += exp;
    }
    public Boolean isMaxExceed(int maxCumulativeExp){
        return engimonCumulativeExp > maxCumulativeExp;
    }
    public void levelUp(){
        if (engimonExperience >= 100){
            int levelplus = engimonExperience / 100;
            engimonLevel += levelplus;
            engimonExperience %= 100;
        }

        if (engimonLevel >= 30){
            engimonSymbol = Character.toUpperCase(engimonSymbol);
        }
    }
    public void displayAllEngimonSkill(){
        for (int i = 0; i < numberOfSkill; i++){
            System.out.println("- Skill " + (i+1));
            engimonSkill[i].displaySkillInfo();
        }
    }

    public void displayAllEngimonSkillName(){
        if (numberOfSkill > 0){
            System.out.print(engimonSkill[0].getName());
            for (int i = 0; i < numberOfSkill; i++) {
                System.out.print(", " + engimonSkill[i].getName());
            }
        } else{
            System.out.print("-");
        }
        System.out.println();
    }

    public Boolean move(Map map, char movecode,Boolean cekkompatibel){
        Tile T;
        if (movecode == 'w'){
            T = map.getTileOnTop(currentPosition);
        } else if (movecode == 'a'){
            T = map.getTileOnLeft(currentPosition);
        } else if (movecode == 's'){
            T = map.getTileBelow(currentPosition);
        } else if (movecode == 'd'){
            T = map.getTileOnRight(currentPosition);
        }else{
            return false;
        }

        if (T.getType() != '#' && T.getOccupierCode() == ' '){
            if ((cekkompatibel && this.isTileCompatible(T)) || !cekkompatibel)
            {
                map.setTileOcc(currentPosition.getOrdinat(),currentPosition.getAbsis(),' ');
                map.setTileOcc(T.getOrdinat(),T.getAbsis(),getEngimonSymbol());
                T.setOccupier(getEngimonSymbol());
                currentPosition = T;
                return true;
            }
        }
        return false;
    }

    public Boolean isTileCompatible(Tile t){
        if (engimonElement[0] == "Fire" || engimonElement[0] == "Electrical" || engimonElement[0] == "Ground"){
            return t.getType() == '-';
        }
        else if (engimonElement[0] == "Water" || engimonElement[0] == "Ice"){
            return t.getType() == 'o';
        }
        return false;
    }
}
