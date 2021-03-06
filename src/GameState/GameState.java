package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public abstract class GameState {
    protected GameStateManager gameStateManager;
    public abstract void init();
    public abstract void update() throws Exception;
    public abstract void draw(Graphics2D g);

    public abstract void keyPressed(int key);
    public abstract void keyReleased(int key);
}
