package ui;

import domain.ShapeController;
import domain.Writer;
import shapes.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EditPane {
    private HashMap<String, JComponent> componentList;
    private Writer writer;
    private JFrame parentFrame;
    private ShapeController shapeController;

    public EditPane(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.writer = new Writer();
        this.componentList = new HashMap<>();
        this.shapeController = new ShapeController();
    }

    public void run() {
        JFrame f = new JFrame("Add shape");
        f.setSize(240, 280);
        f.setLocation(420, 350);

        ArrayList<String> shapeList = new ArrayList<>();
        shapeList.add("cone");
        shapeList.add("cube");
        shapeList.add("cylinder");
        shapeList.add("sphere");
        shapeList.add("squarePyramid");

        componentList.put("Shape type", newLabel("Shape type", 25, 15));
        JComboBox shapeBox = new JComboBox(shapeList.toArray());
        shapeBox.setBounds(105, 15, 110, 30);
        shapeBox.setEditable(false);
        componentList.put("shapeBox", shapeBox);

        shapeBox.addActionListener(e -> {
            Shape shape = shapeController.getShape(shapeBox.getSelectedItem().toString());
            clear(f);
            shape.addFormInputs(componentList);
            show(f);
        });

        componentList.put("Radius label", newLabel("Radius", 25, 50));
        componentList.put("Radius textField", newTextField(85, 50));

        componentList.put("Height label", newLabel("Height", 25, 120));
        componentList.put("Height textField", newTextField(85, 120));

        JButton button = new JButton("Save shape");
        button.addActionListener(e -> {
            Shape shape = shapeController.getShape(shapeBox.getSelectedItem().toString());
            writer.writeShape(shape, this);

            parentFrame.invalidate();
            parentFrame.validate();
            parentFrame.repaint();

            System.out.println("Save");
            f.dispose();
        });
        button.setBounds(82, 190, 138, 30);
        componentList.put("Save shape", button);

        show(f);
    }

    public JLabel newLabel(String label, int x, int y) {
        JLabel radiusLabel = new JLabel(label + ": ");
        radiusLabel.setBounds(x, y, 80, 30);
        return radiusLabel;
    }

    public JTextField newTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 130, 30);
        return textField;
    }

    private void show(JFrame f) {
        for (JComponent c : componentList.values()) {
            f.getContentPane().add(c);
        }

        f.repaint();
        f.setLayout(null);
        f.setVisible(true);
    }

    private void clear(JFrame f) {
        f.getContentPane().removeAll();
        componentList.remove("Radius label");
        componentList.remove("Width label");
        componentList.remove("Height label");
        componentList.remove("Length label");
        componentList.remove("Radius textField");
        componentList.remove("Width textField");
        componentList.remove("Height textField");
        componentList.remove("Length textField");
    }

    public String getText(String textField) {
        if (componentList.containsKey(textField) && componentList.get(textField) instanceof JTextField) {
            JTextField component = (JTextField) componentList.get(textField);
            return component.getText();
        } else {
            return "";
        }
    }
}
