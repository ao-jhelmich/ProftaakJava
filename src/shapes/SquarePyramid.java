package shapes;

import ui.EditPane;

import javax.swing.*;
import java.util.HashMap;

public class SquarePyramid implements Shape {
    private double length;

    private double width;

    private double height;

    public SquarePyramid(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double calculateVolume() {
        return (1.0 / 3) * length * width * height;
    }

    @Override
    public void addFormInputs(HashMap<String, JComponent> componentList) {
        EditPane editPane = new EditPane(new JFrame());
        componentList.put("Width label", editPane.newLabel("Width", 25, 85));
        componentList.put("Width textField", editPane.newTextField(85, 85));

        componentList.put("Height label", editPane.newLabel("Height", 25, 120));
        componentList.put("Height textField", editPane.newTextField(85, 120));

        componentList.put("Length label", editPane.newLabel("Length", 25, 155));
        componentList.put("Length textField", editPane.newTextField(85, 155));
    }

    @Override
    public String toString() {
        return "squarePyramid:" + length + ":" + width + ":" + height;
    }
}
