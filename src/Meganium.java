
import java.util.Vector;

public class Meganium extends Engimon{
    
    public Meganium(String name, Parent parent, Tile position){
        super('n',name,"Meganium",parent,position);
        super.addElement("Water");
        super.addElement("Ground");
        super.addSkill(new BubbleBeam());
        super.setMessageUnik("Gan Gan");
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
