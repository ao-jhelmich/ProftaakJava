package domain;

import datastorage.DataStorageInterface;
import datastorage.json.JsonStorage;
import datastorage.sql.ShapeDAO;
import datastorage.txt.TextStorage;
import shapes.Shape;
import shapes.*;
import ui.StorageFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeController {
    private DataStorageInterface dataStorageInterface;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StorageFrame(new ShapeController()));
    }

    public void setDataOption(String dataOption) {
        switch (dataOption) {
            case "sql":
                dataStorageInterface = new ShapeDAO();
                break;
            case "txt":
                dataStorageInterface = new TextStorage();
                break;
            case "json":
                dataStorageInterface = new JsonStorage();
                break;
            default:
                dataStorageInterface = new TextStorage();
                break;
        }
    }

    public Shape getShape(String shapeString) {
        Shape shape = null;
        List<String> parts = new ArrayList<>(Arrays.asList(shapeString.split(":")));

        if (parts.size() > 0) {
            int id = Integer.parseInt(parts.get(0));

            switch (parts.get(1)) {
                case "cone":
                    shape = new Cone(id, Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3)));
                    break;
                case "cube":
                    shape = new Cube(id, Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3)), Double.parseDouble(parts.get(4)));
                    break;
                case "cylinder":
                    shape = new Cylinder(id, Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3)));
                    break;
                case "sphere":
                    shape = new Sphere(id, Double.parseDouble(parts.get(2)));
                    break;
                case "squarePyramid":
                    shape = new SquarePyramid(id, Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3)), Double.parseDouble(parts.get(4)));
                    break;
            }
        }

        return shape;
    }

    public Shape getShape(Shape shape, ArrayList<Component> components) {
        int id;
        if (shape != null) {
            id = shape.getId();
        } else {
            id = 0;
        }

        StringBuilder shapeString = new StringBuilder().append(id).append(":");

        for (Component c : components) {
            if (c instanceof JComboBox) {
                shapeString.append(((JComboBox) c).getSelectedItem().toString());
            }
        }

        for (Component c : components) {
            if (c instanceof JTextField) {
                shapeString.append(":").append(((JTextField) c).getText());
            }
        }

        return getShape(shapeString.toString());
    }

    public Shape getEmptyShape(String shapeString) {
        Shape shape = null;
        List<String> parts = new ArrayList<>(Arrays.asList(shapeString.split(":")));

        if (parts.size() == 1) {
            switch (parts.get(0)) {
                case "cone":
                    shape = new Cone(0, 0);
                    break;
                case "cube":
                    shape = new Cube(0, 0, 0);
                    break;
                case "cylinder":
                    shape = new Cylinder(0, 0);
                    break;
                case "sphere":
                    shape = new Sphere(0);
                    break;
                case "squarePyramid":
                    shape = new SquarePyramid(0, 0, 0);
                    break;
            }
        }

        return shape;
    }

    public ArrayList<Shape> getShapeList() {
        return dataStorageInterface.getAllShapes();
    }

    public void writeShape(Shape shape) {
        dataStorageInterface.writeShape(shape);
    }

    public void deleteShape(Shape shape) {
        dataStorageInterface.deleteShape(shape);
    }

    public double calculateTotalVolume() {
        double totalVolume = 0.0;

        for (Shape shape : getShapeList()) {
            totalVolume += shape.calculateVolume();
        }

        return (double) Math.round(totalVolume * 100) / 100;
    }
}