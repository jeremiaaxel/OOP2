package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean played;

    public Animation(){
        played = false;
    }

    public void setFrames(BufferedImage[] f){
        this.frames = f;
        this.currentFrame = 0;
        this.startTime = System.nanoTime();
        this.played = false;
    }

    public void setDelay(long delay){ this.delay = delay; }
    public void setCurrentFrameFrame(int N){ this.currentFrame = N; }
    public void update(){
        if (delay != -1){
            long elapsed = (System.nanoTime() - startTime)/ 1000000;
            if (elapsed > delay){
                currentFrame+=1;
                startTime = System.nanoTime();
            }
            if (currentFrame == frames.length){
                currentFrame = 0;
                played = false;
            }
        }
    }

    public int getCurrentFrame(){ return currentFrame; }
    public BufferedImage getImage() { return frames[currentFrame]; }
    private boolean hasPlayed(){ return played; }

}
