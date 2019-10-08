package ui;

import domain.ShapeController;
import domain.UpdatableView;
import shapes.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class EditPanel extends JPanel implements UpdatableView {
    private UIFrame uiFrame;
    private EditFrame editFrame;
    private ShapeController shapeController;

    private HashMap<String, JComponent> componentList = new HashMap<>(); //TODO Can we solve this in another way?

    private JComboBox shapeBox;

    private class ShapeBox {
        ShapeBox() {
            ArrayList<String> shapeList = new ArrayList<>();
            shapeList.add("cone");
            shapeList.add("cube");
            shapeList.add("cylinder");
            shapeList.add("sphere");
            shapeList.add("squarePyramid");

            componentList.put("Shape type", new Label("Shape type"));
            shapeBox = new JComboBox(shapeList.toArray());
            shapeBox.setBounds(105, 15, 110, 30);
            shapeBox.setEditable(false);
            componentList.put("shapeBox", shapeBox);

            shapeBox.addActionListener(e -> {
                Shape shape = shapeController.getShape(shapeBox.getSelectedItem().toString());
                clear();
                shape.addFormInputs(componentList);
                updateView();
            });
        }
    }

    private class InputPanel {
        InputPanel() {
            componentList.put("Radius label", new Label("Radius:"));
            componentList.put("Radius textField", newTextField(85, 50));

            componentList.put("Height label", new Label("Height:"));
            componentList.put("Height textField", newTextField(85, 120));
        }
    }

    private class ActionPanel {
        ActionPanel(EditPanel editPanel) {
            JButton button = new JButton("Save shape");
            button.addActionListener(e -> {
                System.out.println(shapeBox.getSelectedItem().toString());
                Shape shape = shapeController.getShape(shapeBox.getSelectedItem().toString());
                shapeController.writeShape(shape, editPanel);

                uiFrame.invalidate();
                uiFrame.validate();
                uiFrame.repaint();

                System.out.println("Save");
                editFrame.dispose();
            });
            button.setBounds(82, 190, 138, 30);
            componentList.put("Save shape", button);
        }
    }

    public EditPanel(UIFrame uiFrame, EditFrame editFrame, ShapeController shapeController) {
        this.uiFrame = uiFrame;
        this.editFrame = editFrame;
        this.shapeController = shapeController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        new ShapeBox();

        new InputPanel();

        new ActionPanel(this);

        updateView();
    }

    public static JLabel newLabel(String label, int x, int y) {
        JLabel radiusLabel = new JLabel(label + ": ");
        radiusLabel.setBounds(x, y, 80, 30);
        return radiusLabel;
    }

    public static JTextField newTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 130, 30);
        return textField;
    }

    private void clear() {
        removeAll();
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

    @Override
    public void updateView() {
        for (JComponent c : componentList.values()) { //TODO Add Shapetype first and cancel/save action last (change layout)
            add(c);
        }

        editFrame.repaint();
        editFrame.setLayout(null);
        editFrame.setVisible(true);
    }
}
