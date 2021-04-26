package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class ResultState extends GameState{


    public ResultState(GameStateManager gsm){
            this.gameStateManager = gsm;
            }
    public void init(){
            // None
            }

    public void update(){
            // None
            }

    public abstract void draw(Graphics2D g);

    public void keyPressed(int k){
            switch (k){
                case KeyEvent.VK_ENTER:
                    gameStateManager.setGameStates(GameStateManager.MENUSTATE);
                    break;
                case KeyEvent.VK_SPACE:
                    gameStateManager.setGameStates(GameStateManager.MENUSTATE);
                    break;
            default:
                    break;
            }
    }

    public void keyReleased(int k){}
}
