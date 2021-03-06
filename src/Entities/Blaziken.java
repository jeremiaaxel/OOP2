package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Vector;

public class Blaziken extends Engimon implements Serializable {
    
    public Blaziken(String name, Parent parent, Tile position, Map map){
        super('f',name,"Blaziken",parent,position,map);
        super.addElement("Fire");
        try {
            super.addSkill(new HeatWave());
        } catch (Exception e){
            System.out.println("Err : " + e.getMessage());
        }
        super.setMessageUnik("Blazz Blazz");
    }

    public void loadImg(){
        try {
            animation = new Animation();
            BufferedImage[] b = new BufferedImage[1];
            b[0] = ImageIO.read(new FileInputStream("resources/blaziken.gif"));
            animation.setDelay(500);
            animation.setFrames(b);
        } catch (Exception e){
            e.printStackTrace();
        }
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
                tempadv = 0;
            } else if (other.getNthEngimonElement(i) == "Electric")
            {
                tempadv = 1;
            } else if (other.getNthEngimonElement(i) == "Ground")
            {
                tempadv = 0.5f;
            } else if (other.getNthEngimonElement(i) == "Ice")
            {
                tempadv = 2;
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
