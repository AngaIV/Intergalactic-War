
package TilePack;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GPanel;

public class TileManager {
    GPanel gp;
    tile[] Tile;

    public TileManager(GPanel gp) {
        this.gp = gp;
        Tile = new tile[10];
        getTileImage();
    }
    public void getTileImage(){
        
        try{
            Tile[0] = new tile();
            Tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/PGSpace.png"));
            
            Tile[1] = new tile();
            Tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/PRBSpace.png"));
            
            Tile[2] = new tile();
            Tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/WPSpace.png"));
            
            Tile[3] = new tile();
            Tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/BPSpace.png"));
            
            Tile[4] = new tile();
            Tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/RPSpace.png"));
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        
        g2.drawImage(Tile[0].image, 0, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 200, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 400, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 600, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 800, 0, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 0, 200, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 0, 400, gp.tilesize,gp.tilesize,null);
        g2.drawImage(Tile[1].image, 0, 600, gp.tilesize,gp.tilesize,null);
    }
}
