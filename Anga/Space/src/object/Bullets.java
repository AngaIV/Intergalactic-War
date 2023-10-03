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
    
    private Color c1;
    
    public Bullets(int x, int y, double angle, String shoot){
        this.x = x;
        this.y = y;
        this.shoot = shoot;
        r = 4;
        
        speed = 17;
        rad = Math.toRadians(angle);
        dx = (int) (Math.cos(rad) * speed);
        dy = (int) Math.sin(rad) * speed;
        c1 = Color.YELLOW;
        getPlayerImage();
    }
    public void getPlayerImage(){
        
        try{
            bulletDef = ImageIO.read(getClass().getResourceAsStream("/bullets/BulletDef.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
        public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getR(){
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
//        g2.setColor(c1);
//        g2.fillOval((int)(x),(int) (y-r),2*r, 2*r);
        BufferedImage image = null;
        switch(shoot){
            case "Yes":
                image = bulletDef;
        }
        g2.drawImage(image,x, y, 40, 40, null);
        
    }
}