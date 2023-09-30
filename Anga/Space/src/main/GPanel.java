package main;

import TilePack.TileManager;
import entity.Entity;
import entity.Projectiles;
import entity.Player;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GPanel extends JPanel implements Runnable{
    
    public static int WIDTH = 1366;
    public static int HEIGHT = 768;
    public int tilesize = 200;
    private Thread thread;
    KeyHandler kH = new KeyHandler();
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> entityList = new ArrayList<>();
    TileManager TM = new TileManager(this);
//    private boolean isRunning;
//    private BufferedImage image; //canvas
//    private Graphics2D g;
    Player player = new Player(this, kH);
    //Projectiles bull = new Projectiles(this, kH);
    int FPS = 60;
    
    
    public GPanel() {
       
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
    }
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double DrawTime = System.nanoTime() + drawInterval;
        
        while(thread!=null){
            
            update();
            repaint();
            
            try{
                double remTime = DrawTime - System.nanoTime();
                remTime = remTime/1000000;
                if(remTime < 0){
                    remTime = 0;
                }
                Thread.sleep((long) remTime);
                DrawTime += drawInterval;
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
        
    public void update(){
        player.update();
//        for (int i=0; i < projectileList.size(); i++){
//            if(projectileList.get(i) != null){
//                projectileList.get(i).update();
//            }
//        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        TM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
    
}
