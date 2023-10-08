
package object;

import entity.Player;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;

public class HealthManager {
    
    //INITIALIZE CLASSES AND OBJECTS
    private GPanel gp;
    private Health[] h;
    private Player player; 
    private BufferedImage heartDef;
    private int lives;
    
    public HealthManager(GPanel gp, Player player) {
        this.player = player;
        this.gp = gp;
        h = new Health[5];
        getImage();
    }
    
    //GET VISUALS FROM FILES
    public void getImage() {
        try {
            h[0] = new Health();
            h[0].image = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            
            h[1] = new Health();
            h[1].image = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            
            h[2] = new Health();
            h[2].image = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            
            h[3] = new Health();
            h[3].image = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            
            h[4] = new Health();
            h[4].image = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //DISPLAY VISUALS BASED ON PLAYER LIVES
    public void draw(Graphics2D g2) {
        lives = player.getLives();
        if (lives == 5) {
            g2.drawImage(h[0].image, 0, 10, 35, 35, null);
            g2.drawImage(h[1].image, 25, 10, 35, 35, null);
            g2.drawImage(h[2].image, 50, 10, 35, 35, null);
            g2.drawImage(h[3].image, 75, 10, 35, 35, null);
            g2.drawImage(h[4].image, 100, 10, 35, 35, null);
        }
        if (lives == 4) {
            g2.drawImage(h[0].image, 0, 10, 35, 35, null);
            g2.drawImage(h[1].image, 25, 10, 35, 35, null);
            g2.drawImage(h[2].image, 50, 10, 35, 35, null);
            g2.drawImage(h[3].image, 75, 10, 35, 35, null);
        }
        
        if (lives == 3) {
            g2.drawImage(h[0].image, 0, 10, 35, 35, null);
            g2.drawImage(h[1].image, 25, 10, 35, 35, null);
            g2.drawImage(h[2].image, 50, 10, 35, 35, null);
        }
        if (lives == 2) {
            g2.drawImage(h[0].image, 0, 10, 35, 35, null);
            g2.drawImage(h[1].image, 25, 10, 35, 35, null);
        }
        if (lives == 1) {
            g2.drawImage(h[0].image, 0, 10, 35, 35, null);
        }
    }
}
