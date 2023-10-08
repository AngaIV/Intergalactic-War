package object;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;

public class PowerUps {
    
    //FIELDS
    private double x, y;
    private int radius, type;
    private BufferedImage healthPlus, TBolt, TBolt2;
    private boolean spawn;
    
    
    //Type 1 = +life, 2 = +bulletpower, 3 = ++bulletpower
    
    public PowerUps(int type, double x, double y, boolean spawn){
        this.type = type;
        this.x = x;
        this.y = y;
        this.spawn = spawn;
        radius = 20;
        getImages();
    }
    
    //GET POWERUP IMAGES FROM DIRECTORY
    public void getImages(){
        try{
            healthPlus = ImageIO.read(getClass().getResourceAsStream("/utility/Heart.png"));
            TBolt = ImageIO.read(getClass().getResourceAsStream("/utility/TBolt.png"));
            TBolt2 = ImageIO.read(getClass().getResourceAsStream("/utility/TBoltR.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //RETURN CO-ORDINATES OF POWERUP
    public double getx(){
        return x;
    }
    public double gety(){
        return y;
    }
    public double getr(){
        return radius;
    }
    public double gettype(){
        return type;
    }
    
    public boolean update(){
        y += 1;
        if(y > GPanel.HEIGHT + radius){
            return true;
        }
        return false;
    }
    
    public void draw(Graphics g2){
        BufferedImage image = healthPlus;
        BufferedImage image2 = TBolt;
        BufferedImage image3 = TBolt2;
        
        //SPAWN A POWERUP BASED ON THE TYPE OF ENEMY KILLED
        if (type == 1 && spawn){
            g2.drawImage(image, (int)x, (int)y, 30, 30, null);
        }
        if (type == 2 && spawn){
            g2.drawImage(image2, (int)x, (int)y, 30, 35, null);
        }
        if (type == 3 & spawn){
            g2.drawImage(image3, (int)x, (int)y, 30, 35, null);
        }
    }
        
}
