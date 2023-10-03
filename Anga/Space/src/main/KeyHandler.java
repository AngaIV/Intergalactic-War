package main;

import entity.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    
    public boolean PressUp, PressDown, PressLeft, PressRight, PressShoot;
    GPanel gpan;
    
    /*public KeyHandler(GPanel gpan){
        this.gpan = gpan;
    }*/


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (keyCode == KeyEvent.VK_UP){
            PressUp = true;
        }
        if (keyCode == KeyEvent.VK_DOWN){
            PressDown = true;
        }
        if (keyCode == KeyEvent.VK_LEFT){
            PressLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT){
            PressRight = true;
        }
        if (keyCode == KeyEvent.VK_M){
            PressShoot = true;
         
        }
    
    }
    
    /*public void playState(int keyCode){
        if (keyCode == KeyEvent.VK_F){
            PressShoot = true;
        }
    }*/
    @Override
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        

        if (keyCode == KeyEvent.VK_UP){
            PressUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN){
            PressDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT){
            PressLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT){
            PressRight = false;
        }
        if (keyCode == KeyEvent.VK_M){
            PressShoot = false;
        }
    }
}

  

