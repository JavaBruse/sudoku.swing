package org.sudoku;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Controller games = new Controller();
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(() -> games.start());
    }
}
