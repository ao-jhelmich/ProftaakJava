package domain;

import shapes.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader {
    private String filePath = "file.txt";

    private File file;

    private Scanner reader;

    public Reader() throws FileNotFoundException {
        this.file = new File(filePath);
        this.reader = new Scanner(file);
    }

    public ArrayList<Shape> readAll() {
        ArrayList<Shape> shapes = new ArrayList<>();
        while(reader.hasNextLine()) {
            List<String> parts = new ArrayList<>(Arrays.asList(reader.nextLine().split(":")));

            switch(parts.get(0)) {
                case "cone":
                    shapes.add(new Cone(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2))));
                    break;
                case "cube":
                    shapes.add(new Cube(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3))));
                    break;
                case "cylinder":
                    shapes.add(new Cylinder(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2))));
                    break;
                case "sphere":
                    shapes.add(new Sphere(Double.parseDouble(parts.get(1))));
                    break;
                case "squarePyramid":
                    shapes.add(new SquarePyramid(Double.parseDouble(parts.get(1)), Double.parseDouble(parts.get(2)), Double.parseDouble(parts.get(3))));
                    break;
            }
        }
        return shapes;
    }
}
