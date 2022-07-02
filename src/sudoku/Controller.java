package sudoku;


import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.*;

public class Controller {

    private Windows windows;
    private JFrame frame;
    private JComboBox comboBox;
    private JRadioButton radioButton;
    private JButton button;
    private JButton buttonColor;

    private final int paneLights = 594;
    private final int cell = paneLights / 9;
    private final int stepX = 10;
    private final int stepY = 55;//47

    public static boolean fillCell = false;
    public static boolean winner = false;
    private final int stepCell = cell / 6;
    private int[] arrXY = new int[10];
    private int x, y;

    public void start() {
        windows = new Windows();
        initJComponent();
        listeners();
        frame.setVisible(true);
    }

    private void initJComponent() {
        frame = windows.getFrame();
        comboBox = windows.getComboBox();
        radioButton = windows.getRadioButton();
        button = windows.getButton();
        buttonColor = windows.getButtonColor();
    }

    private void listeners() {
        buttonColor.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Draw.color = Core.randomColor();
                renderJPanel();
            }
        });

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winner = false;
                buttonUpdate();
            }
        });
        radioButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButton.isSelected()) {
                    fillCell = true;
                } else {
                    fillCell = false;
                }
                renderJPanel();

            }
        });
        comboBox.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.level = comboBox.getSelectedIndex() + 1;
            }
        });
        frame.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Core.startGame) {
                    x = Core.normalX(e.getX() - stepX, cell);
                    y = Core.normalY(e.getY() - stepY, cell);
                    Core.arrUserNumber[y][x] = Core.arrSetNext(Core.arrUserNumber[y][x]);
                    arrXY = Core.generateXY(x, y, arrXY, cell, stepCell);
                }
                renderJPanel();
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

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });


    }

    private void renderJPanel(){
        if (Core.startGame) {
            windows.renderJPanel();
        }
    }

    private void buttonUpdate() {
        Core.startGame = false;
        Core.startGame();
        windows.renderJPanel();
    }

}