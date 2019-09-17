package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class EditPane {
    public void run ()
    {
        JFrame f = new JFrame("Sphere application");
        f.setSize(300, 200);
        f.setLocation(420, 350);

        ArrayList<String> shapeList = new ArrayList<>();
        shapeList.add("Cone");
        shapeList.add("Cube");
        shapeList.add("Cylinder");
        shapeList.add("Sphere");
        shapeList.add("SquarePyramid");

        JComboBox comboBox = new JComboBox(shapeList.toArray());
        f.getContentPane().add(BorderLayout.NORTH, comboBox);

        String selectedSphere = (String) comboBox.getSelectedItem();
        comboBox.addActionListener(e -> System.out.println("Selected sphere: "+ selectedSphere));
//
//        JTextField radiusField = new JTextField();
//        f.getContentPane().add(radiusField);
//
//        JTextField widthfield = new JTextField();
//        f.getContentPane().add(widthfield);
//
//        JTextField heightField = new JTextField();
//        f.getContentPane().add(heightField);

        JTextField lengthField = new JTextField();
        lengthField.setPreferredSize(new Dimension(10, 10));

        f.getContentPane().add(lengthField, BorderLayout.LINE_START);

        f.setVisible(true);
    }
}
