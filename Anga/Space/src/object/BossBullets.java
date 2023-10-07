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
    private BufferedImage bulletDef, bullet2, bullet3, bullet4;
    
    private int type;
    private int dx;
    private int dy;
    private double rad;
    private int speed;
    public boolean t;

    public BossBullets(int x, int y, int angle,int type, boolean shoot1){
        this.x = x;
        this.y = y;
        this.type = type;
        this.shoot1 = shoot1;
        r = 4;
        t = true;
        
        speed = 15;
        rad = Math.toRadians(angle);
        dx = (int) (Math.cos(rad) * speed);
        dy = (int) Math.sin(rad) * speed;
        
        getPlayerImage();
    }
    public void getPlayerImage(){
        
        try{
            bulletDef = ImageIO.read(getClass().getResourceAsStream("/bullets/PlayerPowerUP.png"));
            bullet2 = ImageIO.read(getClass().getResourceAsStream("/bullets/BackUp.png"));
            bullet3 = ImageIO.read(getClass().getResourceAsStream("/bullets/Type2.png"));
            bullet4 = ImageIO.read(getClass().getResourceAsStream("/bullets/Type3.png"));
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public double getXX(){
        return x;
    }
    public double getYY(){
        return y;
    }
    public double getRR(){
        return r;
    }
    
    public boolean update(){
        x += dx;
        y += dy;
        if(x < -r || x > GPanel.WIDTH + r || y < -r || y > GPanel.HEIGHT + r){
            return true;
        }
        return false;
    }
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        BufferedImage image1 = null;
        BufferedImage image2 = null;
        BufferedImage image3 = null;
        BufferedImage image4 = null;
        if(shoot1){
             image = bulletDef;
             g2.drawImage(image,x, y+200, 40, 40, null);
             image1 = bullet4;
             g2.drawImage(image1,x+50, y+200, 40, 40, null);
             image2 = bullet2;
             g2.drawImage(image2,x+100, y+200, 40, 40, null);
             image3 = bullet3;
             g2.drawImage(image3,x+150, y+200, 40, 40, null);
             g2.drawImage(image,x+200, y+200, 40, 40, null);
        }
 
    }
}