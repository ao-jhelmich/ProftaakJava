package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EditPane {
    public void run ()
    {
        Collection<JComponent> componentList = new ArrayList<>();
        JFrame f = new JFrame("Add sphere");
        f.setSize(240, 270);
        f.setLocation(420, 350);

        ArrayList<String> shapeList = new ArrayList<>();
        shapeList.add("Cone");
        shapeList.add("Cube");
        shapeList.add("Cylinder");
        shapeList.add("Sphere");
        shapeList.add("SquarePyramid");

        JComboBox comboBox = new JComboBox(shapeList.toArray());
        comboBox.setBounds(20,5,200,50);
        componentList.add(comboBox);

        comboBox.addActionListener(e -> {
            String selectedSphere = (String) comboBox.getSelectedItem();
            System.out.println("Selected sphere: "+ selectedSphere);
        });

        JLabel radiusLabel = new JLabel("Radius: ");
        radiusLabel.setBounds(25,50,80,30);
        componentList.add(radiusLabel);

        JTextField radiusField = new JTextField();
        radiusField.setBounds(85,50,130,30);
        componentList.add(radiusField);

        JLabel widthLabel = new JLabel("Width: ");
        widthLabel.setBounds(25,85,80,30);
        componentList.add(widthLabel);

        JTextField widthfield = new JTextField();
        widthfield.setBounds(85,85,130,30);
        componentList.add(widthfield);

        JLabel heightLabel = new JLabel("Heigth: ");
        heightLabel.setBounds(25,125,80,30);
        componentList.add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setBounds(85,125,130,30);
        componentList.add(heightField);

        JLabel lengthLabel = new JLabel("Length: ");
        lengthLabel.setBounds(25,160,80,30);
        componentList.add(lengthLabel);

        JTextField lengthField = new JTextField();
        lengthField.setBounds(85,160,130,30);
        componentList.add(lengthField);

        final JButton button = new JButton("Save shape");
        button.addActionListener(e -> System.out.println("Save"));
        button.setBounds(85,200,130,30);
        componentList.add(button);

        for (JComponent c: componentList) {
            f.getContentPane().add(c);
        }

        f.setLayout(null);
        f.setVisible(true);
    }
}
