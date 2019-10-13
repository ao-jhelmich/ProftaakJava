package datastorage;

import shapes.Shape;

public interface DataStorageInterface {
    void writeShape(Shape shape);
    void deleteShape(Shape shape);
}