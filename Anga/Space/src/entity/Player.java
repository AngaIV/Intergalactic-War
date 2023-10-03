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

public class Player extends Entity{


    GPanel gpan;
    KeyHandler kH;
    
    private long fTimer;
    private long fDelay;
    
    public Player(GPanel gpan, KeyHandler kH){
        this.gpan = gpan;
        this.kH = kH;
        values();
        playerImage();
        kH.PressShoot = false;
        fTimer = System.nanoTime();
        fDelay = 200;
    }
    
    public void values(){
        xb = 592;
        x = 450;
        yb = 570;
        y = 450;
        speed2 = 5;
        dir = "down";
        shoot = "No";
      
    }
    public void playerImage(){
        try{
            upDef = ImageIO.read(getClass().getResourceAsStream("/images/ShipUP.png"));
            rightDef = ImageIO.read(getClass().getResourceAsStream("/images/ShipRight.png"));
            leftDef = ImageIO.read(getClass().getResourceAsStream("/images/ShipLeft.png"));
            downDef = ImageIO.read(getClass().getResourceAsStream("/images/ShipDown.png"));
            bulletDef = ImageIO.read(getClass().getResourceAsStream("/images/Bullet.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
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
        
    }
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        
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
        g2.drawImage(image, x, y, 320, 320, null);
    }

//    public int getLives() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
}
