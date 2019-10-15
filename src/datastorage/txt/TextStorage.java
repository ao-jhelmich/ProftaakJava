package datastorage.txt;

import datastorage.DataStorageInterface;
import shapes.Shape;

import java.util.ArrayList;

public class TextStorage implements DataStorageInterface {
    private Reader reader = new Reader();

    @Override
    public ArrayList<Shape> getAllShapes() {
        reader = new Reader();
        return reader.readAll();
    }

    @Override
    public void writeShape(Shape shape) {
        try (Writer writer = new Writer()) {
            if (shape.getId() != 0) {
                writer.update(shape);
            } else {
                reader = new Reader();
                int lastId = reader.getLastId();
                shape.setId(lastId);
                writer.write(shape.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteShape(Shape shape) {
        reader.close();

        try (Writer writer = new Writer()) {
            writer.deleteShape(shape);
        } catch (Exception e) {
            e.printStackTrace();
        }

        reader = new Reader();
    }
}
