package entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;

public class Bots {

    private int type;
    private int rank;
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
    private BufferedImage botDef;
    private String Thrust;

    public Bots(int type, int rank, String Thrust) {
        this.type = type;
        this.rank = rank;
        this.Thrust = Thrust;

        if (type == 1) {
            c1 = Color.BLUE;
            if (rank == 1) {
                speed = 2;
                r = 10;
                health = 1;
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
        getPlayerImage();
    }
    
    public void getPlayerImage(){
        try{
            botDef = ImageIO.read(getClass().getResourceAsStream("/BotSkins/Bot1.png"));
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
        public boolean ifDead(){
        return dead;
    }
    
    public void ifHit(){
        health--;
        if(health <= 0){
            dead = true;
        }
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
                Thrust = "Yes";
            }
            if (x > GPanel.WIDTH - r && dx > 0) {
                dx = -dx;
                Thrust = "Yes";
            }
            if (y < r && dy < 0) {
                dy = -dy;
                Thrust = "Yes";
            }
            if (y > GPanel.HEIGHT/2 - r && dy > 0) {
                dy = -dy;
                Thrust = "Yes";
            }
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(c1);
//        g2.fillOval((int)(x),(int) (y-r),2*r, 2*r);
        
        BufferedImage image = null;
        switch(Thrust){
            case "Yes":
                image = botDef;
        }
        g2.drawImage(image, (int)x, (int)y, 50, 50, null);
    }
}
