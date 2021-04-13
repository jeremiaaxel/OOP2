import java.util.Vector;

public class FusionFlare extends Skill{
    
    public FusionFlare(){
        super();
        name = "Fusion Flare";
        basePower = 20;
        masteryLevel = 15;
        compatibleElements.add("Fire");
        compatibleElements.add("Electric");
    }
    
    /*public FusionFlare(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Fusion Flare",20,15,compatibleelement);
    }*/
}
