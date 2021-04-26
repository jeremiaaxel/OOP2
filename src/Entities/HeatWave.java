package Entities;

import java.io.Serializable;
import java.util.Vector;

public class HeatWave extends Skill implements Serializable {
    
    public HeatWave(){
        super();
        name = "Heat Wave";
        basePower = 15;
        masteryLevel = 10;
        compatibleElements.add("Fire");
        compatibleElements.add("Electric");
    }
    
    /*public HeatWave(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Heat Wave ",15,10,compatibleelement);
    }*/
}
