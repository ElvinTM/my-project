package org.example;

import org.example.model.CharCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public static void main(String[] args) {
        converter();
    }

    public static void converter()  {

        JFrame f = new JFrame("Конвертер валют");

        CurrencyCalculation calculation = new CurrencyCalculation();

        JComboBox comboBox = new JComboBox(CharCode.values());
        JComboBox comboBox2 = new JComboBox(CharCode.values());

        JLabel l1 = new JLabel("Введите первое значение");
        JLabel l2 = new JLabel("Введите второе значение");

        JButton b = new JButton("Обновить");

        JTextArea t1 = new JTextArea("");
        JFormattedTextField t2 = new JFormattedTextField("");
        JTextField t3 = new JTextField("0");

        comboBox.setBounds(1, 1, 60, 30);
        comboBox2.setBounds(1, 40, 60, 30);
        t3.setBounds(70, 1, 160, 30);

        t1.setBounds(80, 40, 50, 30);
        t2.setBounds(240, 40, 50, 30);

        b.setBounds(150, 150, 100, 30);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeOne = comboBox.getSelectedItem().toString();
                String codeToo = comboBox2.getSelectedItem().toString();
                calculation.calculations(codeOne, codeToo);
                String result = String.valueOf(calculation);
                t3.setText(result);
                t1.setText(codeOne);
                t2.setText(codeToo);
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.add(comboBox);
        f.add(comboBox2);
        f.add(t3);

        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(t2);

        f.add(b);

        f.setLayout(null);
        f.setSize(400, 300);
        f.setVisible(true);
    }
}