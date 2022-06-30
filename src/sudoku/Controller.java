package sudoku;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller {

    private Windows windows = new Windows();
    private JFrame frame;
    private JComboBox comboBox;
    private JRadioButton radioButton;
    private JButton button;

    private final int paneLights = 594;
    private final int cell = paneLights / 9;
    private final int stepX = 10;
    private final int stepY = 55;//47

    public static boolean fillCell = false;
    private final int stepCell = cell / 6;
    private int[] arrXY = new int[10];
    private int x, y;

    public void start() {
        Core.startGame();
        initJComponent();
        listeners();
        frame.setVisible(true);
    }

    private void initJComponent() {
        frame = windows.getFrame();
        comboBox = windows.getComboBox();
        radioButton = windows.getRadioButton();
        button = windows.getButton();
    }

    private void listeners() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonUpdate();
            }
        });
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fillCell == false) {
                    fillCell = true;
                } else {
                    fillCell = false;

                }
                windows.render();
            }
        });
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.level = comboBox.getSelectedIndex() + 1;
                buttonUpdate();
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = Core.normalX(e.getX() - stepX, cell);
                y = Core.normalY(e.getY() - stepY, cell);
                Core.arrUserNumber[y][x] = Core.arrSetNext(Core.arrUserNumber[y][x]);
                arrXY = Core.generateXY(x, y, arrXY, cell, stepCell);
                windows.render();
                if (Core.winSudoku()) {
                    windows.alert("Вы выиграли!", "Поздравляю!!!");
                    buttonUpdate();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    private void buttonUpdate() {
        Core.startGame = false;
        Core.startGame();
        windows.render();
    }

}