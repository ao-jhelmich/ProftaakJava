package datastorage.json;

import datastorage.DataStorageInterface;
import shapes.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class JsonStorage implements DataStorageInterface {
    File file = new File("file.json");
//    FileWriter fileWriter = new FileWriter(file);
    Reader reader;

    @Override
    public ArrayList<Shape> getAllShapes() {
        try {
            return new Reader().readAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeShape(Shape shape) {
        try {
            Writer writer = new Writer();
            if (shape.getId() != 0) {
                writer.update(shape);
            } else {
                reader = new datastorage.json.Reader();
                int lastId = reader.getLastId();
                shape.setId(lastId);
                writer.write(shape.toJsonString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteShape(Shape shape) {

    }
}
