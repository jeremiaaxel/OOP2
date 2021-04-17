package Entities;

import java.util.Vector;

public class Araquanid extends Engimon{
    
    public Araquanid(String name, Parent parent, Tile position){
        super('w',name,"Araquanid",parent,position);
        super.addElement("Water");
        try {
            super.addSkill(new Liquidation());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        super.setMessageUnik("Aqua Aqua");
    }

    public float getAdvantage(Engimon other)throws Exception{
        float maxadv = -1, tempadv = -1;
    
        for (int i = 1; i <= other.getNumberOfElement(); i++)
        {
            if (other.getNthEngimonElement(i) == "Fire")
            {
                tempadv = 2;
            } else if (other.getNthEngimonElement(i) == "Water")
            {
                tempadv = 1;
            } else if (other.getNthEngimonElement(i) == "Electric")
            {
                tempadv = 0;
            } else if (other.getNthEngimonElement(i) == "Ground")
            {
                tempadv = 1;
            } else if (other.getNthEngimonElement(i) == "Ice")
            {
                tempadv = 1;
            } else{
                throw new Exception ("Invalid Engimon's Elements");
            }
            
            if (tempadv > maxadv)
            {
                maxadv = tempadv;
            }   
        }
        return maxadv;
    }
}
