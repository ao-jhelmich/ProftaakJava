package shapes;

import java.util.ArrayList;

public interface Shape {
    int getId();
    void setId(int id);
    String getType();
    double calculateVolume();
    ArrayList<String> getFormInputs();
}
