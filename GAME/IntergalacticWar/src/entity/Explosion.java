
package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Explosion {
    
    //FIELDS
    private double x;
    private double y;
    private double r;
    private int max;
    private BufferedImage explo;
    private boolean explode;
    
    public Explosion(double x,double y, int r, int max, boolean explode){
        this.x = x;
        this.y = y;
        this.r = r;
        this.max = max;
        this.explode = explode;
        getImage();
    }
    //GET EXPLOSION IMAGE
    public void getImage(){
        try{
            explo = ImageIO.read(getClass().getResourceAsStream("/utility/Explosion.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean update(){
        r++;
        if (r >= max){
            return true;
        }
        return false;
    }
    //DRAW PLAYER EXPLOSION
     public void draw(Graphics g2){
        BufferedImage image = explo;
        if(explode){
            g2.drawImage(image,(int)x,(int) y, 100,100,null);
        }
    }
}
