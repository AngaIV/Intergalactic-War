import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;
//import java.lang.Runnable;
import java.awt.event.KeyListener;
import java.awt.event.*;

public class Panel extends JPanel implements Runnable, KeyListener{
    
    public static int WIDTH = 500;
    public static int HEIGHT = 500;
    private Thread thread;
    private boolean isRunning;
    private BufferedImage image; //canvas
    private Graphics2D g;
    private Player player;
    private int FPS = 30;
    private double avgFPS;
   
    
    public Panel() { //Construct
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }
    //Fuctions
    public void addNotify() {
        super.addNotify();
        if(thread==null){
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }
    public void run(){
        
        isRunning = true;
        image = new BufferedImage (WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        player = new Player();
        long sTime;
        long URDTime;
        long wTime;
        int FPScount = 0;
        long totTime = 0;
        int maxFPScount = 30;
        long tarTime = 1000 / FPS;
        
        while (isRunning){ //loop
            sTime = System.nanoTime();
            
            gUpdate();
            gRender();
            gDraw();
            
            URDTime = (System.nanoTime() - sTime) / 1000000; 
            wTime = tarTime - URDTime;
            
            try{
                Thread.sleep(wTime);
            }
            catch(Exception e) {
            }
            totTime += System.nanoTime() - sTime;
            FPScount ++;
            if (FPScount == maxFPScount){
                avgFPS = 1000.0 / ((totTime/FPScount)/1000000);
                FPScount = 0;
                totTime = 0;
            }
            
            
        }
    }
    private void gUpdate() { //logic of the game
        player.update();
    }
    
    private void gRender(){
       g.setColor(Color.black);
       g.fillRect(0, 0, WIDTH, HEIGHT);
       g.setColor(Color.RED);
       g.drawString("FPS: " + avgFPS, 50, 50);
       player.draw(g);
    }
    private void gDraw(){
        Graphics g1 = this.getGraphics();
        g1.drawImage(image, 0, 0, null);
        g1.dispose();
    }
    
    public void keyTyped(KeyEvent key) {
    }
    public void keyPressed(KeyEvent key) {
        
        int keyCode = key.getKeyCode();
        
        if (keyCode == KeyEvent.VK_UP){
            player.setUp(true);
        }
        if (keyCode == KeyEvent.VK_DOWN){
            player.setDown(true);
        }
        if (keyCode == KeyEvent.VK_LEFT){
            player.setLeft(true);
        }
        if (keyCode == KeyEvent.VK_RIGHT){
            player.setRight(true);
        }
    }
    
    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();
       
        if (keyCode == KeyEvent.VK_UP){
            player.setUp(false);
        }
        if (keyCode == KeyEvent.VK_DOWN){
            player.setDown(false);
        }
        if (keyCode == KeyEvent.VK_LEFT){
            player.setLeft(false);
        }
        if (keyCode == KeyEvent.VK_RIGHT){
            player.setRight(false);
        }
    }
}