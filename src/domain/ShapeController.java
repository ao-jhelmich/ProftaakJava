package domain;

import datastorage.txt.*;
import shapes.*;
import ui.EditPanel;
import ui.StorageFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeController {
    private Reader reader = new Reader();
    private Writer writer = new Writer();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StorageFrame(new ShapeController()));
    }

    public Shape getShape(String shapeString) {
        Shape shape = null;
        List<String> parts = new ArrayList<>(Arrays.asList(shapeString.split(":")));

        switch(parts.get(0)) {
            case "cone":
                if (parts.size() <= 1) {
                    shape = new Cone(0, 0);
                } else {
                    shape = new Cone(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)));
                }
                break;
            case "cube":
                if (parts.size() <= 1) {
                    shape = new Cube(0, 0, 0);
                } else {
                    shape = new Cube(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3)));
                }
                break;
            case "cylinder":
                if (parts.size() <= 1) {
                    shape = new Cylinder(0, 0);
                } else {
                    shape = new Cylinder(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)));
                }
                break;
            case "sphere":
                if (parts.size() <= 1) {
                    shape = new Sphere(0);
                } else {
                    shape = new Sphere(Double.parseDouble(parts.get(1)));
                }
                break;
            case "squarePyramid":
                if (parts.size() <= 1) {
                    shape = new SquarePyramid(0, 0, 0);
                } else {
                    shape = new SquarePyramid(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3)));
                }
                break;
        }

        return shape;
    }

    public void writeShape(Shape shape) {
        writer.writeShape(shape);
    }

    public void deleteShape(int selectedIndex) {
        File inputFile = new File("file.txt");
        File tempFile = new File("temp.txt");

        reader.close(); //TODO Fix delete function

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            datastorage.txt.Writer writer = new Writer(tempFile);

            String currentLine;
            int count = 0;

            while ((currentLine = bufferedReader.readLine()) != null) {
                if (count == selectedIndex) continue;
                System.out.println(count + " - " + selectedIndex);
                writer.write(currentLine);
                count++;
            }
            writer.closeWriter();
            bufferedReader.close();
            boolean success = tempFile.renameTo(inputFile);

            if (success) {
                System.out.println("Removed shape on line " + selectedIndex);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        reader = new datastorage.txt.Reader();
    }

    public ArrayList<Shape> getShapeList() {
        reader = new datastorage.txt.Reader();
        return reader.readAll();
    }

    public double calculateTotalVolume() {
        double totalVolume = 0.0;

        for (Shape shape : getShapeList()) {
            totalVolume += shape.calculateVolume();
        }

        return (double) Math.round(totalVolume * 100) / 100;
    }
}