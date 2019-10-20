package datastorage;

import shapes.Shape;

import java.util.ArrayList;

public interface DataStorageInterface {
    ArrayList<Shape> getAllShapes();

    void writeShape(Shape shape); //New or existing shape

    void deleteShape(Shape shape);
}