package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Draw extends JPanel {
    private final int paneLights = 594;
    private final int cell = paneLights / 9;
    private boolean fillCell = false;
    private final int stepCell = cell / 6;
    private int[] arrXY = new int[10];
    public static Color color = new Color(122,125,185);

    public Draw( ) {
    }

    protected void paintComponent(Graphics g) {
        fillCell = Controller.fillCell;
        if (fillCell == true) {
            setFillCell(g);
        }
        setLineCell(g, Color.BLACK);
        setAllNumber(g, Core.sudokuArr);
        Core.synchrArr(Core.arrUserNumber, Core.sudokuArr);
        setAllNumber(g, Core.arrUserNumber);
    }

    private void setLineCell(Graphics g, Color color) {
        for (int i = 0; i < paneLights + 1; i += paneLights / 9) {
            g.setColor(color);
            g.drawLine(i, 0, i, paneLights);
            g.drawLine(0, i, paneLights, i);
        }
    }

    private void setFillCell(Graphics g) {
        Core.mergerArr(Core.arrUserNumber, Core.sudokuArr);
        for (int i = 0; i < 9; i++) {
            Core.generateXY(i, i, arrXY, cell, stepCell);
            if (Core.winerH(i, Core.winnerArr)) {
                paneFillCell(g, 0, cell * i, paneLights, cell);
            }
            if (Core.winerV(i, Core.winnerArr)) {
                paneFillCell(g, cell * i, 0, cell, paneLights);
            }
            if (Core.winerRactzngle(i, Core.winnerArr)) {
                int arr[] = Core.gpsRactangle(i);
                paneFillCell(g, cell * arr[1], cell * arr[0], paneLights / 3, paneLights / 3);
            }
        }
    }

    public void paneFillCell(Graphics g, int horiz, int vert, int hight, int width) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(color);
        Rectangle2D r2D = new Rectangle2D.Float(horiz, vert, hight, width);
        g2.fill(r2D);
    }

    private void setAllNumber(Graphics g, int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arrXY = Core.generateXY(i, j, arrXY, cell, stepCell);
                bruteForce(g, i, j, arr);
            }
        }
    }

    private void bruteForce(Graphics g, int vert, int horiz, int arr[][]) {
        Font newFont = new Font("Courier New", 1, 600 / 9);
        g.setFont(newFont);
        String s = String.valueOf(arr[vert][horiz]);
        int x = (611 / 9) * horiz +10;
        int y = (600 / 9) * vert +55;
        if (!s.equals("0") ) {
            g.drawString(s, x, y);
        }

    }
}
