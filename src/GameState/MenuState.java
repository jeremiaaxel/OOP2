package GameState;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class MenuState extends GameState{

    private BufferedImage backgroundImage;
    private int userChoice = 0;
    private final String[] menu = {
            "New Game",
            "Load Game",
            "Help",
            "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font defaultFont;

    public MenuState(GameStateManager gsm){
        this.gameStateManager = gsm;

        try{
            String backgroundPath = "resources/background.png";
            backgroundImage = ImageIO.read(new FileInputStream(backgroundPath));
            titleColor = new Color(128,0,0);
            titleFont = new Font("Century Gothic",Font.PLAIN,60);
            defaultFont = new Font("Arial",Font.PLAIN, 26);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(){}

    public void update(){}

    public void draw(Graphics2D gr){
        // add background-image
        gr.drawImage(backgroundImage, 0,0,500*2,296*2,null);

        // set title
//        drawCenteredString(gr,"Engimon Factory",new Rectangle(),titleFont,titleColor);
        gr.setColor(titleColor);
        gr.setFont(titleFont);
        gr.drawString("Engimon Factory",230,130);

        // display menu options
        gr.setFont(defaultFont);
        for (int i = 0; i < menu.length; i++){
            if (i == userChoice){
                gr.setColor(Color.RED);
            } else {
                gr.setColor(Color.BLACK);
            }
            gr.drawString(menu[i],420,220 + i*40);
        }
    }

    private void setUserChoice(){
        switch (userChoice){
            case 0 : //newgame
                gameStateManager.setGameStates(GameStateManager.NEWGAME);
                break;
            case 1 : // load game
                break;
            case 2 : // help
                break;
            case 3 : //quit
                System.exit(0);
                break;
            default :
                break;
        }
    }
    public void keyPressed(int k){
        switch (k){
            case KeyEvent.VK_ENTER:
                setUserChoice();
                break;
            case KeyEvent.VK_UP:
                if (userChoice != 0) {
                    userChoice--;
                } else{
                    userChoice = menu.length-1;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (userChoice != 3){
                    userChoice++;
                } else{
                    userChoice = 0;
                }
                break;
            default:
                break;
        }
    }

    public void keyReleased(int k){

    }


}
