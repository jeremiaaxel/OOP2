package Entities;

import java.util.Vector;

public class Ampharos extends Engimon{
    
    public Ampharos(String name, Parent parent, Tile position, Map map){
        super('e',name,"Ampharos",parent,position,map);
        super.addElement("Electric");
        try {
            super.addSkill(new ParabolicCharge());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        super.setMessageUnik("Ampha Ampha");
    }

    public float getAdvantage(Engimon other) throws Exception{
        float maxadv = -1, tempadv = -1;
    
        for (int i = 1; i <= other.getNumberOfElement(); i++)
        {
            if (other.getNthEngimonElement(i) == "Fire")
            {
                tempadv = 1;
            } else if (other.getNthEngimonElement(i) == "Water")
            {
                tempadv = 2;
            } else if (other.getNthEngimonElement(i) == "Electric")
            {
                tempadv = 1;
            } else if (other.getNthEngimonElement(i) == "Ground")
            {
                tempadv = 0;
            } else if (other.getNthEngimonElement(i) == "Ice")
            {
                tempadv = 1.5f;
            } else{
                throw new Exception("Invalid Engimon's Elements");
            }
            
            if (tempadv > maxadv)
            {
                maxadv = tempadv;
            }   
        }
        return maxadv;
    }
}
