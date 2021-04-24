package GameState;

import Entities.Map;
import Entities.Player;
import Game.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class PlayingState extends GameState{

    private Map map = new Map();
    private Player player;
    private boolean newgame;

    public PlayingState(GameStateManager gameStateManager, boolean newgame){
        this.gameStateManager = gameStateManager;
        this.newgame = newgame;
        init();
    }

    public void init(){
        if (newgame){
            try {
                String map_text = map.parse("data/map.txt");
                map = new Map(16, 21, map_text);

//            player = new Player("New Player", map); // NANTI MINTA NAMA DULU
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            load();
        }
    }

    public void load(){

    }

    public void update(){ }

    public void draw(Graphics2D g){
        // clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0,0, GamePanel.WIDTH*GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);

        // draw map
        map.drawMap(g);
    }
}
