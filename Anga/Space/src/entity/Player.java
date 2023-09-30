package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;
import main.KeyHandler;
import object.Bullets;

public class Player extends Entity{
    GPanel gpan;
    KeyHandler kH;
    
    public Player(GPanel gpan, KeyHandler kH){
    this.gpan = gpan;
    this.kH = kH;
    values();
    playerImage();
    }
    
    public void values(){
        x = 100;
        y = 100;
        speed2 = 5;
        dir = "down";
        projectile = new Bullets(gpan);
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
        
        if(kH.PressUp == true || kH.PressDown == true || kH.PressLeft == true || kH.PressRight == true || kH.PressShoot == true ){
            
            if(kH.PressUp == true){
                dir = "up";
                y -= speed2;
            }
            else if(kH.PressDown == true){
                dir = "down";
                y += speed2;
            }
            else if(kH.PressLeft == true){
                dir = "left";
                x -= speed2;
            }
            else if(kH.PressRight == true){
                dir = "right";
                x += speed2;
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
            projectile.set(dir, x, y);
            gpan.projectileList.add(projectile);
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
        g2.drawImage(image, x, y, 400, 400, null);
    }
    
}
