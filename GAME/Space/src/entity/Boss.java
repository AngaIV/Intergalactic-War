package entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;
import object.Bullets;
import entity.Entity;
import object.BossBullets;
import object.BotBullets;

public class Boss {

    private int type;
    private int wave;
    private Color c1;
    private int health;
    private int r;
    private int speed;
    private double x;
    private double y;
    private double rad;
    private double dx;
    private double dy;
    private boolean ready;
    private boolean dead;
    private BufferedImage bossDef;
    private String Thrust;
    private long fTimer;
    private long fDelay;
    private int xb, yb;
    public boolean shoot1;

    public Boss(int type, int wave, String Thrust) {
        this.type = type;
        this.wave = wave;
        this.Thrust = Thrust;
              
        if (type == 3) {
            if (wave == 3) {
                speed = 4;
                r = 10;
                health = 50;
            }
        }
        
        x = Math.random() * GPanel.WIDTH / 2 + GPanel.WIDTH / 4;
        double agl = Math.random() * 140 + 20;
        rad = Math.toRadians(agl);
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;
        y = -r;

        ready = false;
        dead = false;
        fTimer = System.nanoTime();
        fDelay = 10000;
        getPlayerImage();
        values();
    }
    public void values(){
        speed = 10;
        xb = 592;
        yb = 570;
    }
    
    public void getPlayerImage(){
        try{
            bossDef = ImageIO.read(getClass().getResourceAsStream("/BossSkins/boss.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public double gtX(){
        return x;
    }
    public double gtY(){
        return y;
    }
    public double gtR(){
        return r;
    }
        public boolean Dead(){
        return dead;
    }
    public void Hit(){
    
        health--;
        if(health <= 0){
            dead = true;
        }
    }
    public int gtType(){
        return type;
    }
    public int gtWave(){
        return wave;
    }


    public void update() {
        if (!dead) {
            x += dx;
            y += dy;

            if (!ready) {
                if (x > r && x < GPanel.WIDTH - r && y > r && y < GPanel.HEIGHT - r) {
                    ready = true;
                }
            }
            if (x < r && dx < 0) {
                dx = -dx;
                xb += speed;
                shoot1 = true;
                Thrust = "Yes";
             
                
            }
            if (x > GPanel.WIDTH - r && dx > 0) {
                dx = -dx;
                xb -= speed;
                shoot1 = true;
                Thrust = "Yes";
               
            }
            if (y < r && dy < 0) {
                dy = -dy;
                yb += speed;
                shoot1 = true;
                Thrust = "Yes";
           
            }
            if (y > GPanel.HEIGHT/2 - r && dy > 0) {
                dy = -dy;
                yb += speed;
                shoot1 = true;
                Thrust = "Yes";
               
                
            }
            if (Thrust.equals("Yes") && !dead) {
                long pass = (System.nanoTime() - fTimer) / 1000000;
                if (pass > fDelay) {
                    GPanel.bossBull.add(new BossBullets((int) gtX(), (int) gtY(), -270,type,  shoot1));
                    fTimer = System.nanoTime();
                }
            }
        }
        
    }



    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        switch(Thrust){
            case "Yes":
                if (type == 3){
                   image = bossDef; 
                   g2.drawImage(image, (int)x, (int)y, 250, 250, null);
                }
        } 
    }
}