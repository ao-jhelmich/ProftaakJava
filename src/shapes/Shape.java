package shapes;

import java.util.ArrayList;

public interface Shape extends Comparable<Shape> {
    int getId();
    void setId(int id);
    String getType();
    double calculateVolume();
    ArrayList<String> getFormInputs();
    int compareTo(Shape that);
    String toJsonString();
}
