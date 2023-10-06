package entity;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Entity {
    
    public int x, y, xb, yb;
    public int speed2, speedbull;
    public BufferedImage upDef, downDef, leftDef, rightDef, bulletDef, botDef, heartDef, upHit, downHit, leftHit, rightHit;
    public String dir, shoot;
    public int counter = 0;
    public int num = 1;
    public String shot;
    
    //bots
    public int health;
    public int type;
    public int rank;
    public boolean ready;
    public boolean dead;
    public Color c1;
    public int dx;
    public int dy;
    public double rad;
    public int speed;
    public int r;
    public int lives;
    
    
    public int attack;
    
    public int maxM;
    public int M;
    
}
