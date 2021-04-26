package GameState;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;


public class LoseState extends ResultState{

    private Color titleColor;
    private Font font;

    public LoseState(GameStateManager gsm){
        super(gsm);
    }

    public void draw(Graphics2D g){
        g.setColor(Color.RED);
        g.setFont(new Font("MicrosoftYaHei", Font.BOLD, 35));
        g.fillRect(0, 0, GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);
        g.drawString("You're dead, man!", 600, 380);
    }
}