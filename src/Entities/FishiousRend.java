package Entities;

import java.util.Vector;

public class FishiousRend extends Skill {

    public FishiousRend(){
        super();
        name = "Fishious Rend";
        basePower = 85;
        masteryLevel = 3;
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
        compatibleElements.add("Ground");
        getImageFishSkill();
        loadImage();
    }

    public String getImageFishSkill() {
        if (masteryLevel == 1) {
            backgroundPath = "resources/fish_1.png";
        } else if (masteryLevel == 2) {
            backgroundPath = "resources/fish_2.png";
        }else if(masteryLevel == 3){
            backgroundPath = "resources/fish_3.png";
        }
        return backgroundPath;
    }


    /*public FishiousRend(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Fishious Rend",85,10,compatibleelement);
    }*/
}
