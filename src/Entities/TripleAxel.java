package Entities;

import java.util.Vector;

public class TripleAxel extends Skill{

    public TripleAxel(){
        super();
        name = "Triple Axel";
        basePower = 20;
        masteryLevel = 1;
        compatibleElements.add("Ice");
        compatibleElements.add("Water");
        compatibleElements.add("Ground");
        getImageTripleSkill();
        loadImage();
    }

    public String getImageTripleSkill() {
        if (masteryLevel == 1) {
            backgroundPath = "resources/axel_1.png";
        } else if (masteryLevel == 2) {
            backgroundPath = "resources/axel_2.png";
        }else if(masteryLevel == 3){
            backgroundPath = "resources/axel_3.png";
        }
        return backgroundPath;
    }
    /* public TripleAxel(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Triple Axel",20,10,compatibleelement);
    }*/
}
