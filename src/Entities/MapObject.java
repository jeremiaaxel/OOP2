package Entities;

import java.awt.*;

public class MapObject {

    protected Map map;
    protected double x_map;
    protected double y_map;

    // position and vector (coordinate)
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    protected double xtemp;
    protected double ytemp;

    protected double moveDistance;

    // dimensions
    protected int height;
    protected int width;

    // animation
    protected Animation animation;
    protected int currentAction;
    protected boolean facingRight;
    protected boolean facingUp;

    protected boolean right;
    protected boolean left;
    protected boolean up;
    protected boolean down;


    public MapObject(Map map){

        this.map = map;
        width = map.getTilesize();
        height = map.getTilesize();
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    public void setLeft(boolean left){ this.left = left;}
    public void setRight(boolean right){ this.right = right;}
    public void setUp(boolean up){ this.up = up;}
    public void setDown(boolean down){ this.down = down;}
    public void setWidth(int width){
        this.width = width;
    }

//    public Rectangle getRectangle(){
//        return new Rectangle((int) );
//    }










}
