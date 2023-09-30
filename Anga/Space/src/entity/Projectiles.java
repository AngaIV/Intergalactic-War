package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;
import main.KeyHandler;

public class Projectiles extends Entity{
    
    GPanel gpan;
    KeyHandler kH;
    
    public Projectiles(GPanel gpan){
        this.gpan = gpan;
        
    }
    
    public void set(String dir, int x, int y){
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.life = this.maxlife;
        this.speedbull = speedbull;
    }
    public void update(){
        switch(dir){
            case "up": y -= speedbull; break;
            case "down": y += speedbull; break;
            case "left": x -= speedbull; break;
            case "right": y += speedbull; break;  
        }
        
        counter++;
        if (counter > 8){
            if(num == 1){
                num = 2;
            }else if(num == 2){
                num = 1;
            }
            counter = 0;   
        }
    }
}
    

    
