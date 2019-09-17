package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {

    public void run()
    {
        JFrame f = new JFrame("Sphere application");
        f.setSize(400, 300);

        final JTextArea textArea = new JTextArea(10, 40);
        f.getContentPane().add(BorderLayout.CENTER, textArea);

        final JButton button = new JButton("Add shape");
        button.addActionListener(e -> new EditPane().run());

        f.getContentPane().add(BorderLayout.NORTH, button);
        f.setVisible(true);
    }
}
