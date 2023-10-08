package main;

import TilePack.TileManager;
import entity.Boss;
import entity.Bots;
import entity.Entity;
import entity.Player;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.BossBullets;
import object.Bullets;
import object.BotBullets;
import object.Health;
import object.HealthManager;
import object.PowerUps;

public class GPanel extends JPanel implements Runnable{
    
    //SET PRPGRAM SCREEN VALUES
    public static int WIDTH = 1366;
    public static int HEIGHT = 768;
    
    GPanel gp;
    private Thread thread;
    UI ui = new UI(this);
    KeyHandler kH = new KeyHandler(this);
    
    //BULLET, BOSS, BOT AND POWERUP LISTS
    public static ArrayList<Bullets> BulletList;
    public static ArrayList<BotBullets> BotBulletList;
    public static ArrayList<BossBullets> bossBull;
    public static ArrayList<Bots> BotList;
    public static ArrayList<Boss> boss;
    public static ArrayList<PowerUps> P_ups;
    public ArrayList<Entity> entityList = new ArrayList<>();
    
    //IN GAME VISUALS
    TileManager TM = new TileManager(this);
    HealthManager HM;
    Player player = new Player(this, kH);
    public int tilesize = 200;
    
    //IN-GAME LEVELS
    private long levelTimer;
    private long levelTimeDifference;
    private int levelNum;
    private boolean startLevel;
    private int levelDelay = 2000;
    private int waveNum;
    private int numBots;
    
 
    
    //GAME FPS
    int FPS = 70;
    
    
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
        P_ups = new ArrayList<PowerUps>();
        
        String t = "Yes";
        
        levelTimer = 0;
        levelTimeDifference = 0;
        levelNum = 1;
        waveNum = 0;
        numBots = 7;
        startLevel = true;
        gameState = play;
        
