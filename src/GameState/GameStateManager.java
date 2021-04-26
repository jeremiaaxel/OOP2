package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int NEWGAME = 1;
    public static final int LOADGAME = 2;
    public static final int LOSE = 3;
    public static final int WIN = 4;

    public GameStateManager(){
        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;

        gameStates.add(new MenuState(this));
        gameStates.add(new PlayingState(this, true));
        gameStates.add(new PlayingState(this, false));
        gameStates.add(new LoseState(this));
        gameStates.add(new WinningState(this));

    }

     public synchronized void setGameStates(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update() throws Exception{
        gameStates.get(currentState).update();
    }

    public void draw(Graphics2D gr){
        gameStates.get(currentState).draw(gr);
    }
    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }
    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }
}
