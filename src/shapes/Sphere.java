package shapes;

import ui.EditPane;

import javax.swing.*;
import java.util.HashMap;

public class Sphere implements Shape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double calculateVolume() {
        return 4/3 * Math.PI * (radius * radius * radius);
    }

    @Override
    public void addFormInputs(HashMap<String, JComponent> componentList) {
        EditPane editPane = new EditPane(new JFrame());
        componentList.put("Radius label", editPane.newLabel("Radius", 25, 50));
        componentList.put("Radius textField", editPane.newTextField(85, 50));
    }

    @Override
    public String toString() {
        return "sphere:" + radius;
    }
}
