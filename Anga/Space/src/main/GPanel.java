package main;

import TilePack.TileManager;
import entity.Boss;
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
import object.BossBullets;
import object.Bullets;
import object.BotBullets;
import object.Health;
import object.HealthManager;

public class GPanel extends JPanel implements Runnable{
    
    public static int WIDTH = 1366;
    public static int HEIGHT = 768;
    public int tilesize = 200;
    private Thread thread;
    KeyHandler kH = new KeyHandler();
    public static ArrayList<Bullets> BulletList;
    public static ArrayList<BotBullets> BotBulletList;
    public static ArrayList<BossBullets> bossBull;
    public static ArrayList<Bots> BotList;
    public static ArrayList<Boss> boss;
    //public static ArrayList<Health> HealthList;
    public ArrayList<Entity> entityList = new ArrayList<>();
    TileManager TM = new TileManager(this);
    HealthManager HM;
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
    private int waveNum;
    private int numBots;
    
 
    
    
    int FPS = 65;
    
    
    //Game State
    public int gameState;
    public final int NullMenu = 1;
    public final int play = 1;
    public final int pause = 2;
    public final int stop = 3;
    public boolean Lost = false;
    
    
    public GPanel() {
        this.HM = new HealthManager(this, player);
       
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
        BotBulletList = new ArrayList<BotBullets>();
        boss = new ArrayList<Boss>();
        bossBull = new ArrayList<BossBullets>();
        
        String t = "Yes";
        
        levelTimer = 0;
        levelTimeDifference = 0;
        levelNum = 1;
        waveNum = 0;
        numBots = 7;
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
            if(gameState == stop){
                break;
            }    
        }
    }
        
    public void update(){

        
        if (player.getLives() == 0){
            Lost = true;
            gameState = stop;
        }
        
       //NEW LEVEL
        if(levelTimer == 0 && BotList.size() ==0){
            
            if (waveNum == 4){
                levelNum ++;
                waveNum = 0;
                numBots = 7;
            }
            waveNum++;
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
//       String llv = "Display";
//       int lives = 3;
//       HealthList.add(new Health(lives, llv));
       
       //CRAETE MORE BOTTSSS!!!!!!!!!!!!!
       if(startLevel && BotList.isEmpty()){
           botCreation();
       }
       
        player.update();
        for (int i=0; i<BotBulletList.size(); i++){
            boolean remove = BotBulletList.get(i).update();
            if(remove){
                BotBulletList.remove(i);
                i--;
            }
        }
        for (int i=0; i<bossBull.size(); i++){
            boolean remove = bossBull.get(i).update();
            if(remove){
                bossBull.remove(i);
                i--;
            }
        }
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
        for (int i=0; i<boss.size(); i++){
            boss.get(i).update();
        }
        
        for (int i=0; i<BulletList.size(); i++){
            Bullets b = BulletList.get(i);
            double bx = b.getX();
            double by = b.getY();
            double br = b.getR();
            
            //bullet bot collision
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
                    player.addScore(bot.getType()+bot.getWave());
                    BulletList.remove(i);
                    i--;
                    break;
                }
                        
            }
            //boss collision
            for (int j=0; j<boss.size(); j++){
                Boss bs =boss.get(j);
                
                double cx = bs.gtX();
                double cy = bs.gtY();
                double cr = bs.gtR();
                
                double dx = bx - cx;
                double dy = by - cy;
                double distance = Math.sqrt(dx*dx + dy*dy);
                
                if (distance < br + cr){
                    bs.Hit();
                    player.addScore(bs.gtType()+bs.gtWave());
                    BulletList.remove(i);
                    i--;
                    break;
                }
                        
            }
            
            
            //Checking for dead enemies
            for (int j=0; j<BotList.size(); j++){
                if(BotList.get(j).ifDead()){
                    player.addScore(10);
                    BotList.remove(j);
                    j--;
                }
            }
            //checking if boss is dead
            for (int m=0; m<boss.size(); m++){
                if(boss.get(m).Dead()){
                    player.addScore(10);
                    boss.remove(m);
                    m--;
                }
            }
                //player enemy collision
                if (!player.isRecoverig()){
                    int xC = player.getx();
                    int yC = player.gety();
                    int rC = player.getr();
                    

                        for (int l=0; l<BotBulletList.size(); l++){
                            BotBullets bb = BotBulletList.get(l);
                            double bbx = bb.getXX();
                            double bby = bb.getYY();
                            double bbr = bb.getRR();

                            double dx = xC - bbx;
                            double dy = yC - bby;
                            double distance = Math.sqrt(dx*dx + dy*dy);
                            //System.out.println(distance +" "+ rC + bbr);
                            if (distance < (rC + bbr)){
                                BotBulletList.remove(i);
                                player.lifelost();
                                l--;
                            } 
                        }
                        //boss bullets & player collision
                        for (int l=0; l<bossBull.size(); l++){
                            BossBullets bull = bossBull.get(l);
                            double bullx = bull.getXX();
                            double bully = bull.getYY();
                            double bullr = bull.getRR();

                            double dx = xC - bullx;
                            double dy = yC - bully;
                            double distance = Math.sqrt(dx*dx + dy*dy);
                            //System.out.println(distance +" "+ rC + bbr);
                            if (distance < (rC + bullr)){
                                bossBull.remove(i);
                                player.lifelost();
                                l--;
                            } 
                        }

            }
            
        }
        
    }
    
    public int getL(){
         return player.getLives();
    }
    
    private void botCreation(){
        BotList.clear();
        boss.clear();
        Bots b;
        String t = "Yes";
       
        
        if(levelNum == 1){
            for (int i =0; i<numBots; i++){
                BotList.add(new Bots(1,waveNum,t));
            }
            numBots += 4;
        }
        if(levelNum == 2){
            for (int i =0; i<numBots; i++){
                BotList.add(new Bots(2,waveNum,t));
            }
            numBots += 5;
        }
        if(levelNum == 3){
                for (int a=0;a<numBots;a++){
                    BotList.add(new Bots(3,waveNum,t));
                }
                numBots += 6;
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //Title Screen
       
        //DRAW LEVEL NUMBER

        
        TM.draw(g2);
        HM.draw(g2);
        player.draw(g2);
        
   
        //draw player score
        g2.setColor(Color.RED);
        g2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        g2.drawString("Score: " + player.getScore(), WIDTH-250, 35);

        for (int i=0; i<BulletList.size(); i++){
            BulletList.get(i).draw(g2);
        }
        for (int i=0; i<boss.size(); i++){
            boss.get(i).draw(g2);
        }
        for (int i=0; i<BotList.size(); i++){
            BotList.get(i).draw(g2);
        }
        for (int i=0; i<BotBulletList.size(); i++){
            BotBulletList.get(i).draw(g2);
        }
        
        for (int i=0; i<bossBull.size(); i++){
            bossBull.get(i).draw(g2);
        }
        if (Lost){
                g2.setFont(new Font("Century Gothic", Font.PLAIN, 50));
                String loseMessage = "-    Y O U   L O S E    -";
                int length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.RED);
                g2.drawString(loseMessage, WIDTH / 2 - length / 2, HEIGHT / 2);
        }
        if(levelTimer != 0){
            if(levelNum == 3  && waveNum == 4){
                g2.setFont(new Font("Century Gothic",Font.PLAIN, 50));
                String s = "-   B O S S    L E V E L   -";
                int length = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
                int al = (int) (255 * Math.sin(50* levelTimeDifference / levelDelay));
                al = Math.max(al, 0);
                if(al > 255) al = 255;
                g2.setColor(new Color(255,255,255,al));
                g2.drawString(s, WIDTH/2 - length/2, HEIGHT/2);
                
            }

            
            g2.setFont(new Font("Century Gothic",Font.PLAIN, 50));
            String s = "-   LVL " + levelNum +  "  W A V E  " + waveNum + "   -";
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