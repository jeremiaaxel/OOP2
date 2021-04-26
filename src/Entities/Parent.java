package Entities;

import java.awt.*;
import java.io.Serializable;

public class Parent implements Serializable {
    private String parent1name;
    private String parent1species;
    private String parent2name;
    private String parent2species;

    public Parent(){
        parent1name = "-";
        parent1species = "-";
        parent2name = "-";
        parent2species = "-";
    }

    public Parent(String name1, String spc1, String name2, String spc2){
        parent1name = name1;
        parent1species = spc1;
        parent2name = name2;
        parent2species = spc2;
    }

    public String describe() {
        String result = "";
        result.concat(parent1name + "\n");
        result.concat(parent1species + "\n");
        result.concat(parent2name + "\n");
        result.concat(parent2species + "\n");
        return result;
    }

    void displayInfo(Graphics2D g, int x, int y){
        g.drawString(String.format("%s : (Spesies %s) ",parent1name,parent1species), x, y );
        g.drawString(String.format("%s : (Spesies %s) ",parent2name,parent2species), x, y+15);
    }
}
