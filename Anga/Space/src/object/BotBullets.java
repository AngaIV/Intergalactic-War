package object;

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
    private BufferedImage bulletDef;
    
    private int dx;
    private int dy;
    private double rad;
    private int speed;
    public boolean t;

    public BotBullets(int x, int y, int angle, boolean shoot1){
        this.x = x;
        this.y = y;
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
            bulletDef = ImageIO.read(getClass().getResourceAsStream("/bullets/BulletDef2.png"));
            
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

        BufferedImage image = bulletDef;
        if(shoot1){
                image = bulletDef;
        }
        g2.drawImage(image,x, y, 40, 40, null);
        
    }
}