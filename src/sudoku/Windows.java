package sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Windows extends JFrame {

    private JPanel panel, panelMap;
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

    public JButton getButton() {
        return button;
    }

    public JButton getButtonColor() {
        return buttonColor;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public JRadioButton getRadioButton() {
        return radioButton;
    }

    public JFrame getFrame() {
        return this;
    }

    public Windows() {
        initComponent();
        addComponent();
    }

    public void renderJPanel() {
        if (Core.winSudoku()) {
            Core.startGame = false;
            this.add(alertWinner(), BorderLayout.CENTER);
        } else {
            this.add(map(), BorderLayout.CENTER);
        }
        this.validate();
    }

    private void addComponent() {
        panel.add(button);
        panel.add(radioButton);
        panel.add(buttonColor);
        panel.add(comboBox);
        this.add(panel, "North");
        JLabel jLabel = new JLabel();
        for (String s : alertNewGame) {
            jLabel.setText(s);
        }
        this.add(new Alert(alertNewGame), BorderLayout.CENTER);
    }

    private void initComponent() {
        initFrame();
        initPanel();
        initComboBox();
        initRadioButton();
        initButton();
    }

    private void initFrame() {
        this.setTitle("SUDOKU");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(Core.WIDTH, Core.HEIGHT));
        this.pack();
        centeringFrame(Core.WIDTH, Core.HEIGHT, this);
        this.setResizable(false);
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

    private void initPanel() {
        panel = new JPanel(new GridLayout(1, 4));
    }

    private JPanel map() {
        panelMap = new Draw();
        return panelMap;
    }

    private JPanel alertWinner() {
        panelMap = new Alert(alertWinner);
        return panelMap;
    }

    private void centeringFrame(int sizeWidth, int sizeHeight, JFrame frame) {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (s.width - sizeWidth) / 2;
        int Y = (s.height - sizeHeight) / 2;
        frame.setBounds(X, Y, sizeWidth, sizeHeight);
    }
}


