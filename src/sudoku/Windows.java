package sudoku;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

public class Windows extends JFrame {
    private int WIDTH = 591;
    private int HEIGHT = 651;
    private boolean activatePanelMap = true;
    private final String courses[] = {"Легко", "Нормально", "Тяжело", "Очень тяжело"};
    private final JPanel menu = new JPanel(new GridLayout(1, 4));
    private JPanel jPanel = new Map();
    private final JComboBox comboBox = new JComboBox<>(courses);
    private final JRadioButton radioButton = new JRadioButton("Подсветка");
    private final JButton button = new JButton("Старт");
    private final JButton buttonColor = new JButton("Цвет");

    public JFrame getFrame() {
        return this;
    }

    public Windows() {
        this.setTitle("SUDOKU");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Core.centeringJFrame(WIDTH, HEIGHT, null, this);
        this.setMinimumSize(new Dimension(50, 100));
        this.setResizable(true);
        addComponent();
        listeners();
        addMouseListener();
        Core.HEIGHT = HEIGHT;
        Core.WIDTH = WIDTH;
        Core.condition = 3;
    }

    ActionListener taskPerformer = evt -> jPanel.repaint();

    private void renderJPanel() {
        if (Core.winSudoku()) {
            Core.startGame = false;
            Core.winGame = true;
            activatePanelMap = false;
            Core.condition = 3;
            new Timer(50, taskPerformer).start();
        } else {
            jPanel.repaint();
        }
        this.validate();
    }

    private void addComponent() {
        menu.add(button);
        menu.add(radioButton);
        menu.add(buttonColor);
        menu.add(comboBox);
        this.add(menu, BorderLayout.NORTH);
        this.add(jPanel, BorderLayout.CENTER);
    }


    private void listeners() {
        buttonColor.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Core.condition == 1) {
                    Map.color = Core.randomColor();
                    renderJPanel();
                }
            }
        });
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.winner = false;
                activatePanelMap = true;
                Core.condition = 1;
                buttonUpdate();
            }
        });
        radioButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButton.isSelected()) {
                    Core.fillCell = true;
                } else {
                    Core.fillCell = false;
                }
                if (Core.condition == 1) {
                    renderJPanel();
                }

            }
        });
        comboBox.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.level = comboBox.getSelectedIndex() + 1;
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                double x = e.getComponent().getWidth();
                double y = e.getComponent().getHeight();
                Core.HEIGHT = y;
                Core.WIDTH = x;
                HEIGHT = (int) y;
                WIDTH = (int) x;
                renderJPanel();
                //super.componentResized(e);
            }
        });
    }

    private void addMouseListener() {
        jPanel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Core.startGame && activatePanelMap) {
                    int x = (int) ((e.getX() - Core.leftX) / (Core.resizable / 9));
                    int y = (int) ((e.getY() - Core.upY) / (Core.resizable / 9));
                    Core.arrUserNumber[y][x] = Core.arrSetNext(Core.arrUserNumber[y][x]);
                    renderJPanel();
                }
            }
        });
    }

    private void buttonUpdate() {
        Core.startGame = false;
        Core.startGame();
        renderJPanel();
    }


}
