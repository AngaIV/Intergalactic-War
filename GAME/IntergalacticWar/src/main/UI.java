
package main;

import entity.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import static main.GPanel.HEIGHT;
import static main.GPanel.WIDTH;


public class UI {
    GPanel gp;
    Player player;
    KeyHandler kH;
    private int counter = 0;
    private int num = 1;
    private BufferedImage image1, image2, image3, image4;
    private long currentTime = 0;
    
    //COMMAND NUMBER
    public int commandNum = 0;
    
    //private long DelayTime;
    public UI(GPanel gp, KeyHandler kH){
        this.kH =kH;
        this.gp = gp;
        
        getImages();
    }
    

    
    public void getImages(){
        
        try{
            image1  = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            image2  = ImageIO.read(getClass().getResourceAsStream("/images/ShipL.png")); 
            image3 = ImageIO.read(getClass().getResourceAsStream("/BotSkins/Bot3.png")); 
            image4 = ImageIO.read(getClass().getResourceAsStream("/images/ShipR.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void drawTitleImage(Graphics2D g2){
                //SHADOW
                if (gp.Lost == true){
                                   //SHADOW
                        g2.setFont(new Font("Century Gothic", Font.BOLD, 80));
                        String loseMessage = "Y O U   D I E D !";
                        int length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                        g2.setColor(Color.WHITE);
                        g2.drawString(loseMessage, (WIDTH / 2 - length / 2)+3, (HEIGHT / 2)+3);
                        //GAME TITILE
                        g2.setFont(new Font("Century Gothic", Font.BOLD, 80));
                         loseMessage = "Y O U   D I E D !";
                         length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                        g2.setColor(Color.RED);
                        g2.drawString(loseMessage, WIDTH / 2 - length / 2, (HEIGHT / 2));



                        //MENU
                        //NEW GAME
                        g2.setFont(new Font("Century Gothic", Font.BOLD, 30));
                         loseMessage = "N E W  G A M E  [0]";
                         length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                        g2.setColor(Color.DARK_GRAY);
                        g2.drawString(loseMessage, (WIDTH / 2 - length / 2)+2, (HEIGHT / 2)+92);
                        //SHADOW
                        g2.setFont(new Font("Century Gothic", Font.BOLD, 30));
                         loseMessage = "N E W  G A M E  [0]";
                         length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                        g2.setColor(Color.WHITE);
                        g2.drawString(loseMessage, WIDTH / 2 - length / 2, (HEIGHT / 2)+90);


                        //QUIT
                        g2.setFont(new Font("Century Gothic", Font.BOLD, 30));
                         loseMessage = "Q U I T  [1]";
                         length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                        g2.setColor(Color.DARK_GRAY);
                        g2.drawString(loseMessage, (WIDTH / 2 - length / 2)+2, (HEIGHT / 2)+142);
                        //SHADOW
                        g2.setFont(new Font("Century Gothic", Font.BOLD, 30));
                         loseMessage = "Q U I T  [1]";
                         length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                        g2.setColor(Color.WHITE);
                        g2.drawString(loseMessage, WIDTH / 2 - length / 2, (HEIGHT / 2)+140);
                
                
                }else{
                    
                    //DRAW TITLE SCREEN
                g2.setFont(new Font("Century Gothic", Font.BOLD, 55));
                String loseMessage = "I N T E R G A L A C T I C    W A R";
                int length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.DARK_GRAY);
                g2.drawString(loseMessage, (WIDTH / 2 - length / 2)+3, 153);
                //GAME TITILE
                g2.setFont(new Font("Century Gothic", Font.BOLD, 55));
                 loseMessage = "I N T E R G A L A C T I C    W A R";
                 length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.WHITE);
                g2.drawString(loseMessage, WIDTH / 2 - length / 2, 150);
                
                //VISUALS
                    BufferedImage img = image1;
                    BufferedImage img2 = image2;
                    BufferedImage img3 = image3;
                    BufferedImage img4 = image4;
                    
                    g2.drawImage(img3, 515, (HEIGHT/2)-150, 50, 50, null);
                    //g2.drawImage(img2, 500, (HEIGHT/2)-150, 80, 80, null);
                    
                    g2.drawImage(img, 615, (HEIGHT/2)-150, 150, 150, null);
                    
                    //g2.drawImage(img4, 760, (HEIGHT/2)-150, 80, 80, null);
                    g2.drawImage(img3, 810, (HEIGHT/2)-150, 50, 50, null);
                
                //MENU
                //NEW GAME
                g2.setFont(new Font("Century Gothic", Font.BOLD, 30));
                 loseMessage = "N E W  G A M E  [0]";
                 length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.DARK_GRAY);
                g2.drawString(loseMessage, (WIDTH / 2 - length / 2)+2, (HEIGHT / 2)+92);
                //SHADOW
                g2.setFont(new Font("Century Gothic", Font.BOLD, 30));
                 loseMessage = "N E W  G A M E  [0]";
                 length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.WHITE);
                g2.drawString(loseMessage, WIDTH / 2 - length / 2, (HEIGHT / 2)+90);
                
                
                //QUIT
                g2.setFont(new Font("Century Gothic", Font.BOLD, 30));
                 loseMessage = "Q U I T  [1]";
                 length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.DARK_GRAY);
                g2.drawString(loseMessage, (WIDTH / 2 - length / 2)+2, (HEIGHT / 2)+142);
                //SHADOW
                g2.setFont(new Font("Century Gothic", Font.BOLD, 30));
                 loseMessage = "Q U I T  [1]";
                 length = (int) g2.getFontMetrics().getStringBounds(loseMessage, g2).getWidth();
                g2.setColor(Color.WHITE);
                g2.drawString(loseMessage, WIDTH / 2 - length / 2, (HEIGHT / 2)+140);
                }
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
