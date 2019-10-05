package shapes;

import ui.EditPanel;

import javax.swing.*;
import java.util.HashMap;

public class Cone implements Shape {
    private double radius;

    private double height;

    public Cone(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double calculateVolume() {
        return (1.0 / 3) * Math.PI * (radius * radius) * height;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void addFormInputs(HashMap<String, JComponent> componentList) {
        componentList.put("Height label", EditPanel.newLabel("Height", 25, 120));
        componentList.put("Height textField", EditPanel.newTextField(85, 120));
        componentList.put("Radius label", EditPanel.newLabel("Radius", 25, 50));
        componentList.put("Radius textField", EditPanel.newTextField(85, 50));
    }

    @Override
    public String toString() {
        return "cone:" + radius + ":" + height;
    }
}
