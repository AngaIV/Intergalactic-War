<<<<<<< HEAD
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.lang.Runnable;

public class Panel extends JPanel implements Runnable{
    
    public static int w = 500;
    public static int h = 500;
    private Thread thread;
    private boolean isRunning;
    private BufferedImage image; //canvas
    private Graphics2D g;
    
    public Panel() { //Construct
        super();
        setPreferredSize(new Dimension(w, h));
        setFocusable(true);
        requestFocus();
    }
    //Fuctions
    public void addNotify() {
        super.addNotify();
        if(thread==null){
            thread = new Thread((Runnable) this);
            thread.start();
        }   
    }
    public void run(){
        isRunning = true;
        image = new BufferedImage (w, h, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        
        while (isRunning){ //loop
            Update();
            Render();
            Draw();
        }
    }
    private void Update() { //logic of the game
    }
    
    private void Render(){
       g.setColor(Color.black);
       g.fillRect(0, 0, w, h);
       g.setColor(Color.white);
       g.drawString("LESS GOOO", 100, 100);
    }
    private void Draw(){
        Graphics g1 = this.getGraphics();
        g1.drawImage(image, 0, 0, null);
        g1.dispose();
    }
=======
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.lang.Runnable;

public class Panel extends JPanel implements Runnable{
    
    public static int w = 500;
    public static int h = 500;
    private Thread thread;
    private boolean isRunning;
    private BufferedImage image; //canvas
    private Graphics2D g;
    
    public Panel() { //Construct
        super();
        setPreferredSize(new Dimension(w, h));
        setFocusable(true);
        requestFocus();
    }
    //Fuctions
    public void addNotify() {
        super.addNotify();
        if(thread==null){
            thread = new Thread((Runnable) this);
            thread.start();
        }   
    }
    public void run(){
        isRunning = true;
        image = new BufferedImage (w, h, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        
        while (isRunning){ //loop
            Update();
            Render();
            Draw();
        }
    }
    private void Update() { //logic of the game
    }
    
    private void Render(){
       g.setColor(Color.black);
       g.fillRect(0, 0, w, h);
       g.setColor(Color.white);
       g.drawString("LESS GOOO", 100, 100);
    }
    private void Draw(){
        Graphics g1 = this.getGraphics();
        g1.drawImage(image, 0, 0, null);
        g1.dispose();
    }
>>>>>>> 1b09f1789ac69999d08f27e5469cb98e8744bb77
}