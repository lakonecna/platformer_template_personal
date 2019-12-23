package Main;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Platform Shadow");
        gameFrame.setContentPane(new GamePanel());
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }
}
