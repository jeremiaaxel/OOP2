package Entities;

import java.util.Vector;

public class FusionFlare extends Skill {

    public FusionFlare(){
        super();
        name = "Fusion Flare";
        basePower = 20;
        masteryLevel = 2;
        compatibleElements.add("Fire");
        compatibleElements.add("Electric");
        getImageFushionSkill();
        loadImage();
    }

    public String getImageFushionSkill() {
        if (masteryLevel == 1) {
            backgroundPath = "resources/fushion_1.png";
        } else if (masteryLevel == 2) {
            backgroundPath = "resources/fushion_2.png";
        }else if(masteryLevel == 3){
            backgroundPath = "resources/fushion_3.png";
        }
        return backgroundPath;
    }

    /*public FusionFlare(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Fusion Flare",20,15,compatibleelement);
    }*/
}
