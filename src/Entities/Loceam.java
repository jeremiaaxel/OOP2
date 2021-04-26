package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Vector;

public class Loceam extends Engimon implements Serializable {
    
    public Loceam(String name, Parent parent, Tile position, Map map){
        super('l',name,"Loceam",parent,position, map);
        super.addElement("Fire");
        super.addElement("Electric");
        try {
            super.addSkill(new FusionFlare());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        super.setMessageUnik("Loceee Loceee");
    }

    public void loadImg(){
        try {
            animation = new Animation();
            BufferedImage[] b = new BufferedImage[1];
            b[0] = ImageIO.read(new FileInputStream("resources/loceam.png"));
            animation.setDelay(500);
            animation.setFrames(b);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public float getAdvantage(Engimon other)throws Exception{
        float blazikenAdv = -1 , ampharosAdv = -1;
        for (int i = 1; i <= other.getNumberOfElement(); i++)
        {
            if (other.getNthEngimonElement(i) == "Fire")
            {
                blazikenAdv = 1;
                ampharosAdv = 1;
            } else if (other.getNthEngimonElement(i) == "Water")
            {
                blazikenAdv = 0;
                ampharosAdv = 2;
            } else if (other.getNthEngimonElement(i) == "Electric")
            {
                blazikenAdv = 1;
                ampharosAdv = 1;
            } else if (other.getNthEngimonElement(i) == "Ground")
            {
                blazikenAdv = 0.5f;
                ampharosAdv = 0;
            } else if (other.getNthEngimonElement(i) == "Ice")
            {
                blazikenAdv = 2;
                ampharosAdv = 1.5f;
            } 
            else{
                throw new Exception ("Invalid Engimon's Elements");
            }
        }
        if (blazikenAdv > ampharosAdv)
        {
            return blazikenAdv;
        } else{
            return ampharosAdv;
        }
    }
}
