package Entities;

import java.util.Vector;

public class HeatWave extends Skill {

    public HeatWave(){
        super();
        name = "Heat Wave";
        basePower = 15;
        masteryLevel = 1;
        compatibleElements.add("Fire");
        compatibleElements.add("Electric");
        getImageHeatSkill();
        loadImage();
    }

    public String getImageHeatSkill() {
        if (masteryLevel == 1) {
            backgroundPath = "resources/firee_10.png";
        } else if (masteryLevel == 2) {
            backgroundPath = "resources/fire_15.png";
        }else if(masteryLevel == 3){
            backgroundPath = "resources/fire_20.png";
        }
        return backgroundPath;
    }

    /*public HeatWave(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Heat Wave ",15,10,compatibleelement);
    }*/
}
