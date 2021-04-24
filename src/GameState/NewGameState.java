package GameState;

import Entities.Map;
import Game.GamePanel;

import java.awt.*;
import java.io.IOException;

public class NewGameState extends GameState{

    private Map map = new Map();

    public NewGameState(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        init();
    }

    public void init(){
        try {
            String map_text = map.parse("data/map.txt");
            map = new Map(16, 21, map_text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){ }

    public void draw(Graphics2D g){
        // clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0,0, GamePanel.WIDTH*GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);

        // draw map
        map.drawMap(g);
    }

    public void keyPressed(int key){}
    public void keyReleased(int key){}

}
