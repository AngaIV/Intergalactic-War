package main;

import TilePack.TileManager;
import entity.Bots;
import entity.Entity;
import entity.Player;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import object.Bullets;

public class GPanel extends JPanel implements Runnable{
    
    public static int WIDTH = 1366;
    public static int HEIGHT = 768;
    public int tilesize = 200;
    private Thread thread;
    KeyHandler kH = new KeyHandler();
    public static ArrayList<Bullets> BulletList;
    public static ArrayList<Bots> BotList;
    public ArrayList<Entity> entityList = new ArrayList<>();
    TileManager TM = new TileManager(this);
//    private boolean isRunning;
//    private BufferedImage image; //canvas
//    private Graphics2D g;
    Player player = new Player(this, kH);
    
    //IN-GAME LEVELS
    private long levelTimer;
    private long levelTimeDifference;
    private int levelNum;
    private boolean startLevel;
    private int levelDelay = 2000;
    
    
    
    
    int FPS = 60;
    
    
    //Game State
    public int gameState;
    public final int NullMenu = 0;
    public final int play = 1;
    public final int pause = 2;
    
    public GPanel() {
       
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
    }
    
    public void setUp(){
        gameState = NullMenu;
    }
    
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }
    

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double DrawTime = System.nanoTime() + drawInterval;
        BulletList = new ArrayList<Bullets>();
        BotList = new ArrayList<Bots>();
        
        String t = "Yes";
        
        levelTimer = 0;
        levelTimeDifference = 0;
        levelNum = 0;
        startLevel = true;
        
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
        
       //NEW LEVEL
        if(levelTimer == 0 && BotList.size() ==0){
            levelNum ++;
            startLevel = false;
            levelTimer = System.nanoTime();
        }
        else{
            levelTimeDifference = (System.nanoTime()-levelTimer)/1000000;
            if(levelTimeDifference > levelDelay){
                levelTimer = 0;
                levelTimeDifference = 0;
                startLevel = true;
            }
        }
       //CRAETE MORE BOTTSSS!!!!!!!!!!!!!
       if(startLevel && BotList.isEmpty()){
           botCreation();
       }
       
        player.update();
        for (int i=0; i<BulletList.size(); i++){
            boolean remove = BulletList.get(i).update();
            if(remove){
                BulletList.remove(i);
                i--;
            }
        }
        
        for (int i=0; i<BotList.size(); i++){
            BotList.get(i).update();
        }
        
        for (int i=0; i<BulletList.size(); i++){
            Bullets b = BulletList.get(i);
            double bx = b.getX();
            double by = b.getY();
            double br = b.getR();
            
            for (int j=0; j<BotList.size(); j++){
                Bots bot =BotList.get(j);
                double cx = bot.getX();
                double cy = bot.getY();
                double cr = bot.getR();
                
                double dx = bx - cx;
                double dy = by - cy;
                double distance = Math.sqrt(dx*dx + dy*dy);
                
                if (distance < br + cr){
                    bot.ifHit();
                    BulletList.remove(i);
                    i--;
                    break;
                }
                        
            }
            for (int j=0; j<BotList.size(); j++){
                if(BotList.get(j).ifDead()){
                    BotList.remove(j);
                    j--;
                }
            }
            
        }
        
//        for (int i=0; i < projectileList.size(); i++){
//            if(projectileList.get(i) != null){
//                projectileList.get(i).update();
//            }
//        }
    }
    
    private void botCreation(){
        BotList.clear();
        Bots b;
        String t = "Yes";
        
        if(levelNum == 1){
            for (int i =0; i<7; i++){
                BotList.add(new Bots(1,1,t));
            }
        }
        if(levelNum == 2){
            for (int i =0; i<14; i++){
                BotList.add(new Bots(1,1,t));
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //Title Screen
       
        //DRAW LEVEL NUMBER

        
        TM.draw(g2);
        player.draw(g2);
        
        //DRAW LEVEL NUMBER
        
        
        for (int i=0; i<BulletList.size(); i++){
            BulletList.get(i).draw(g2);
        }
        for (int i=0; i<BotList.size(); i++){
            BotList.get(i).draw(g2);
        }
        if(levelTimer != 0){
            g2.setFont(new Font("Century Gothic",Font.PLAIN, 50));
            String s = "-  L E V E L      "  + levelNum + "   -";
            int length = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
            int al = (int) (255 * Math.sin(3.14 * levelTimeDifference / levelDelay));
            al = Math.max(al, 0);
            if(al > 255) al = 255;
            g2.setColor(new Color(255,255,255,al));
            g2.drawString(s, WIDTH/2 - length/2, HEIGHT/2);
        }
        
//        for (int i = 0; i < player.getLives(); i++){
//            
//        }
        g2.dispose();
    }
    
}
