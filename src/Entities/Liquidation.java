package Entities;

import java.util.Vector;

public class Liquidation extends Skill {

    public Liquidation(){
        super();
        name = "Parabolic Charge";
        basePower = 85;
        masteryLevel = 1;
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
        compatibleElements.add("Ground");
        getImageLiquidSkill();
        loadImage();
    }

    public String getImageLiquidSkill() {
        if (masteryLevel == 1) {
            backgroundPath = "resources/liquid_1.png";
        } else if (masteryLevel == 2) {
            backgroundPath = "resources/liquid_2.png";
        }else if(masteryLevel == 3){
            backgroundPath = "resources/liquid_3.png";
        }
        return backgroundPath;
    }

    /*public Liquidation(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Parabolic Charge",85,10,compatibleelement);
    }*/
}
