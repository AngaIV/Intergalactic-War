import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;



public class GamePanel extends JPanel implements Runnable, KeyListener {

        //fields
        public static int WIDTH = 400;
        public static int HEIGHT = 400;

        private Thread thread;
        private boolean running;


        private BufferedImage image;
        private Graphics2D g;


        private int FPS = 30;
        private double averageFPS;

        public static Player player;
        public static ArrayList<Bullet>bullets;
        public static ArrayList<Enemy> enemies;

        //constrac
    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }
    
    //Function
    public void addNotify(){
        super.addNotify();
        if (thread == null){
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }

    public void run(){
        running = true;

        image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        player = new Player();

        bullets = new ArrayList<Bullet>();
        enemies = new  ArrayList<Enemy>();
        
        for(int i = 0 ;i<5;i++){// used it to addd five enemy
            enemies.add(new Enemy(1, 1));
        }


        long startTime;
        long URDTimeMil;
        long WaitTime;
        long Totaltime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

        long targetTime = 1000/FPS;

        //Game_Loop
        while(running){

            startTime = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            URDTimeMil = (System.nanoTime()-startTime)/1000000;
            WaitTime = targetTime - URDTimeMil;

            try{thread.sleep(WaitTime);}catch(Exception e){}

            Totaltime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount==maxFrameCount){
                averageFPS=1000.0/((Totaltime/frameCount)/1000000);
                frameCount=0;
                Totaltime=0;
            }
        }
    }

    private void gameUpdate(){
        //game update
        player.update();
        //bullet update
        for(int i = 0 ; i< bullets.size();i++){
            boolean remove = bullets.get(i).update();
            if(remove){
                bullets.remove(i);
                i--;
            }
        }
        //enemy update
        for(int i = 0 ; i< enemies.size();i++){
            enemies.get(i).update();
        }
        //check bullet-enemy colletion
        for(int i =0; i<bullets.size();i++){
            Bullet b = bullets.get(i);
            double bx = b.getsx();
            double by = b.getsy();
            double br = b.getsr();

            for(int j = 0 ; j<enemies.size();j++){
                Enemy e= enemies.get(j);
                double ex = e.getsx();
                double ey = e.getsy();
                double er = e.getsr();

                double dx =bx -ex;
                double dy = by -ey;
                double dist = Math.sqrt(dx*dx+dy*dy);

                if(dist < br +er){
                    e.hit();
                    bullets.remove(i);
                    i--;
                    break;
                }

            }
        }
    // check dead enemies
    for (int i = 0 ; i<enemies.size();i++){
        if (enemies.get(i).isDead()){
            enemies.remove(i);
            i--;
        }
    }

    }

    private void gameRender(){
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("FPS"+ averageFPS, 10, 10);

        g.drawString("num bull: "+ bullets.size(),10,20);

        //draw playe
        player.draw(g);
        //draw bullt
        for(int i = 0 ; i< bullets.size();i++){
            bullets.get(i).draw(g);
         }

         //draw enemy
         for(int i = 0 ; i< enemies.size();i++){
            enemies.get(i).draw(g);
        }



        }
    private void gameDraw(){

        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();;       
    }

    
    public void keyTyped(KeyEvent key){}
  
    public void keyPressed(KeyEvent key){
        int keyCode = key.getKeyCode();
        if(keyCode==KeyEvent.VK_LEFT){
            player.setLeft(true);
        }
        if(keyCode==KeyEvent.VK_RIGHT){
            player.setRight(true);
        }

        if(keyCode==KeyEvent.VK_UP){
            player.setUp(true);
        }
        if(keyCode==KeyEvent.VK_DOWN){
        player.setDown(true);
        }
        
        if(keyCode==KeyEvent.VK_X){
            player.setFiring(true);
            }




    }
  
    public void keyReleased(KeyEvent key){
        int keyCode = key.getKeyCode();
        if(keyCode==KeyEvent.VK_LEFT){
            player.setLeft(false);
        }
        if(keyCode==KeyEvent.VK_RIGHT){
            player.setRight(false);
        }

        if(keyCode==KeyEvent.VK_UP){
            player.setUp(false);
        }
        if(keyCode==KeyEvent.VK_DOWN){
        player.setDown(false);
        }

        
        if(keyCode==KeyEvent.VK_X){
            player.setFiring(false);
            }
    

    
    }
    

}


