import java.util.Vector;

public class BubbleBeam extends Skill{

    public BubbleBeam(){
        super();
        name = "Bubble Beam";
        basePower = 65;
        masteryLevel = 20;
        compatibleElements.add("Water");
        compatibleElements.add("Ice");
        compatibleElements.add("Ground");
    }

    /*public BubbleBeam(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Bubble Beam",65,20,compatibleelement);
    }*/
}
