package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import TileMap.Background;
// TODO sort out the package problem
public class MenuState extends GameState {

    private Background background;

    private int currentChoice = 0;
    private String[] options = {"Start", "Help", "Quit"};

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            // TODO find and paste in image/ gif
            background = new Background("/backgrounds/menubg.gif", 1);
            background.setVector(-0.1, 0);
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 20);
            font = new Font("Arial", Font.PLAIN, 12);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {

    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        background.draw(g);

        // draw titles
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Platform Shadow", 80, 70);
        // a function which automatically centers a string would be more practical

        // draw menu options
        g.setFont(font);
        for(int i = 0; i < options.length; ++i) {
            if(i == currentChoice) {
                g.setColor(Color.BLACK);
            }
            else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }
    }

    private void select() { // start, help, exit
        if(currentChoice == 0) {

        }
        if(currentChoice == 1) {

        }
        if(currentChoice == 2) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER) {
            select();
        }
        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    public void keyReleased(int k) {

    }
}
