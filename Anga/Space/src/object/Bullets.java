package object;

import java.awt.*;
import main.GPanel;

public class Bullets{
    
    private double x;
    private double y;
    private int r;
    
    private double dx;
    private double dy;
    private double rad;
    private double speed;
    
    private Color c1;
    
    public Bullets(double angle, int x, int y){
        this.x = x;
        this.y = y;
        r = 4;
        
        speed = 15;
        rad = Math.toRadians(angle);
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;
        c1 = Color.YELLOW;
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
        g2.setColor(c1);
        g2.fillOval((int)(x-r),(int) (y-r),2*r, 2*r);
    }
}