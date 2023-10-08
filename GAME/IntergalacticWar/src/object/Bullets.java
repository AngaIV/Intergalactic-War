package object;

import java.awt.*;
import main.GPanel;
import entity.Entity;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bullets{
    
    private int x;
    private int y;
    private int r;
    private String shoot;
    private BufferedImage bulletDef;
    
    private int dx;
    private int dy;
    private double rad;
    private int speed;
    
    
    public Bullets(int x, int y, double angle, String shoot){
        //GET CO-ORDINATES OF PLAYER AS WELL AS KEYBOARD INPUT IF SHOOTING
        this.x = x;
        this.y = y;
        this.shoot = shoot;
        r = 10;
        
        speed = 10;
        rad = Math.toRadians(angle);
        dx = (int) (Math.cos(rad) * speed);
        dy = (int) Math.sin(rad) * speed;
        getPlayerImage();
    }
    
    //GET VISUALS FROM FILES
    public void getPlayerImage(){
        
        try{
            bulletDef = ImageIO.read(getClass().getResourceAsStream("/bullets/BulletDef.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //RETURN BULLET CO-ORDINATES
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getR(){
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
    
    //DRAW BULLET IMAGE
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch(shoot){
            case "Yes":
                image = bulletDef;
        }
        g2.drawImage(image,x, y, 40, 40, null);
        
    }
}