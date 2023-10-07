
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static main.GPanel.HEIGHT;
import static main.GPanel.WIDTH;


public class UI {
    GPanel gp;
    
    public UI(GPanel gp){
        this.gp = gp;
    }
    
    //DRAW PAUSE SCREEN
    public void draw(Graphics g2){
                g2.setFont(new Font("Century Gothic", Font.BOLD, 50));
                String loseMessage = "-    P A U S E D    -";
                int length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.WHITE);
                g2.drawString(loseMessage, WIDTH / 2 - length / 2, HEIGHT / 2);
    }
}
