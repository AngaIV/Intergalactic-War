package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;
import static main.GPanel.BulletList;
import main.KeyHandler;
import object.Bullets;
import object.HealthManager;

public class Player extends Entity{


    GPanel gpan;
    KeyHandler kH;
    private int lives;

    private long fTimer;
    private long fDelay;
    private boolean recovering;
    private long recoveryTime;
    HealthManager HM;
    
    
    public Player(GPanel gpan, KeyHandler kH){
        this.gpan = gpan;
        this.kH = kH;

        values();
        playerImage();
        kH.PressShoot = false;
        fTimer = System.nanoTime();
        fDelay = 300;
        recovering = false;
        recoveryTime = 0;
    }
    
    public void values(){
        xb = 672;
        x = 650;
        yb = 550;
        y = 550;
        r = 10;
        lives = 3;
        speed2 = 5;
        dir = "down";
        shoot = "No";
        
      
    }

    
    public void playerImage(){
        try{
            upDef = ImageIO.read(getClass().getResourceAsStream("/images/ShipUP.png"));
            rightDef = ImageIO.read(getClass().getResourceAsStream("/images/ShipL.png"));
            leftDef = ImageIO.read(getClass().getResourceAsStream("/images/ShipR.png"));
            downDef = ImageIO.read(getClass().getResourceAsStream("/images/ShipDown.png"));
            upHit = ImageIO.read(getClass().getResourceAsStream("/images/ShipUHit.png"));
            rightHit = ImageIO.read(getClass().getResourceAsStream("/images/ShipRHit.png"));
            leftHit = ImageIO.read(getClass().getResourceAsStream("/images/ShipLHit.png"));
            downHit = ImageIO.read(getClass().getResourceAsStream("/images/ShipDHit.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
        public int getx() {return x;}
    public int gety() {return y;}
    public int getr() {
        r =10;
        return r;}
    public boolean isRecoverig(){
        return recovering;
    }
    public int getLives(){
        return lives;
    }

    
    public void lifelost(){
        lives--;
        recovering = true;
        recoveryTime = System.nanoTime();
    }
    
    public void update(){
        
        if(kH.PressUp == true || kH.PressDown == true || kH.PressLeft == true || kH.PressRight == true || kH.PressShoot == true){
            
            if(kH.PressUp == true){
                dir = "up";
                y -= speed2;
                yb -= speed2;
            }
            else if(kH.PressDown == true){
                dir = "down";
                y += speed2;
                yb += speed2;
            }
            else if(kH.PressLeft == true){
                dir = "left";
                x -= speed2;
                xb -= speed2;
            }
            else if(kH.PressRight == true){
                dir = "right";
                x += speed2;
                xb += speed2;
            }
            counter++;
            if (counter > 16){
                if(num == 1){
                    num = 2;
                }else if(num == 2){
                    num = 1;
                }
                counter = 0;
            }
        }
    
        if (kH.PressShoot == true){ 
            long pass = (System.nanoTime() - fTimer) / 1000000;
            shoot = "Yes";
            if (pass > fDelay) {
                GPanel.BulletList.add(new Bullets(xb, yb, 270, shoot)); // Pass x and y coordinates of the spaceship
                fTimer = System.nanoTime();
            }
            
        }
        long elapsed = (System.nanoTime() - recoveryTime)/1000000;
        if (elapsed > 4000){
            recovering = false;
            recoveryTime = 0;
        }
    
    }
    

    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        if (recovering){
            switch(dir){
                case "up":
                    if (num == 1){
                        image = upHit;
                    }
                    if (num == 2){
                        image = downDef;
                    }
                    break;
                case "down":
                    if (num == 1){
                        image = downHit;
                    }
                    if (num == 2){
                        image = downDef;
                    }
                    break;
                case "right":
                    if (num == 1){
                        image = rightHit;
                    }
                    if (num == 2){
                        image = downDef;
                    }
                    break;
                case "left":
                    if (num == 1){
                        image = leftHit;
                    }
                    if (num == 2){
                        image = downDef;
                    }
                    break;
            }
            
        }else{
        
            switch(dir){
                case "up":
                    if (num == 1){
                        image = upDef;
                    }
                    if (num == 2){
                        image = downDef;
                    }
                    break;
                case "down":
                    if (num == 1){
                        image = downDef;
                    }
                    if (num == 2){
                        image = downDef;
                    }
                    break;
                case "right":
                    if (num == 1){
                        image = rightDef;
                    }
                    if (num == 2){
                        image = downDef;
                    }
                    break;
                case "left":
                    if (num == 1){
                        image = leftDef;
                    }
                    if (num == 2){
                        image = downDef;
                    }
                    break;
            }
        }
        g2.drawImage(image, x, y, 80, 80, null);
    }
    
}
