package src.TileMap;

import src.Main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Background {
    private BufferedImage image;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double moveScale;
    // to create the parallax effect
    // background doesn't move at player's speed
    // instead at the above fraction of player's
    // speed. The parallax effect = 3D appearance
    // obtained through the slowing down of background
    // movement.

    public Background(String s, double ms) {

        // import resources into game like this:
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            moveScale = ms;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //TODO ***************************************************************** Don't understand
    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH; // smooth scrolling
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    //TODO ***************************************************************** Don't understand
    public void draw(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, null);
        if(x < 0) {
            g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y, null);
        }
        if(x > 0) {
            g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
        }
    }
}