        //GAME LOOP
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
            if(Lost){
                break;
            }    
        }
    }
        
    public void update(){

        //STOP GAME IF PLAYER LOSES ALL 3 LIVES
        if (player.getLives() == 0){
            Lost = true;
        }
        
        
       //INCREASE LEVEL AFTER 4 WAVES
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
            //DELAY TIME BETWEEN LEVELS AND WAVES
            levelTimeDifference = (System.nanoTime()-levelTimer)/1000000;
            if(levelTimeDifference > levelDelay){
                levelTimer = 0;
                levelTimeDifference = 0;
                startLevel = true;
            }
        }

       //RESUME GAME
        if (gameState == play){
       //CRAETE MORE BOTTSSS!!!!!!!!!!!!!
            if(startLevel && BotList.isEmpty()){
                botCreation();
            }
            player.update();
            
            for (int i=0; i<BotList.size(); i++){
                BotList.get(i).update();
            }

            for (int i=0; i<boss.size(); i++){
                boss.get(i).update();
            }
                    //REMOVE BULLETS FROM LIST ONCE OUT OF BOUNDS
        for (int i=0; i<BotBulletList.size(); i++){
            boolean remove = BotBulletList.get(i).update();
            if(remove){
                BotBulletList.remove(i);
                i--;
            }
        }
        //REMOVE BULLETS FROM LIST ONCE OUT OF BOUNDS
        for (int i=0; i<bossBull.size(); i++){
            boolean remove = bossBull.get(i).update();
            if(remove){
                bossBull.remove(i);
                i--;
            }
        }
        //REMOVE BULLETS FROM LIST ONCE OUT OF BOUNDS
        for (int i=0; i<BulletList.size(); i++){
            boolean remove = BulletList.get(i).update();
            if(remove){
                BulletList.remove(i);
                i--;
            }
        }
        for (int i=0; i<P_ups.size(); i++){
            boolean remove = P_ups.get(i).update();
            if(remove){
                P_ups.remove(i);
                i--;
                }
            }
        }
        //PAUSE GAME
        if(gameState == pause){
            
        }
        
        for (int i=0; i<BulletList.size(); i++){
            Bullets b = BulletList.get(i);
            double bx = b.getX();
            double by = b.getY();
            double br = b.getR();
            
            //BOT BULLET + PLAYER COLLISION
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
            //BOSS PLAYER COLLISION
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
            
            
            //CHECK IF BOTS ARE DEAD
            for (int j=0; j<BotList.size(); j++){
                if(BotList.get(j).ifDead()){
                    
                    //probability of GETTING A POWERUP
                    double random = Math.random();
                    if(random < 0.04){
                        P_ups.add(new PowerUps(1, BotList.get(j).getX(), BotList.get(j).getY(), true));
                    }
                    else if(random < 0.3){
                        P_ups.add(new PowerUps(2, BotList.get(j).getX(), BotList.get(j).getY(), true));
                    }
                    else if(random < 0.15){
                        P_ups.add(new PowerUps(3, BotList.get(j).getX(), BotList.get(j).getY(), true));
                    }

                    player.addScore(10);
                    BotList.remove(j);
                    
                    j--;
                }
            }
            //CHECK IF BOSS IS DEAD
            for (int m=0; m<boss.size(); m++){
                if(boss.get(m).Dead()){
                    player.addScore(10);
                    boss.remove(m);
                    m--;
                }
            }
                
                if (!player.isRecoverig()){
                    int xC = player.getx();
                    int yC = player.gety();
                    int rC = player.getr();
                    
                        //PLAYER BULLET + BOT COLLISION
                        for (int l=0; l<BotBulletList.size(); l++){
                            BotBullets bb = BotBulletList.get(l);
                            double bbx = bb.getXX();
                            double bby = bb.getYY();
                            double bbr = bb.getRR();

                            double dx = xC - bbx;
                            double dy = yC - bby;
                            double distance = Math.sqrt(dx*dx + dy*dy);
                            if (distance < (rC + bbr)){
                                BotBulletList.remove(i);
                                player.lifelost();
                                //LOSE BULLET POWER UPS IF SHOT
                                player.bulletLevel = 0;
                                player.power = 0;
                                l--;
                            } 
                        }
                        //BOSS BULLETS + PLAYER COLLISION
                        for (int l=0; l<bossBull.size(); l++){
                            BossBullets bull = bossBull.get(l);
                            double bullx = bull.getXX();
                            double bully = bull.getYY();
                            double bullr = bull.getRR();

                            double dx = xC - bullx;
                            double dy = yC - bully;
                            double distance = Math.sqrt(dx*dx + dy*dy);
                            if (distance < (rC + bullr)){
                                bossBull.remove(i);
                                player.lifelost();
                                l--;
                            } 
                        }

            }
            
        }
        //PLAYER , POWERUP COLLISION
        int playerx = player.getx();
        int playery = player.gety();
        int playerr = player.getr();
        for (int i=0;i<P_ups.size();i++){
            PowerUps p = P_ups.get(i);
            double x = p.getx();
            double y = p.gety();
            double r = p.getr();
            double dx = playerx - x;
            double dy = playery - y;
            double distance = Math.sqrt(dx*dx + dy*dy);
            
            //POWER UP COLLECTED
            if(distance < playerr + r){
                if(P_ups.get(i).gettype() == 1){
                    player.addLife();
                }
                if(P_ups.get(i).gettype() == 2){
                    player.increasedBullets(1);
                }
                if(P_ups.get(i).gettype() == 3){
                    player.increasedBullets(2);
                }
                P_ups.remove(i);
                i--;
            }
        }
        
    }
    
    //GET PLAYER LIVES
    public int getL(){
         return player.getLives();
    }
    
    //CREATE BOTS BASED ON LEVEL
    private void botCreation(){
        BotList.clear(); //ENSURE LIST IS EMPTY
        boss.clear(); //ENSURE LIST IS EMPTY
        Bots b;
        String t = "Yes";
       
        
        if(levelNum == 1){
            for (int i =0; i<numBots; i++){
                BotList.add(new Bots(1,waveNum,t));
            }
            numBots += 2;
        }
        if(levelNum == 2){
            for (int i =0; i<numBots; i++){
                BotList.add(new Bots(2,waveNum,t));
            }
            numBots += 3;
        }
        if(levelNum == 3){
            if (waveNum == 4){
                boss.add(new Boss(3, waveNum, t));
                for (int i =0; i<10; i++){
                    BotList.add(new Bots(3,waveNum,t));
                }
                for (int i =0; i<3; i++){
                   BotList.add(new Bots(1,waveNum,t));
                }
                for (int i =0; i<5; i++){
                    BotList.add(new Bots(2,waveNum,t));
                }
            }else{
                for (int a=0;a<waveNum;a++){
                    BotList.add(new Bots(3,waveNum,t));
                }
            }
            numBots += 4;
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //DRAW IN-GAME VISUALS
        TM.draw(g2);
        HM.draw(g2);
        player.draw(g2);
        
        //DRAW PAUSE-STATE TEXT
        if(gameState == pause){
            ui.draw(g);
        }
        
        //DRAW BULLET LEVEL
        g2.setColor(Color.GREEN);
        g2.fillRect(9, 50, player.getPower()*15, 15);
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(2));
        for (int i=0 ;i<player.getReqPower();i++){
            g2.drawRect(9+15*i, 50, 15, 15);
        }
        g2.setStroke(new BasicStroke(1));
   
        //DRAW PLAYER SCORE
        g2.setColor(Color.RED);
        g2.setFont(new Font("Century Gothic", Font.BOLD, 20));
        g2.drawString("SCORE: " + player.getScore(), WIDTH-250, 35);
        
        //DRAW IN-GAME VISUALS(BULLETS AND BOTS)
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
        for (int i=0; i<P_ups.size(); i++){
            P_ups.get(i).draw(g2);
        }
        
        for (int i=0; i<bossBull.size(); i++){
            bossBull.get(i).draw(g2);
        }
        
        //DISPLAY TEXT WHEN USER LOSES
        if (Lost){
                g2.setFont(new Font("Century Gothic", Font.BOLD, 50));
                String loseMessage = "-    Y O U   L O S E    -";
                int length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.RED);
                g2.drawString(loseMessage, WIDTH / 2 - length / 2, HEIGHT / 2);
        }
        if(levelTimer != 0){
            
            //DISPLAY TEXT WHEN ENTERING BOSS LEVEL 
            if(levelNum == 3  && waveNum == 4){
                g2.setFont(new Font("Century Gothic",Font.BOLD, 50));
                String s = "-   B O S S    L E V E L   -";
                int length = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
                int al = (int) (255 * Math.sin(50* levelTimeDifference / levelDelay));
                al = Math.max(al, 0);
                if(al > 255) al = 255;
                g2.setColor(new Color(255,0,0,al));
                g2.drawString(s, WIDTH/2 - length/2, HEIGHT/2);
                
            }else{

                //DISPLAY LEVEL AND WAVE NUMBER
                g2.setFont(new Font("Century Gothic",Font.BOLD, 50));
                String s = "-   LVL " + levelNum +  "  W A V E  " + waveNum + "   -";
                int length = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
                int al = (int) (255 * Math.sin(3.14 * levelTimeDifference / levelDelay));
                al = Math.max(al, 0);
                if(al > 255) al = 255;
                g2.setColor(new Color(255,255,255,al));
                g2.drawString(s, WIDTH/2 - length/2, HEIGHT/2);
                }

        }
        g2.dispose();
    }
    
}   