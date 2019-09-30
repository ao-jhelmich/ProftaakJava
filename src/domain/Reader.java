package domain;

import shapes.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader {
    private String filePath = "file.txt";

    private File file;

    private Scanner reader;

    public Reader() {
        this.file = new File(filePath);
        try {
            this.reader = new Scanner(file);
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }
    }

    public ArrayList<Shape> readAll() {
        ArrayList<Shape> shapes = new ArrayList<>();
        ShapeController shapeController = new ShapeController();

        while(reader.hasNextLine()) {
            shapes.add(shapeController.getShape(reader.nextLine()));
        }

        return shapes;
    }
}
