package Entities;

import java.util.Vector;

public class Liquidation extends Skill {
    
    public Liquidation(){
        super();
        name = "Liquidation";
        basePower = 85;
        masteryLevel = 1;
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
        compatibleElements.add("Ground");
    }
    
    /*public Liquidation(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Parabolic Charge",85,10,compatibleelement);
    }*/
}
