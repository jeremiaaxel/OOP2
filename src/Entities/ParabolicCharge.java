package Entities;

import java.util.Vector;

public class ParabolicCharge extends Skill {

    public ParabolicCharge(){
        super();
        name = "Parabolic Charge";
        basePower = 20;
        masteryLevel = 2;
        compatibleElements.add("Electric");
        compatibleElements.add("Fire");
        getImageChargeSkill();
        loadImage();
    }

    public String getImageChargeSkill() {
        if (masteryLevel == 1) {
            backgroundPath = "resources/charge_1.png";
        } else if (masteryLevel == 2) {
            backgroundPath = "resources/charge_2.png";
        }else if(masteryLevel == 3){
            backgroundPath = "resources/charge_3.png";
        }
        return backgroundPath;
    }
    /*public ParabolicCharge(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Parabolic Charge ",20,10,compatibleelement);
    }*/
}
