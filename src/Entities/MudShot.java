package Entities;

import java.io.Serializable;
import java.util.Vector;

public class MudShot extends Skill implements Serializable {

    public MudShot(){
        super();
        name = "Mud Shot";
        basePower = 9;
        masteryLevel = 1;
        compatibleElements.add("Ground");
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
    }

    /*public MudShot(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Mud Shot",9,15,compatibleelement);
    }*/
}
