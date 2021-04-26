package Entities;

import java.awt.*;
import java.io.Serializable;

public class MapObject implements Serializable {

    protected Map map;
    protected int x_map = -1; // absis terhadap map
    protected int y_map = -1; // ordinat terhadap map

    // position and vector (coordinate)
    protected double x;
    protected double y;

    protected double xtemp;
    protected double ytemp;

    protected double moveDistance;

    // dimensions
    protected int height;
    protected int width;

    // animation
    protected Animation animation;
    protected int currentAction;

    protected boolean right;
    protected boolean left;
    protected boolean up;
    protected boolean down;
    protected boolean idle;

    public MapObject() {
    }

    public MapObject(Map map) {

        this.map = map;
        width = map.getTilesize();
        height = map.getTilesize();
    }

    public Map getMap() {
        return map;
    }

    protected int getMapColFromAbsis(double x) {
        return ((int) (((x + width / 2) / map.getTilesize())) + 1);
    }

    protected int getMapRowFromOrd(double y) {
        return ((int) (((y + height / 2) / map.getTilesize())) + 1);
    }

    public synchronized void setPosition(double x, double y) {
        if (this.x_map != -1) {
            map.setTileOcc(this.y_map, this.x_map, map.NO_OCCUPIER);
        }
        this.x_map = ((int) (((x + width / 2) / map.getTilesize()) + 1));
        this.y_map = ((int) (((y + height / 2) / map.getTilesize()) + 1));
        this.x = x;
        this.y = y;
        this.xtemp = x;
        this.ytemp = y;
        map.setTileOcc(y_map, x_map, map.OCCUPIED);
    }

    public synchronized void setPositionByMap(int x_map, int y_map) {
        if (this.x_map != -1) {
            map.setTileOcc(this.y_map, this.x_map, map.NO_OCCUPIER);
        }
        this.x_map = x_map;
        this.y_map = y_map;
        System.out.println(y + "," + x);
        System.out.println(y_map + "," + x_map);
        this.x = (x_map - 1) * map.getTilesize();
        this.y = (y_map - 1) * map.getTilesize();
        this.xtemp = x;
        this.ytemp = y;
        map.setTileOcc(this.y_map, this.x_map, map.OCCUPIED);
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setIdle(boolean idle) {
        if (idle) {
            setLeft(false);
            setDown(false);
            setRight(false);
            setUp(false);
        }
    }

    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){this.height = height;}
}
