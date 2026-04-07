import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

class TextAreaToFile extends JFrame {
    private JTextArea textArea;
    private JButton saveButton;

    public TextAreaToFile() {
        setTitle("Запись текста в файл");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Создаём текстовую область
        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Создаём кнопку
        saveButton = new JButton("Сохранить в файл");

        // Обработчик кнопки
        saveButton.addActionListener(e -> saveToFile());

        // Добавляем компоненты
        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveToFile() {
        String text = textArea.getText();

        if (text.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Нет текста для сохранения!");
            return;
        }

        try (FileWriter writer = new FileWriter("diary_entry.txt")) {
            writer.write(text);
            JOptionPane.showMessageDialog(this, "Запись сохранена!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TextAreaToFile();
    }
}