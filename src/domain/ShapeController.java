package domain;

import datastorage.sql.ShapeDAO;
import datastorage.txt.Reader;
import datastorage.txt.Writer;
import shapes.Shape;
import shapes.*;
import ui.StorageFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeController {
    private String dataOption;
    private Reader reader = new Reader();
    private Writer writer = new Writer();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StorageFrame(new ShapeController()));
    }

    public void setDataOption(String dataOption) {
        this.dataOption = dataOption;
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

    public Shape getShape(String shapeString, ArrayList<Component> components) {
        StringBuilder shape = new StringBuilder("0:").append(shapeString);

        for (Component c: components) {
            if (c instanceof JTextField) {
                shape.append(":").append(((JTextField) c).getText());
            }
        }

        return getShape(shape.toString());
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

    public void writeShape(Shape shape) {
        System.out.println(shape);
        if (dataOption != null) {
            switch (dataOption) {
                case "db":
                    new ShapeDAO().writeShape(shape);
                    // Todo reload list of all shapes
                    break;
                case "txt":
                    writer.writeShape(shape);
                    break;
                case "json":
                    //TODO Implement write to json here
                    break;
            }
        }
    }

    public void deleteShape(Shape shape) {
        if (dataOption != null) {
            switch (dataOption) {
                case "db":
                    new ShapeDAO().deleteShape(shape);
                    break;
                case "txt":
                    reader = writer.deleteShape(shape, reader);
                    break;
                case "json":
                    //TODO Implement delete from json here
                    break;
            }
        }
    }

    public ArrayList<Shape> getShapeList() {
        if (dataOption != null) {
            switch (dataOption) {
                case "db":
                    return new ShapeDAO().all();
                case "txt":
                    reader = new Reader();
                    return reader.readAll();
                case "json":
                    //TODO Implement get all Shapes from json here
                    return new ArrayList<>();
                default:
                    return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    public double calculateTotalVolume() {
        double totalVolume = 0.0;

        for (Shape shape : getShapeList()) {
            totalVolume += shape.calculateVolume();
        }

        return (double) Math.round(totalVolume * 100) / 100;
    }
}