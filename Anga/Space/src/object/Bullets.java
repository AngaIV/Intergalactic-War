package object;

import entity.Projectiles;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;

public class Bullets extends Projectiles{
    GPanel gpan;
    public Bullets(GPanel gpan) {
        super(gpan);
        this.gpan = gpan;
        shot = "Bullet";
        speedbull = 15;
        life = maxlife;
        attack = 2;
        //alive = false;
        playerImage();
    }

    public void playerImage() {
        try{
            bulletDef = ImageIO.read(getClass().getResourceAsStream("/images/Bullet.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    
}
