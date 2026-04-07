import javax.swing.*;
import java.awt.*;

public class Button1 {
    public static void main (String[] args){
        Button1 gui = new Button1();
        gui.go();
    }
    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        frame.getContentPane().add(BorderLayout.SOUTH,panel);
        JButton button = new JButton("ETO KAKOI TO SPUN");
        JButton button2 = new JButton("shock me2333333333333");
        panel.add(BorderLayout.EAST,button2);
        panel.setSize(600,100);
        frame.getContentPane().add(BorderLayout.NORTH,button);
        frame.setSize(1200,200);;
        frame.setVisible(true);
        JTextField textField = new JTextField();
        textField.setColumns(20);           // ширина в символах (не пикселях)
        textField.setText("Текст по умолчанию");
        textField.setEditable(true);
        textField.setVisible(true);
    }
}
