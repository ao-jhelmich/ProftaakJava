package shapes;

import ui.EditPane;

import javax.swing.*;
import java.util.HashMap;

public class Cylinder implements Shape {
    private double radius;

    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double calculateVolume() {
        return Math.PI * (radius * radius) * height;
    }

    @Override
    public void addFormInputs(HashMap<String, JComponent> componentList) {
        EditPane editPane = new EditPane(new JFrame());
        componentList.put("Height label", editPane.newLabel("Height", 25, 120));
        componentList.put("Height textField", editPane.newTextField(85, 120));
        componentList.put("Radius label", editPane.newLabel("Radius", 25, 50));
        componentList.put("Radius textField", editPane.newTextField(85, 50));
    }

    @Override
    public String toString() {
        return "cylinder:" + radius + ":" + height;
    }
}
