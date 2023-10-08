package entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;
import object.Bullets;
import entity.Entity;
import object.BotBullets;

public class Bots {

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
    private BufferedImage botDef, bot2, bot3;
    private String Thrust;
    private long fTimer;
    private long fDelay;
    private int xb, yb;
    public boolean shoot1;

    public Bots(int type, int wave, String Thrust) {
        this.type = type;
        this.wave = wave;
        this.Thrust = Thrust;
        
        //UPDATES BOTS PER WAVE & LEVEL
        if (type == 1) {
            if (wave == 1) {
                speed = 3;
                r = 10;
                health = 1;
            }
            if (wave == 2) {
                speed = 3;
                r = 10;
                health = 2;
            }
            if (wave == 3) {
                speed = 3;
                r = 10;
                health = 3;
            }
            if (wave == 4) {
                speed = 4;
                r = 10;
                health = 4;
            }
            
        }
        
        
        if (type == 2) {
            if (wave == 1) {
                speed = 4;
                r = 10;
                health = 4;
            }
            if (wave == 2) {
                speed = 4;
                r = 10;
                health = 5;
            }
            if (wave == 3) {
                speed = 4;
                r = 10;
                health = 5;
            }
            if (wave == 4) {
                speed = 4;
                r = 10;
                health = 6;
            }
        }
        
        
        if (type == 3) {
            if (wave == 1) {
                speed = 5;
                r = 10;
                health = 6;
            }
            if (wave == 2) {
                speed = 5;
                r = 10;
                health = 6;
            }
            if (wave == 3) {
                speed = 5;
                r = 10;
                health = 6;
            }
            if (wave == 4) {
                speed = 5;
                r = 10;
                health = 10;
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
        fDelay = 3000;
        getPlayerImage();
        values();
    }
    public void values(){
        //DEFAULT BOT VALUES
        speed = 2;
        xb = 592;
        yb = 570;
    }
    
    //GET BOT IMAGES
    public void getPlayerImage(){
        try{
            botDef = ImageIO.read(getClass().getResourceAsStream("/BotSkins/Bot1.png"));
            bot2 =  ImageIO.read(getClass().getResourceAsStream("/BotSkins/Wave2.png"));
            bot3 =  ImageIO.read(getClass().getResourceAsStream("/BotSkins/Bot3.png"));
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
    public boolean ifDead(){ //CALLED IF BOTS ARE DEAD
        return dead;
    }
    public void ifHit(){ //BOTS LOSE HEALTH WHEN SHOT
        health--;
        if(health <= 0){
            dead = true;
        }
    }
    public int getType(){ //RETURN TYPE OF BOT
        return type;
    }
    public int getWave(){ //RETURN WAVE NUMBER
        return wave;
    }


    public void update() {
        //UPDATE BOT POSITION
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
            //BOTS SHOOT BULLETS
            if (Thrust.equals("Yes") && !dead) {
                long pass = (System.nanoTime() - fTimer) / 1000000;
                if (pass > fDelay) {
                    GPanel.BotBulletList.add(new BotBullets((int) getX(), (int) getY(), -270,type,  shoot1));
                    fTimer = System.nanoTime();
                }
            }
        }
        
    }



    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        switch(Thrust){
            case "Yes":
                if (type == 1){
                   image = botDef; 
                }
                if (type == 2){
                    image = bot2;
                }
                if (type == 3){
                    image = bot3;
                }
        }
        g2.drawImage(image, (int)x, (int)y, 60, 50, null);
    }
}
