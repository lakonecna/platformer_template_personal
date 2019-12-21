//

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements KeyListener, Runnable {
    private static final int WIDTH = 320;
    private static final int HEIGHT = 240;
    private static final int SCALE = 2;
    //typical game dimensions are 640x480

    // setting up game threads
    //WHY ***********************************************************************************
    // TODO
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;
    //***************************************************************************************

    // setting up frame's image
    // difference between image and bufferimage according to so:
    // The java.awt.Image class is the superclass that represents
    // graphical images as rectangular arrays of pixels.
    // The java.awt.image.BufferedImage class, which extends the
    // Image class to allow the application to operate directly with image data
    // (for example, retrieving or setting up the pixel color).
    // Applications can directly construct instances of this class.
    // aka: Image is an abstract encapsulation of an image as an array of pixels.
    // while the BufferedImage inherits from image as well as contains
    // functionality for interacting with the pixels in the array.
    private BufferedImage image;
    private Graphics2D graphics;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        // TODO
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage, TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();
        running = true;
    }

    public void run() {
        init();
        // game loop
        while(running) {
            
        }
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {}
    public void keyReleased(KeyEvent key) {}
}
