package sudoku;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ControllerGames games = new ControllerGames();
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                games.start();
            }
        });
    }
}
