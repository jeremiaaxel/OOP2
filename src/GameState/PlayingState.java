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

    private final int panelSize = 300;

    public PlayingState(GameStateManager gameStateManager, boolean newgame){
        this.gameStateManager = gameStateManager;
        this.newgame = newgame;
        init();
    }

    public void init(){
        if (newgame){
            try {
                String map_text = map.parse("data/map.txt");
                map = new Map(16, 20, map_text);
                map.setTileSize((GamePanel.WIDTH*GamePanel.SCALE - panelSize)/ (map.getNumberOfColumn()-2));
                player = new Player("New Player", map); // NANTI MINTA NAMA DULU
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            load();
        }
    }

    public void load(){
    }

    public void update(){
        player.update();
    }

    public void keyPressed(int key){
        switch (key) {
            case KeyEvent.VK_SPACE:
//                doBattle();
                break;
            case KeyEvent.VK_UP:
                this.player.setUp(true);
                break;
            case KeyEvent.VK_DOWN:
                this.player.setDown(true);
                break;
            case KeyEvent.VK_LEFT:
                this.player.setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                this.player.setRight(true);
                break;
            case KeyEvent.VK_S:
//                savegame();
                break;
            case KeyEvent.VK_1:
//                commandPlayer1
                break;
            case KeyEvent.VK_2:
//                commandPlayer12
                break;
            case KeyEvent.VK_3:
//                commandPlayer3
                break;
            case KeyEvent.VK_4:
//                commandPlayer4
                break;
            case KeyEvent.VK_5:
//                commandPlayer5
                break;
            case KeyEvent.VK_ESCAPE:
//                commandQuit
            default:
                break;
        }
    }

    public void keyReleased(int key){
        switch (key) {
            case KeyEvent.VK_UP:
                this.player.setUp(false);
                break;
            case KeyEvent.VK_DOWN:
                this.player.setDown(false);
                break;
            case KeyEvent.VK_LEFT:
                this.player.setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                this.player.setRight(false);
                break;
            default:
                break;
        }
    }

    public void draw(Graphics2D g){
        // clear screen
        g.setColor(Color.BLACK);
//        g.fillRect(0,0, GamePanel.WIDTH*GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);

        // draw map
        map.drawMap(g);
        player.draw(g);
    }
}
