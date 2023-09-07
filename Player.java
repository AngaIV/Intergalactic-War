<<<<<<< HEAD
import java.awt.*;

public class Player {
    
    //Fields
    private int x_int;
    private int y_int;
    private int radius;
    
    private int dirX;
    private int dirY;
    private int speedofplayer;
    
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    
    private int lives;
    
    //LETS CONSTRUUCTTTT!!!!
    public Player() {
    x_int = Panel.WIDTH /2;
    y_int = Panel.HEIGHT /2;
    radius = 5;
    
    dirX =0;
    dirY = 0;
    speedofplayer = 5;
    lives = 5;
    }
    
    public void update() {
        if(left) {
            dirX = -speedofplayer;
        }
        if (right) {
            dirX = speedofplayer;
        }
        if (up){
            dirY = -speedofplayer;
        }
        if(down) {
            dirY = speedofplayer;
        }
        x_int += dirX;
        y_int += dirY;
        if(x_int < radius) x_int = radius;
        if(y_int < radius) y_int = radius;
        if(x_int > Panel.WIDTH - radius) x_int = Panel.WIDTH - radius;
        if(x_int > Panel.HEIGHT - radius) x_int = Panel.HEIGHT - radius;
    }
    public void draw(Graphics2D g) {
        
    }
}
=======
import java.awt.*;

public class Player {
    
    //Fields
    private int x_int;
    private int y_int;
    private int radius;
    
    private int dirX;
    private int dirY;
    private int speedofplayer;
    
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    
    private int lives;
    
    //LETS CONSTRUUCTTTT!!!!
    public Player() {
    x_int = Panel.WIDTH /2;
    y_int = Panel.HEIGHT /2;
    radius = 5;
    
    dirX =0;
    dirY = 0;
    speedofplayer = 5;
    lives = 5;
    }
    
    public void update() {
        if(left) {
            dirX = -speedofplayer;
        }
        if (right) {
            dirX = speedofplayer;
        }
        if (up){
            dirY = -speedofplayer;
        }
        if(down) {
            dirY = speedofplayer;
        }
        x_int += dirX;
        y_int += dirY;
        if(x_int < radius) x_int = radius;
        if(y_int < radius) y_int = radius;
        if(x_int > Panel.WIDTH - radius) x_int = Panel.WIDTH - radius;
        if(x_int > Panel.HEIGHT - radius) x_int = Panel.HEIGHT - radius;
    }
    public void draw(Graphics2D g) {
        
    }
}
>>>>>>> 1b09f1789ac69999d08f27e5469cb98e8744bb77
