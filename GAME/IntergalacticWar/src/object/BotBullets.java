package object;

import entity.Bots;
import java.awt.*;
import main.GPanel;
import entity.Entity;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BotBullets{
    
    private int x;
    private int y;
    private int r;
    public boolean shoot1;
    private BufferedImage bulletDef, bullet2, bullet3;
    
    private int type;
    private int dx;
    private int dy;
    private double rad;
    private int speed;
    public boolean t;

    public BotBullets(int x, int y, int angle,int type, boolean shoot1){
        //GET CO-ORDINATES OF BOTS
        this.x = x;
        this.y = y;
        this.type = type;
        this.shoot1 = shoot1;
        r = 10;
        t = true;
        
        speed = 10;
        rad = Math.toRadians(angle);
        dx = (int) (Math.cos(rad) * speed);
        dy = (int) Math.sin(rad) * speed;
        
        getPlayerImage();
    }
    
    //GET VISUALS FROM FILES
    public void getPlayerImage(){
        
        try{
            bulletDef = ImageIO.read(getClass().getResourceAsStream("/bullets/Type1.png"));
            bullet2 = ImageIO.read(getClass().getResourceAsStream("/bullets/Type2.png"));
            bullet3 = ImageIO.read(getClass().getResourceAsStream("/bullets/Type3.png"));
            
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
    
    //DRAW BULLET IMAGES BASED ON DIFFERENT TYPES OF BOTS
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        if(shoot1){
            if(type ==1){
                image = bulletDef;
                g2.drawImage(image,x, y, 40, 40, null);
            }
            if(type ==2){
                image = bullet2;
                 g2.drawImage(image,x, y, 40, 40, null);
            }
            if(type ==3){
                image = bullet3;
                g2.drawImage(image,x+4, y, 37, 37, null);
            }
 
        }
        //g2.drawImage(image,x, y, 40, 40, null);
        
    }
}