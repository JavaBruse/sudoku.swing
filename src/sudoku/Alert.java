package sudoku;

import javax.swing.*;
import java.awt.*;

public class Alert extends JPanel {
    String[] text;
    private int resizable;
    int length = 0;
    int HEIGHT;
    int WIDTH;


    Alert(String[] text, int HEIGHT, int WIDTH) {
        this.text = text;
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        if (HEIGHT > WIDTH) {
            resizable = WIDTH;
        } else {
            resizable = HEIGHT;
        }
        for (String s : text) {
            if (length < s.length()) {
                length = s.length();
            }
        }
    }


    protected void paintComponent(Graphics g) {
        Font newFont = new Font("Courier New", 1, 17);
        g.setFont(newFont);
        int y = (resizable - 100 - (text.length * 15)) / 2;
        int x = (resizable - (length * 10)) / 2;
        for (String s : text) {
            g.drawString(s, x, y);
            y += 20;
        }
    }
}
