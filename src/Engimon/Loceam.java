import java.util.Vector;

public class Loceam extends Engimon{
    
    public Loceam(String name, Parent parent, Tile position){
        super('l',name,"Loceam",parent,position);
        super.addElement("Fire");
        super.addElement("Electric");
        super.addSkill(new FusionFlare());
        super.setMessageUnik("Loceee Loceee");
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
