
package object;

import entity.Player;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;

public class HealthManager {
    
    private GPanel gp;
    private Health[] h;
    private Player player; // Initialize or pass a Player object
    private BufferedImage heartDef;
    private int lives;
    
    public HealthManager(GPanel gp, Player player) {
        this.player = player;
        this.gp = gp;
  // Initialize the Player object
        //lives = player.getLives();
        h = new Health[3];
        getImage();
    }

    public void getImage() {
        try {
            h[0] = new Health();
            h[0].image = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            
            h[1] = new Health();
            h[1].image = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            
            h[2] = new Health();
            h[2].image = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        lives = player.getLives();
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
