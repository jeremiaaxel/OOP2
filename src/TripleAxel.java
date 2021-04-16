
import java.util.Vector;

public class TripleAxel extends Skill{
    
    public TripleAxel(){
        super();
        name = "Triple Axel";
        basePower = 20;
        masteryLevel = 10;
        compatibleElements.add("Ice");
        compatibleElements.add("Water");
        compatibleElements.add("Ground");
    }
    
    /* public TripleAxel(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Triple Axel",20,10,compatibleelement);
    }*/
}
