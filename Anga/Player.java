import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {

    // Fields (including image variables)
    private int x_int;
    private int y_int;
    private int radius;

    private int dirX;
    private int dirY;
    private int speedofplayer;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private int lives;

    // Image variables
    private BufferedImage upDef;
    private BufferedImage upLeft;
    private BufferedImage upRight;
    private BufferedImage rightDef;
    private BufferedImage leftDef;
    private BufferedImage downDef;

    // Constructor
    public Player() {
        
        try {
            upDef = ImageIO.read(getClass().getResourceAsStream(".//res//ShipUP.png"));
            upLeft = ImageIO.read(getClass().getResourceAsStream(".//res//ShipLU.png"));
            upRight = ImageIO.read(getClass().getResourceAsStream(".//res//ShipRU.png"));
            rightDef = ImageIO.read(getClass().getResourceAsStream(".//res//ShipRight.png"));
            leftDef = ImageIO.read(getClass().getResourceAsStream(".//res//ShipLeft.png"));
            downDef = ImageIO.read(getClass().getResourceAsStream(".//res//ShipDown.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        x_int = Panel.WIDTH / 2;
        y_int = Panel.HEIGHT / 2;
        radius = 5;

        dirX = 0;
        dirY = 0;
        speedofplayer = 5;
        lives = 5;
    }
    
    public void setLeft(boolean b) {left = b;}
    public void setRight(boolean b) {right = b;}
    public void setUp(boolean b) {up = b;}
    public void setDown(boolean b) {down = b;}
    
    public void update() {
        if(left) {
            
            dirX = -speedofplayer;
        }
        if (right) {
            dirX = speedofplayer;
        }
        if (up){
            dirY = -speedofplayer;
        }
        if(down) {
            dirY = speedofplayer;
        }
        x_int += dirX;
        y_int += dirY;
        if(x_int < radius) x_int = radius;
        if(y_int < radius) y_int = radius;
        if(x_int > Panel.WIDTH - radius) x_int = Panel.WIDTH - radius;
        if(x_int > Panel.HEIGHT - radius) x_int = Panel.HEIGHT - radius;
        dirX = 0;
        dirY= 0;
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;

        if (left) {
            image = leftDef;
        } else if (right) {
            image = rightDef;
        } else if (up) {
            image = upDef;
        } else if (down) {
            image = downDef;
        }

        if (image != null) {
            g.drawImage(image, x_int, y_int, null);
        }
    }
}
