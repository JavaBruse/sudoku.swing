package org.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Map extends JPanel {
    private double HEIGHT, WIDTH;
    private double leftX, upY, rightX, downY, resizable, borderLine;
    private boolean fillCell = false;
    public static Color color = new Color(122, 125, 185);

    public Map() {
    }

    private void dot() {
        if (HEIGHT > WIDTH) {
            resizable = WIDTH;
        } else {
            resizable = HEIGHT;
        }
        leftX = WIDTH / 2 - resizable / 2;
        upY = HEIGHT / 2 - resizable / 2;
        if (upY < 0) {
            upY = 0;
        }
        rightX = WIDTH - leftX;
        downY = HEIGHT - upY;
        if (rightX - leftX > downY - upY) {
            resizable = downY - upY;
        } else {
            resizable = rightX - leftX;
        }
        if (resizable < 10) {
            resizable = 10;
        }
        borderLine = (resizable / 100) / 2;
        Core.borderLine = borderLine;
        Core.leftX = leftX;
        Core.upY = upY;
        Core.resizable = resizable;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        HEIGHT = Core.HEIGHT - 57;
        WIDTH = Core.WIDTH;
        dot();
        switch (Core.condition) {
            case 1: // 1-игра.
                fillCell = Core.fillCell;
                if (fillCell == true) {
                    setFillCell(g);
                }
                setAllNumber(g, Core.sudokuArr, Color.BLACK);
                Core.synchrArr(Core.arrUserNumber, Core.sudokuArr);
                setAllNumber(g, Core.arrUserNumber, Color.magenta);
                break;
            case 2: // 2-ожидание игры.
               // setAllNumber(g, Core.sudokuArr, Color.BLACK);

                break;
            case 3: // 3-победа.
                randomColorCell(g);
        }
        border(g);

    }




    private void border(Graphics g){
        setLineCell(g, Color.BLACK);
        setBorderLine(g, Color.BLACK);
        setBorderCub(g, Color.BLACK);
    }


    private void randomColorCell(Graphics g) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                paneFillCell(g, (int) (leftX + ((resizable / 9) * i)), (int) (upY + ((resizable / 9) * j)), (int) (resizable / 9), (int) (resizable / 9), Core.randomColor());
            }
        }
    }


    private void setBorderLine(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke bs = new BasicStroke((float) (10));
        g2.setStroke(bs);
        g.drawRect((int) leftX, (int) upY, (int) resizable, (int) resizable);
    }

    private void setBorderCub(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke bs = new BasicStroke((float) (borderLine));
        g2.setStroke(bs);
        for (double i = leftX; i < rightX; i += resizable / 3) {
            g.drawLine((int) i, (int) upY, (int) i, (int) downY);
        }
        for (double i = upY; i < downY; i += resizable / 3) {
            g.drawLine((int) leftX, (int) i, (int) rightX, (int) i);
        }
    }

    private void setLineCell(Graphics g, Color color) {
        g.setColor(color);
        for (double i = leftX; i < rightX; i += resizable / 9) {
            g.drawLine((int) i, (int) upY, (int) i, (int) downY);
        }
        for (double i = upY; i < downY; i += resizable / 9) {
            g.drawLine((int) leftX, (int) i, (int) rightX, (int) i);
        }
    }

    private void setFillCell(Graphics g) {
        Core.mergerArr(Core.arrUserNumber, Core.sudokuArr);
        for (int i = 0; i < 9; i++) {
            if (Core.winerH(i, Core.winnerArr)) {
                paneFillCell(g, (int) leftX, (int) (upY + ((resizable / 9) * i)), (int) resizable, (int) (resizable / 9), color);
            }
            if (Core.winerV(i, Core.winnerArr)) {
                paneFillCell(g, (int) (leftX + ((resizable / 9) * i)), (int) upY, (int) (resizable / 9), (int) resizable, color);
            }
            if (Core.winerRactzngle(i, Core.winnerArr)) {
                int arr[] = Core.gpsRactangle(i);
                paneFillCell(g, (int) (leftX + ((resizable / 9) * arr[1])), (int) (upY + ((resizable / 9) * arr[0])), (int) (resizable / 3), (int) (resizable / 3), color);
            }
        }
    }

    public void paneFillCell(Graphics g, int horiz, int vert, int hight, int width, Color color) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(color);
        Rectangle2D r2D = new Rectangle2D.Float(horiz, vert, hight, width);
        g2.fill(r2D);
    }

    private void setAllNumber(Graphics g, int arr[][], Color color) {
        Font newFont = new Font("Courier New", 1, (int) (resizable / 9));
        g.setFont(newFont);
        g.setColor(color);
        double stepCell = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] != 0) {
                    stepCell = ((resizable / 9) / 100) * 18;
                    double x = leftX + ((resizable / 9) * j) + stepCell;
                    double y = upY + ((resizable / 9) * i) - stepCell + (resizable / 9);
                    g.drawString(String.valueOf(arr[i][j]), (int) x, (int) y);
                }
            }
        }
        Core.stepX = (int) stepCell;
        Core.stepY = (int) ((int) stepCell + (resizable / 9));
    }

}
