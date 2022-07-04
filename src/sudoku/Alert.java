package sudoku;

import javax.swing.*;
import java.awt.*;

public class Alert extends JPanel {
    String[] text;
    private final int HEIGHT, WIDTH;
    private int leftX, upY, resizable, borderChar, length;

    Alert(String[] text, int HEIGHT, int WIDTH) {
        this.text = text;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        initLength();
        if (HEIGHT > WIDTH) {
            resizable = WIDTH;
        } else {
            resizable = HEIGHT;
        }
        dot();
    }

    private void initLength() {
        for (String s : text) {
            if (s.length() > length) {
                this.length = s.length();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        Font newFont = new Font("Courier New", Font.BOLD, borderChar);
        g.setFont(newFont);
        g.setColor(Color.BLACK);
        for (String s : text) {
            g.drawString(s, leftX, upY);
            upY += borderChar;
        }
    }

    private void dot() {
        double coefficient = 1.6;
        borderChar = (int) (((double) resizable / (double) length) * coefficient);
        if (borderChar < 15) {
            borderChar = 15;
        }
        leftX = (int) (((double) WIDTH / 2) - ((((double) borderChar / coefficient) * (double) length) / 2));
        upY = (int) (((double) HEIGHT / 2) - ((((double) borderChar / coefficient) * (double) text.length) / 2));
        if (resizable < 10) {
            resizable = 10;
        }
    }
}
