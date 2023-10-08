package object;

import entity.Bots;
import java.awt.*;
import main.GPanel;
import entity.Entity;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BossBullets{
    
    private int x;
    private int y;
    private int r;
    public boolean shoot1;
    private BufferedImage bulletDef;
    
    private int type;
    private int dx;
    private int dy;
    private double rad;
    private int speed;
    public boolean t;

    public BossBullets(int x, int y, int angle,int type, boolean shoot1){
        //GET BOSS CO-ORDIANTES
        this.x = x;
        this.y = y;
        this.type = type;
        this.shoot1 = shoot1;
        r = 15;
        t = true;
        
        speed = 10;
        rad = Math.toRadians(angle);
        dx = (int) (Math.cos(rad) * speed);
        dy = (int) Math.sin(rad) * speed;
        
        getPlayerImage();
    }
    
    //GET PLAYER VISUALS FROM FILES
    public void getPlayerImage(){
        
        try{
            bulletDef = ImageIO.read(getClass().getResourceAsStream("/bullets/PlayerPowerUP.png"));          
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //RETURN BULLET CO-ORDINATES
    public double getXX(){
        return x;
    }
    public double getYY(){
        return y;
    }
    public double getRR(){
        return r;
    }
    
    //UPDATE BULLET POSITION
    public boolean update(){
        x += dx;
        y += dy;
        if(x < -r || x > GPanel.WIDTH + r || y < -r || y > GPanel.HEIGHT + r){
            return true;
        }
        return false;
    }
    //DRAW BULLET IMAGES
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if(shoot1){
             image = bulletDef;
             g2.drawImage(image,x, y, 40, 40, null);
        }
 
    }
}