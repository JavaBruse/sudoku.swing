package sudoku;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Controller games = new Controller();
        JFrame.setDefaultLookAndFeelDecorated(true);
        Core.runnable = new Runnable() {
            @Override
            public void run() {
                games.start();
            }
        };
        javax.swing.SwingUtilities.invokeLater(Core.runnable);


    }
}
