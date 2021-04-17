package Game;

import GameState.GameStateManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    /* dimensions */
    public static final int WIDTH = 500;
    public static final  int HEIGHT = 296;
    public static final  int SCALE = 2;


    /* thread */
    private Thread thread;
    private boolean running;
    private final int FPS = 60;
    private final long targetTime= 1000 / FPS;

    /* image */
    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gameStateManager;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init(){
        image = new BufferedImage(WIDTH*SCALE,HEIGHT*SCALE,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        gameStateManager = new GameStateManager();
        running = true;
    }


    public void run(){
        init();

        long start, elapsed, wait;

        while (running){
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if (wait < 0) wait = 5;

            try{
                Thread.sleep(wait);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void update(){
        gameStateManager.update();
    }

    private void draw(){
        gameStateManager.draw(g);
    }

    private void drawToScreen(){
        Graphics gr = getGraphics();
        gr.drawImage(image,0,0,null);
        gr.dispose();
    }

    public void keyPressed(KeyEvent key){
        gameStateManager.keyPressed((key.getKeyCode()));
    }
    public void keyTyped(KeyEvent key){

    }
    public void keyReleased(KeyEvent key){
        gameStateManager.keyReleased(key.getKeyCode());
    }
}
