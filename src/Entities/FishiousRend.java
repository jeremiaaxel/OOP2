package Entities;

import java.util.Vector;

public class FishiousRend extends Skill {
    
    public FishiousRend(){
        super();
        name = "Fishious Rend";
        basePower = 85;
        masteryLevel = 10;
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
        compatibleElements.add("Ground");
    }
    
    
    /*public FishiousRend(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Fishious Rend",85,10,compatibleelement);
    }*/
}
