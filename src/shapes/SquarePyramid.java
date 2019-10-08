package shapes;

import ui.EditPanel;

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

    public double calculateVolume() {
        return (1.0 / 3) * length * width * height;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void addFormInputs(HashMap<String, JComponent> componentList) {
        componentList.put("Width label", EditPanel.newLabel("Width", 25, 85));
        componentList.put("Width textField", EditPanel.newTextField(85, 85));

        componentList.put("Height label", EditPanel.newLabel("Height", 25, 120));
        componentList.put("Height textField", EditPanel.newTextField(85, 120));

        componentList.put("Length label", EditPanel.newLabel("Length", 25, 155));
        componentList.put("Length textField", EditPanel.newTextField(85, 155));
    }

    @Override
    public String toString() {
        return "squarePyramid:" + length + ":" + width + ":" + height;
    }
}
