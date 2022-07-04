package sudoku;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class Windows extends JFrame {

    private int WIDTH = 591;
    private int HEIGHT = 651;

    private JPanel panelMenu, panelMap, panelAlert;
    private JComboBox comboBox;
    private JRadioButton radioButton;
    private JButton button;
    private JButton buttonColor;
    private String[] alertNewGame = new String[]{
            "Я приветсвую тебя в игре SUDOKU",
            " - Кнопка СТАРТ создаст игру!",
            " - ПОДСВЕТКА покрасит завершенные комбинации!",
            " - ЦВЕТ изменит цвет подсветки на рандомный!",
            " - А ЛЕГКО это сложность!"};
    private String[] alertWinner = new String[]{"Победа", "Поздравляю, Вы победили!"};

    public JFrame getFrame() {
        return this;
    }

    public Windows() {
        initComponent();
        addComponent();
        renderNewGame();
    }

    private void renderJPanel() {
        removeContent();
        if (Core.winSudoku()) {
            Core.startGame = false;
            Core.winGame = true;
            initAlert(alertWinner);
            this.add(panelAlert, BorderLayout.CENTER);
        } else {
            initMap(1);
            this.add(panelMap, BorderLayout.CENTER);
        }
        this.validate();
    }

    private void renderNewGame() {
        removeContent();
        initAlert(alertNewGame);
        this.add(panelAlert, BorderLayout.CENTER);
        this.validate();
    }


    private void removeContent() {
        int x = 1;
        for (Component s : this.getContentPane().getComponents()) {
            if (s.getName().equals("panelMap")) {
                this.getContentPane().remove(panelMap);
            }
            if (s.getName().equals("panelAlert")) {
                this.getContentPane().remove(panelAlert);
            }
        }
    }

    private void addComponent() {
        panelMenu.add(button);
        panelMenu.add(radioButton);
        panelMenu.add(buttonColor);
        panelMenu.add(comboBox);
        this.add(panelMenu, BorderLayout.NORTH);
    }

    private void initComponent() {
        initFrame();
        initPanelMenu();
        initComboBox();
        initRadioButton();
        initButton();
        listeners();
        initMap(2);
    }

    private void initFrame() {
        this.setTitle("SUDOKU");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centeringComponent(WIDTH, HEIGHT, null, this);
        this.setMinimumSize(new Dimension(50, 50));
        this.setResizable(true);
    }

    private void initComboBox() {
        String courses[] = {"Легко", "Нормально", "Тяжело", "Очень тяжело"};
        comboBox = new JComboBox(courses);
        comboBox.setBounds(120, 5, 150, 20);
    }

    private void initRadioButton() {
        radioButton = new JRadioButton("Подсветка");
        radioButton.setBounds(280, 5, 150, 20);
    }

    private void initButton() {
        button = new JButton("Старт");
        button.setBounds(5, 5, 100, 20);
        buttonColor = new JButton(("Цвет"));
        buttonColor.setBounds(5, 5, 100, 20);
    }

    private void initPanelMenu() {
        panelMenu = new JPanel(new GridLayout(1, 4));
        panelMenu.setName("panelMenu");
        //panelMenu.setBorder(BorderFactory.createBevelBorder(0));
    }

    private void initMap(int condition) {
        panelMap = new MapGame(this.HEIGHT - 57, this.WIDTH, condition);
        panelMap.setName("panelMap");
        addMouseListener();
    }

    private void initAlert(String[] s) {
        panelAlert = new Alert(s, this.HEIGHT - 57, this.WIDTH);
        panelAlert.setName("panelAlert");
    }

    private void centeringComponent(int sizeWidth, int sizeHeight, JComponent component, JFrame frame) {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (s.width - sizeWidth) / 2;
        int Y = (s.height - sizeHeight) / 2;
        if (frame == null) {
            component.setBounds(X, Y, sizeWidth, sizeHeight);
        } else {
            frame.setBounds(X, Y, sizeWidth, sizeHeight);
        }
    }

    private void listeners() {
        buttonColor.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapGame.color = Core.randomColor();
                renderJPanel();
            }
        });
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.winner = false;
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
                renderJPanel();
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
                HEIGHT = e.getComponent().getHeight();
                WIDTH = e.getComponent().getWidth();
                if (Core.startGame || Core.winGame) {
                    renderJPanel();
                } else {
                    renderNewGame();
                }
                super.componentResized(e);
            }
        });
    }

    private void addMouseListener() {
        panelMap.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Core.startGame) {
                    int x = (int) ((e.getX() - Core.leftX) / (Core.resizable / 9));
                    int y = (int) ((e.getY() - Core.upY) / (Core.resizable / 9));
                    Core.arrUserNumber[y][x] = Core.arrSetNext(Core.arrUserNumber[y][x]);
                }
                renderJPanel();
            }
        });
    }

    private void buttonUpdate() {
        Core.startGame = false;
        Core.startGame();
        renderJPanel();
    }

}


