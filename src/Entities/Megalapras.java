package Entities;

import java.util.Vector;

public class Megalapras extends Engimon{
    
    public Megalapras(String name, Parent parent, Tile position,Map map){
        super('s',name,"Megalapras",parent,position,map);
        super.addElement("Water");
        super.addElement("Ice");
        try {
            super.addSkill(new FishiousRend());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        super.setMessageUnik("Lap Lap");
    }

    public float getAdvantage(Engimon other)throws Exception{
        float araquanidAdv = -1 , eiscueAdv = -1;
        for (int i = 1; i <= other.getNumberOfElement(); i++)
        {
            if (other.getNthEngimonElement(i) == "Fire")
            {
                araquanidAdv = 2;
                eiscueAdv = 0;
            } else if (other.getNthEngimonElement(i) == "Water")
            {
                araquanidAdv = 1;
                eiscueAdv = 1;
            } else if (other.getNthEngimonElement(i) == "Electric")
            {
                araquanidAdv = 0;
                eiscueAdv = 0.5f;
            } else if (other.getNthEngimonElement(i) == "Ground")
            {
                araquanidAdv = 1;
                eiscueAdv = 2;
            } else if (other.getNthEngimonElement(i) == "Ice")
            {
                araquanidAdv = 1;
                eiscueAdv = 1;
            } 
            else{
                throw new Exception("Invalid Engimon's Elements");
            }
        }
        if (araquanidAdv > eiscueAdv)
        {
            return araquanidAdv;
        } else{
            return eiscueAdv;
        }
    }
}
