package Entities;

import java.util.Vector;

public class TripleAxel extends Skill{
    
    public TripleAxel(){
        super();
        name = "Triple Axel";
        basePower = 20;
        masteryLevel = 3;
        compatibleElements.add("Ice");
        compatibleElements.add("Water");
        compatibleElements.add("Ground");
    }
    
    /* public TripleAxel(String skillname, int basepower, int masterylevel, Vector<String> compatibleelement){
        super("Triple Axel",20,3,compatibleelement);
    }*/
}
