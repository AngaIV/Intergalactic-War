package main;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import main.GPanel;

public class Game {
   
    public static void main(String[] args){
        //MAIN METHOD
        ImageIcon logo = new ImageIcon("/images/Alien.png");
        JFrame window = new JFrame("IntergalacticWars");
        window.setIconImage(logo.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GPanel gpan = new GPanel();
        window.add(gpan);
        window.pack();
        window.setVisible(true);
        gpan.startThread();
    }
}