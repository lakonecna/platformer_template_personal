package Main;//

import GameState.GameStateManager;

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

    //game state manager
    private GameStateManager gsm;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        // Many components – even those primarily operated with the mouse, such as buttons –
        // can be operated with the keyboard.
        // For a key press to affect a component, the component must have the keyboard focus
        // below code sets the Main.GamePanel as the component which has the focus of the keyboard
        // the action listener for this component will be roused if any key is pressed
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify(); // for now, links component to parent when it is linked to a container
        if(thread == null) {
            // the target object of a thread must implement the runnable interface
            // along with its run method, we do all this in the Main.GamePanel class
            // the arg to addKeyListener must be a class implementing the KeyListener interface
            // along with keyTyped/pressed/released. In this example we use the current class
            // where as before we used an anonymous class/ lambda.
            // the reason we can't use a lambda here is because there is more than one class to implement.
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();
        running = true;
        gsm = new GameStateManager();
    }

    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        // game loop
        while(running) {

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed/1000000;

            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        gsm.update();
    }
    private void draw() {
        gsm.draw(graphics);
    }
    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }
}
