package shapes;

import java.util.ArrayList;

public interface Shape {
    int getId();
    String getType();
    double calculateVolume();
    ArrayList<String> getFormInputs();
}
