package Entities;

import java.io.Serializable;
import java.util.Vector;

public class Skill implements Serializable {
    protected String name;
    protected int basePower;
    protected int masteryLevel;
    protected Vector<String> compatibleElements;

    public Skill()
    {
        name = "";
        basePower = 0;
        masteryLevel = 0;
        compatibleElements = new Vector<String>();
    }
    
    public Skill(String _name, int _basepwr, int _masterylvl, Vector<String> _elements)
    {
        name = _name;
        basePower = _basepwr;
        masteryLevel = _masterylvl;
        compatibleElements = new Vector<String>(_elements);
    }
    
    // getter
    public String getName(){
        return name;
    }
    public int getBasePower(){
        return basePower;
    }
    public int getMasteryLevel(){
        return masteryLevel;
    }
    
    public String getNthCompatibleElement(int n) throws Exception{
        if (n > compatibleElements.size() || n < 1){
            throw new Exception("index out of range");
        }
        return compatibleElements.get(n-1);
    }
    // setter 
    public void setName(String _nama){
        name = _nama;
    }
    public void setBasePower(int _basePower){
        basePower = _basePower;
    }
    public void setMasteryLevel(int _masteryLevel){
        masteryLevel = _masteryLevel;
    }
    public void addCompatibleElement(String el){
        compatibleElements.add(el);
    }
    
    public int countCompatibleElement(){
        return compatibleElements.size();
    }
    
    public void displaySkillInfo() throws Exception{
        System.out.println("Nama               : "+getName());
        System.out.println("Base power         : "+getBasePower());
        System.out.println("Mastery level      : "+getMasteryLevel());
        System.out.println("Compatible Element : ");
        for (int i = 1; i <= countCompatibleElement(); i++)
        {
            System.out.println(" - " + getNthCompatibleElement(i));
        }
    }

    public String describe() throws Exception {
        String result = "";
        result.concat(this.getName() + "\n");
        result.concat(this.getBasePower() + "\n");
        result.concat(this.getMasteryLevel() + "\n");
        for (int i = 0; i < this.compatibleElements.size(); i++) {
            result.concat("SKILLELMT\n");
            result.concat(this.getNthCompatibleElement(i) + "\n");
        }
        return result;
    }
}

/*Skill("Mud Shot",9,15,skillCompatibleElement))
Skill("Parabolic Charge",20,10,skillCompatibleElement))
Skill("Liquidation",85,10,skillCompatibleElement));
Skill("Heat Wave",15,10,skillCompatibleElement)
Skill("Triple Axel",20,10,skillCompatibleElement)
Skill("Fusion Flare",20,15,skillCompatibleElement)
Skill("Fishious Rend",85,10,skillCompatibleElement)
Skill("Bubble Beam",65,20,skillCompatibleElement)
*/