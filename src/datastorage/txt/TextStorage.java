package datastorage.txt;

import datastorage.DataStorageInterface;
import shapes.Shape;

import java.util.ArrayList;

public class TextStorage implements DataStorageInterface {
    private Reader reader = new Reader();
    private Writer writer = new Writer();

    @Override
    public ArrayList<Shape> getAllShapes() {
        reader = new Reader();
        return reader.readAll();
    }

    @Override
    public void writeShape(Shape shape) {
        //Check if shape exists
        writer.writeShape(shape);
    }

    @Override
    public void deleteShape(Shape shape) {
        reader.close();
        writer.deleteShape(shape);
        reader = new Reader();
    }
}
