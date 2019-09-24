package domain;

import shapes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeController {
    public void save(String name, String type) {
        //TODO Implement save method here
    }

    public void delete(String name) {
        //TODO Implement delete method here
    }

    public Shape getShape(String shapeString) {
        Shape shape = null;
        List<String> parts = new ArrayList<>(Arrays.asList(shapeString.split(":")));

        switch(parts.get(0)) {
            case "cone":
                shape = new Cone(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)));
                break;
            case "cube":
                shape = new Cube(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3)));
                break;
            case "cylinder":
                shape = new Cylinder(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)));
                break;
            case "sphere":
                shape = new Sphere(Double.parseDouble(parts.get(1)));
                break;
            case "squarePyramid":
                shape = new SquarePyramid(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3)));
                break;
        }

        return shape;
    }
}