package Entities;

import java.io.Serializable;
import java.util.Vector;

public class ParabolicCharge extends Skill implements Serializable {
    
    public ParabolicCharge(){
        super();
        name = "Parabolic Charge";
        basePower = 20;
        masteryLevel = 2;
        compatibleElements.add("Electric");
        compatibleElements.add("Fire");
    }
    
    /*public ParabolicCharge(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Parabolic Charge ",20,10,compatibleelement);
    }*/
}
