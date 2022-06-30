package sudoku;

import javax.swing.*;
import java.awt.*;


public class Windows extends JFrame {

    private JPanel panel;
    private JComboBox comboBox;
    private JRadioButton radioButton;
    private JButton button;

    public JButton getButton() {
        return button;
    }
    public JComboBox getComboBox() {
        return comboBox;
    }
    public JRadioButton getRadioButton() {
        return radioButton;
    }
    public JFrame getFrame (){
        return this;
    }

    public Windows() {

        try {
            render();
        } catch (HeadlessException e) {
            alert("Ошибка", "Не удалось запустить приложение!");
        }
    }

    public void render() {
        initComponent();
        addComponent();
    }

    private void addComponent( ) {
        panel.add(button);
        panel.add(radioButton);
        panel.add(comboBox);
        this.add(panel, "North");
        this.add(map(), "Center");

    }

    private void initFrame() {
        this.setTitle("SUDOKU");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(611, 660));
        this.pack();
        this.setResizable(false);
    }
    private void initComponent() {
        initFrame();
        initPanel();
        initComboBox();
        initRadioButton();
        initButton();
    }
    private void initComboBox() {
        String courses[] = {"Легко", "Нормально", "Тяжело", "Очень тяжело"};
        comboBox = new JComboBox(courses);
        comboBox.setBounds(120, 5, 150, 20);
    }
    private void initRadioButton() {
        radioButton = new JRadioButton("Подсказки");
        radioButton.setBounds(280, 5, 150, 20);
    }
    private void initButton() {
        button = new JButton("Обновить");
        button.setBounds(5, 5, 100, 20);
    }
    private void initPanel() {
        panel = new JPanel(new GridLayout(1,3));
    }

    private JPanel map( ) {
        Draw draw = new Draw();
        return draw;
    }

    public void alert(String title, String meseg) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(meseg);
//        alert.showAndWait();
        System.out.println(title + " " + meseg);
    }

}
