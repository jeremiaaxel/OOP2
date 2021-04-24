package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public abstract class GameState {
    protected GameStateManager gameStateManager;
    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics2D g);

    public void keyPressed(int key){
        switch (key) {
            case KeyEvent.VK_SPACE:
//                doBattle();
                break;
            case KeyEvent.VK_UP:
//                this.Player.move('w');
                break;
            case KeyEvent.VK_DOWN:
//                this.Player.move('w');
                break;
            case KeyEvent.VK_LEFT:
//                this.Player.move('w');
                break;
            case KeyEvent.VK_RIGHT:
//                this.Player.move('w');
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
//                this.Player.move('w');
                break;
            case KeyEvent.VK_DOWN:
//                this.Player.move('s');
                break;
            case KeyEvent.VK_LEFT:
//                this.Player.move('a');
                break;
            case KeyEvent.VK_RIGHT:
//                this.Player.move('d');
                break;
            default:
                break;
        }
    }
}
