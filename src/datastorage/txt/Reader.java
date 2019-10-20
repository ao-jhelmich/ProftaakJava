package datastorage.txt;

import domain.ShapeController;
import shapes.Shape;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader implements Closeable {
    private Scanner reader;

    public Reader() {
        this(new File("file.txt"));
    }

    public Reader(File file) {
        try {
            this.reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> readProperties() {
        HashMap<String, String> properties = new HashMap<>();

        while (reader.hasNextLine()) {
            String prop = reader.nextLine();
            List<String> parts = new ArrayList<>(Arrays.asList(prop.split("=", 2)));

            if (parts.size() <= 1) {
                properties.put(parts.get(0), "");
            } else {
                properties.put(parts.get(0), parts.get(1));
            }
        }

        return properties;
    }

    public ArrayList<Shape> readAll() {
        ArrayList<Shape> shapes = new ArrayList<>();
        ShapeController shapeController = new ShapeController();

        while (reader.hasNextLine()) {
            shapes.add(shapeController.getShape(reader.nextLine()));
        }

        return shapes;
    }

    public int getLastId() {
        ArrayList<Shape> shapes = readAll();
        if (shapes.size() > 0) {
            shapes.sort(Collections.reverseOrder());
            return shapes.get(0).getId() + 1;
        } else {
            return 1;
        }
    }

    @Override
    public void close() {
        reader.close();
    }
}
