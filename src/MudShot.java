
import java.util.Vector;

public class MudShot extends Skill{

    public MudShot(){
        super();
        name = "Mud Shot";
        basePower = 9;
        masteryLevel = 15;
        compatibleElements.add("Ground");
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
    }

    /*public MudShot(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Mud Shot",9,15,compatibleelement);
    }*/
}
