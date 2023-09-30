package entity;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Entity {
    
    public int x, y;
    public int speed2, speedbull;
    public BufferedImage upDef, downDef, leftDef, rightDef, bulletDef;
    public String dir;
    public int counter = 0;
    public int num = 1;
    public String shot;
    public int life;
    public int maxlife = 3;
    public int attack;
    public Projectiles projectile;
    public int maxM;
    public int M;
    
}
