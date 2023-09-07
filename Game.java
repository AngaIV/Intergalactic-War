<<<<<<< HEAD
import javax.swing.JFrame;
import java.lang.Runnable;
import javax.swing.ImageIcon;

public class Game {
    public static void main(String[] args){
        ImageIcon logo = new ImageIcon(".//res//Alien.png");
        JFrame window = new JFrame("IntergalacticWars");
        window.setIconImage(logo.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Panel());
        window.pack();
        window.setVisible(true);
    }
=======
import javax.swing.JFrame;
import java.lang.Runnable;
import javax.swing.ImageIcon;

public class Game {
    public static void main(String[] args){
        ImageIcon logo = new ImageIcon(".//res//Alien.png");
        JFrame window = new JFrame("IntergalacticWars");
        window.setIconImage(logo.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Panel());
        window.pack();
        window.setVisible(true);
    }
>>>>>>> 1b09f1789ac69999d08f27e5469cb98e8744bb77
}