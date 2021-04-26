package Entities;

import java.util.Vector;

public class BubbleBeam extends Skill {

    public BubbleBeam(){
        super();
        name = "Bubble Beam";
        basePower = 65;
        masteryLevel = 2;
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
        compatibleElements.add("Ground");
        getImageBubbleSkill();
        loadImage();
    }

    public String getImageBubbleSkill() {
        if (masteryLevel == 1) {
            backgroundPath = "resources/bubble_10.png";
        } else if (masteryLevel == 2) {
            backgroundPath = "resources/bubble_15.png";
        }else if(masteryLevel == 3){
            backgroundPath = "resources/bubble_20.png";
        }
        return backgroundPath;
    }


    /*public BubbleBeam(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Bubble Beam",65,20,compatibleelement);
    }*/
}
