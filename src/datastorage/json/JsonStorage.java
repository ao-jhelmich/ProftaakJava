package datastorage.json;

import datastorage.DataStorageInterface;
import shapes.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JsonStorage implements DataStorageInterface {

    File file = new File("file.json");
//    FileWriter fileWriter = new FileWriter(file);

    @Override
    public ArrayList<Shape> getAllShapes() {
        try {
            return new Reader().readAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeShape(Shape shape) {
        try {
            PrintWriter printWriter = new PrintWriter(this.file);
            printWriter.append(shape.toJsonString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteShape(Shape shape) {

    }
}