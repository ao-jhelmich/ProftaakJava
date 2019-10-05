package shapes;

import ui.EditPanel;

import javax.swing.*;
import java.util.HashMap;

public class Sphere implements Shape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double calculateVolume() {
        return 4/3 * Math.PI * (radius * radius * radius);
    }

    @Override
    public void addFormInputs(HashMap<String, JComponent> componentList) {
        componentList.put("Radius label", EditPanel.newLabel("Radius", 25, 50));
        componentList.put("Radius textField", EditPanel.newTextField(85, 50));
    }

    @Override
    public String toString() {
        return "sphere:" + radius;
    }
}
