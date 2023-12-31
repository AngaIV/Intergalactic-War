
package TilePack;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;
import main.UI;

public class TileManager {
    GPanel gp;
    tile[] Tile;
    UI ui;
    public TileManager(GPanel gp, UI ui) {
        this.ui = ui;
        this.gp = gp;
        Tile = new tile[10];
        getTileImage();
    }
    
    //GET TILE IMAGES
    public void getTileImage(){
        
        try{
            Tile[0] = new tile();
            Tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileWG.png"));
            
            Tile[1] = new tile();
            Tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileBlack.png"));
            
            Tile[2] = new tile();
            Tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileRB.png"));
            
            Tile[3] = new tile();
            Tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileBP.png"));
            
            Tile[4] = new tile();
            Tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileBW.png"));
            
            Tile[5] = new tile();
            Tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileWP.png"));
            
            Tile[6] = new tile();
            Tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileRP.png"));
            
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void drawtitle(Graphics2D g2){
        //DRAW FIRST ROW OF TILES
        g2.drawImage(Tile[1].image, 0, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 200, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 400, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 600, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 800, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 1000, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 1200, 0, gp.tilesize,gp.tilesize,null);
        
        //DRAW SECOND ROW OF TILES
        g2.drawImage(Tile[4].image, 0, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 200, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 400, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 600, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[2].image, 800, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 1000, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 1200, 200, gp.tilesize,gp.tilesize,null);
        
        //DRAW THIRD ROW OF TILES
        g2.drawImage(Tile[4].image, 0, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 200, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 400, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 600, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 800, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 1000, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 1200, 400, gp.tilesize,gp.tilesize,null);
        
        //DRAW FOURTH ROW OF TILES
        g2.drawImage(Tile[1].image, 0, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 200, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 400, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 600, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 800, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 1000, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 1200, 600, gp.tilesize,gp.tilesize,null);
        ui.drawTitleImage(g2);
    }
    
    public void draw(Graphics2D g2){
        
        
        
        //DRAW FIRST ROW OF TILES
        g2.drawImage(Tile[0].image, 0, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[3].image, 200, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 400, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[2].image, 600, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 800, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 1000, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 1200, 0, gp.tilesize,gp.tilesize,null);
        
        //DRAW SECOND ROW OF TILES
        g2.drawImage(Tile[4].image, 0, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 200, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 400, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 600, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 800, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 1000, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[5].image, 1200, 200, gp.tilesize,gp.tilesize,null);
        
        //DRAW THIRD ROW OF TILES
        g2.drawImage(Tile[6].image, 0, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 200, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 400, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 600, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 800, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 1000, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 1200, 400, gp.tilesize,gp.tilesize,null);
        
        //DRAW FOURTH ROW OF TILES
        g2.drawImage(Tile[1].image, 0, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 200, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[2].image, 400, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[4].image, 600, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 800, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[0].image, 1000, 600, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 1200, 600, gp.tilesize,gp.tilesize,null);
    }
}
