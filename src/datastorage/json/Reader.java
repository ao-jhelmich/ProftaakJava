package datastorage.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import shapes.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Reader implements Closeable {

    private Scanner reader;

    public ArrayList<Shape> readAll() throws FileNotFoundException {
        File file = new File("file.json");
        ArrayList<Shape> shapes = new ArrayList<>();
        this.reader = new Scanner(new FileReader(file));

        while (reader.hasNextLine()) {
            JsonObject jsonObject = new Gson().fromJson(reader.nextLine(), JsonObject.class);
            shapes.add(convertToShape(jsonObject));
        }

        reader.close();

        return shapes;
    }

    private Shape convertToShape(JsonObject jsonObject) {
        Shape shape = null;

        System.out.println(jsonObject);
        int id = jsonObject.get("id") == null ? -1 : jsonObject.get("id").getAsInt();
        String type = jsonObject.get("type") == null ? null : jsonObject.get("type").getAsString();
        double length = jsonObject.get("length") == null ? -1 : jsonObject.get("length").getAsDouble();
        double width = jsonObject.get("width") == null ? -1 : jsonObject.get("width").getAsDouble();
        double height = jsonObject.get("height") == null ? -1 : jsonObject.get("height").getAsDouble();
        double radius = jsonObject.get("radius") == null ? -1 : jsonObject.get("radius").getAsDouble();

        switch (type) {
            case "cone":
                shape = new Cone(id, radius, height);
                break;
            case "cube":
                shape = new Cube(id, length, width, height);
                break;
            case "cylinder":
                shape = new Cylinder(id, radius, height);
                break;
            case "sphere":
                shape = new Sphere(id, radius);
                break;
            case "squarePyramid":
                shape = new SquarePyramid(id, length, width, height);
                break;
        }

        return shape;
    }

    public int getLastId() throws FileNotFoundException {
        ArrayList<Shape> shapes = this.readAll();
        if (shapes.size() > 0) {
            shapes.sort(Collections.reverseOrder());
            return shapes.get(0).getId() + 1;
        } else {
            return 1;
        }
    }

    @Override
    public void close() {
        this.reader.close();
    }
}
