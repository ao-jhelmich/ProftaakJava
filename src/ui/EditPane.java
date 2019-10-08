package ui;

import datastorage.ShapeDAO;
import domain.Writer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EditPane {
    private HashMap<String, JComponent> componentList;
    private Writer writer;
    private JFrame parentFrame;
    private ShapeDAO dao;

    public EditPane(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.writer = new Writer();
        this.dao = new ShapeDAO();
        this.componentList = new HashMap<>();
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
            String selectedSphere = shapeBox.getSelectedItem().toString();
        });

        componentList.put("Radius label", newLabel("Radius", 25, 50));
        componentList.put("Radius textField", newTextField(85, 50));

        componentList.put("Width label", newLabel("Width", 25, 85));
        componentList.put("Width textField", newTextField(85, 85));

        componentList.put("Height label", newLabel("Height", 25, 120));
        componentList.put("Height textField", newTextField(85, 120));

        componentList.put("Length label", newLabel("Length", 25, 155));
        componentList.put("Length textField", newTextField(85, 155));

        JButton button = new JButton("Save shape");
        button.addActionListener(e -> {
            writer.writeShape(shapeBox.getSelectedItem().toString(), getText("Radius textField"), getText("Width textField"), getText("Height textField"), getText("Length textField"));
            dao.saveShape(shapeBox.getSelectedItem().toString(), getText("Radius textField"), getText("Width textField"), getText("Height textField"), getText("Length textField"));

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

    private JLabel newLabel(String label, int x, int y) {
        JLabel radiusLabel = new JLabel(label + ": ");
        radiusLabel.setBounds(x, y, 80, 30);
        return radiusLabel;
    }

    private JLabel newLabel(String label, int x, int y, int width, int height) {
        JLabel radiusLabel = new JLabel(label + ": ");
        radiusLabel.setBounds(x, y, width, height);
        return radiusLabel;
    }

    private JTextField newTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 130, 30);
        return textField;
    }

    private void show(JFrame f) {
        for (JComponent c : componentList.values()) {
            f.getContentPane().add(c);
        }

        f.setLayout(null);
        f.setVisible(true);
    }

    private String getText(String textField) {
        if (componentList.containsKey(textField) && componentList.get(textField) instanceof JTextField) {
            JTextField component = (JTextField) componentList.get(textField);
            return component.getText();
        } else {
            return "";
        }
    }
}
