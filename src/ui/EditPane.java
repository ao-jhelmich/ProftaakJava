package ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class EditPane {
    private Collection<JComponent> componentList;

    public EditPane() {
        this.componentList = new ArrayList<>();
    }

    public void run ()
    {
        JFrame f = new JFrame("Add shape");
        f.setSize(240, 250);
        f.setLocation(420, 350);

        ArrayList<String> shapeList = new ArrayList<>();
        shapeList.add("Cone");
        shapeList.add("Cube");
        shapeList.add("Cylinder");
        shapeList.add("Sphere");
        shapeList.add("SquarePyramid");

        JLabel shapeLabel = new JLabel("Shape type: ");
        shapeLabel.setBounds(25,15,80,30);
        componentList.add(shapeLabel);

        JComboBox shapeBox = new JComboBox(shapeList.toArray());
        shapeBox.setBounds(105,5,110,50);
        componentList.add(shapeBox);

        shapeBox.addActionListener(e -> {
            String selectedSphere = (String) shapeBox.getSelectedItem();
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

        JLabel heightLabel = new JLabel("Height: ");
        heightLabel.setBounds(25,120,80,30);
        componentList.add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setBounds(85,120,130,30);
        componentList.add(heightField);

        JLabel lengthLabel = new JLabel("Length: ");
        lengthLabel.setBounds(25,155,80,30);
        componentList.add(lengthLabel);

        JTextField lengthField = new JTextField();
        lengthField.setBounds(85,155,130,30);
        componentList.add(lengthField);

        final JButton button = new JButton("Save shape");
        button.addActionListener(e -> {
            f.setVisible(false);
            System.out.println("Save");
        });
        button.setBounds(82,190,138,30);
        componentList.add(button);

        for (JComponent c: componentList) {
            f.getContentPane().add(c);
        }

        f.setLayout(null);
        f.setVisible(true);
    }
}
