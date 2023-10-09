package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;
import static main.GPanel.BulletList;
import main.KeyHandler;
import main.UI;
import object.Bullets;
import object.HealthManager;

public class Player extends Entity{

    UI ui;
    GPanel gpan;
    KeyHandler kH;
    public int lives;
   
    //SCORE
    public int score;
    
    //DELAY BETWEEN EACH BULLET FIRED
    private long fTimer;
    private long fDelay;
    
    
    //ENSURES PLAYER INVINCIBILTY WHEN SHOT
    private boolean recovering;
    private long recoveryTime;
    
    //UPDATES HEALTH VISUAL IF PLAYER LOSES OR GAIN A LIVES
    HealthManager HM;
    
    //POWERUP SYSTEM
    public int bulletLevel;
    public int power;
    public int[] powerneeded = {
        1, 2, 3, 4, 5
    };
    private long PUTimer;

    
    public Player(GPanel gpan, KeyHandler kH, UI ui){
        this.gpan = gpan;
        this.kH = kH;
        this.ui = ui;

        values();
        playerImage();
        kH.PressShoot = false;
        fTimer = System.nanoTime();
        fDelay = 200;
        recovering = false;
        recoveryTime = 0;

    }
    
    public void values(){
        //DEFAULT PLAYER VALUES
        xb = 672;
        x = 650;
        score = 0;
        yb = 550;
        y = 550;
        r = 10;
        lives = 3;
        speed2 = 6;
        dir = "down";
        shoot = "No";
        
        
      
    }
    
    public void increasedBullets(int i){
        power += i;
        if (bulletLevel >= 5){

        }else{
            if(power >= powerneeded[bulletLevel] && powerneeded[bulletLevel] < 5){ //CHECK IF PLAYER HAVE ENOUGH POWER TO GET TO THE NEXT BULLET LEVEL
            
            System.out.print(powerneeded[bulletLevel]);
            power -= powerneeded[bulletLevel];
            bulletLevel++;
        }
        }
        
    }
    public int getBLevel(){
        return bulletLevel;
    }
    public int getPower(){
       
        return power;
    }
    public int getReqPower(){
        
        return powerneeded[bulletLevel];
    }

    //GET PLAYPER IMAGES
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
    //RETURN PLAYER POSITION
    public int getx() {return x;}
    public int gety() {return y;}
    public int getr() {
        r =10;
        return r;}
    public boolean isRecoverig(){ //CHECK IF PLAYER WAS SHOT
        return recovering;
    }
    public int getLives(){
        return lives;
    }
    public int getScore(){
        return score;
    }
    public void addScore(int s){//UPDATE PLAYER SCORE
        score += s;
    }
    
    public void addLife() {
        if (lives >= 5){
        //MAX LIVES ARE == 5
        }else{
           lives++; 
        }
        
    }
    
    public void lifelost(){
        
        //LOSE LIFE WHEN SHOT
        lives--;
        recovering = true;
        recoveryTime = System.nanoTime();
    }
    
    public void update(){
        //MOVE PLAYER POSITION ON COMMAND
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
        //SHOOT BULLETS ON COMMAND
        if (kH.PressShoot == true){ 
            long pass = (System.nanoTime() - fTimer) / 1000000;
            long endPowerUp = (System.nanoTime() - PUTimer) / 1000000;
            shoot = "Yes";
            if (pass > fDelay) {
                fTimer = System.nanoTime();
                
                if(bulletLevel < 2){
                    GPanel.BulletList.add(new Bullets(xb, yb, 270, shoot)); 
                }
                else if (bulletLevel < 4) {
                    GPanel.BulletList.add(new Bullets(xb + 4, yb, 270, shoot)); // SHOOT 2 BULLETS PER SHOT WHEN 2 POWER UP BARS ARE FULL
                    GPanel.BulletList.add(new Bullets(xb - 4, yb, 270, shoot));
                }
                else {
                    GPanel.BulletList.add(new Bullets(xb + 20, yb+5, 270, shoot)); // SHOOT 3 BULLETS PER SHOT WHEN 5 POWER UP BARS ARE FULL
                    GPanel.BulletList.add(new Bullets(xb, yb, 270, shoot)); 
                    GPanel.BulletList.add(new Bullets(xb - 20, yb+5, 270, shoot)); 
 
                }
                
                
            }
            
        }
        //INVINCIBILITY OF PLAYER WHEN SHOT
        long elapsed = (System.nanoTime() - recoveryTime)/1000000;
        if (elapsed > 4000){
            recovering = false;
            recoveryTime = 0;
        }
    
    }
    

    public void draw(Graphics2D g2){
        //DRAW PLAYER IMAGE
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
