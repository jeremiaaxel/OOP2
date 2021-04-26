package Entities;

import java.util.Vector;

public class MudShot extends Skill {

    public MudShot(){
        super();
        name = "Mud Shot";
        basePower = 9;
        masteryLevel = 1;
        compatibleElements.add("Ground");
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
        getImageMudSkill();
        loadImage();
    }

    public String getImageMudSkill() {
        if (masteryLevel == 1) {
            backgroundPath = "resources/mud_1.png";
        } else if (masteryLevel == 2) {
            backgroundPath = "resources/mud_2.png";
        }else if(masteryLevel == 3){
            backgroundPath = "resources/mud_3.png";
        }
        return backgroundPath;
    }

    /*public MudShot(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Mud Shot",9,15,compatibleelement);
    }*/
}