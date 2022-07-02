package sudoku;

import javax.swing.*;
import java.awt.*;

public class Alert extends JPanel {
    String[] text;
    int length = 0;

    Alert(String[] text) {
        this.text = text;
        for (String s : text) {
            if (length < s.length()) {
                length = s.length();
            }
        }
    }


    protected void paintComponent(Graphics g) {
        Font newFont = new Font("Courier New", 1, 17);
        g.setFont(newFont);
        int y = (Core.HEIGHT - 100 - (text.length * 15)) / 2;
        int x = (Core.WIDTH - (length * 10)) / 2;
        for (String s : text) {
            g.drawString(s, x, y);
            y += 20;
        }
    }
}
