package Entities;

import java.awt.*;

public class MapObject {

    protected Map map;
    protected int x_map;
    protected int y_map;

    // position and vector (coordinate)
    protected int x;
    protected int y;
    protected double dx;
    protected double dy;

    // dimensions
    protected int height;
    protected int width;

    // animation
    protected Animation animation;
    protected int currentAction;
    protected boolean facingRight;
    protected boolean facingUp;


    public MapObject(Map map){

        this.map = map;
    }

//    public Rectangle getRectangle(){
//        return new Rectangle((int) );
//    }










}
