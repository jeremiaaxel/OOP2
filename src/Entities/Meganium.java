package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Vector;

public class Meganium extends Engimon implements Serializable {
    
    public Meganium(String name, Parent parent, Tile position,Map map){
        super('n',name,"Meganium",parent,position,map);
        super.addElement("Water");
        super.addElement("Ground");
        try {
            super.addSkill(new BubbleBeam());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        super.setMessageUnik("Gan Gan");
    }

    public void loadImg(){
        try {
            animation = new Animation();
            BufferedImage[] b = new BufferedImage[1];
            b[0] = ImageIO.read(new FileInputStream("resources/meganium.gif"));
            animation.setDelay(500);
            animation.setFrames(b);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public float getAdvantage(Engimon other) throws Exception{
        float araquanidAdv = -1 , aggronAdv = -1;
        for (int i = 1; i <= other.getNumberOfElement(); i++)
        {
            if (other.getNthEngimonElement(i) == "Fire")
            {
                araquanidAdv = 2;
                aggronAdv = 1.5f;
            } else if (other.getNthEngimonElement(i) == "Water")
            {
                araquanidAdv = 1;
                aggronAdv = 1;
            } else if (other.getNthEngimonElement(i) == "Electric")
            {
                araquanidAdv = 0;
                aggronAdv = 2;
            } else if (other.getNthEngimonElement(i) == "Ground")
            {
                araquanidAdv = 1;
                aggronAdv = 1;
            } else if (other.getNthEngimonElement(i) == "Ice")
            {
                araquanidAdv = 1;
                aggronAdv = 0;
            } 
            else{
                throw new Exception("Invalid Engimon's Elements");
            }
        }
        if (araquanidAdv > aggronAdv)
        {
            return araquanidAdv;
        } else{
            return aggronAdv;
        }
    }
}
