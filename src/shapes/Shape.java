package shapes;

import javax.swing.*;
import java.util.HashMap;

public interface Shape {
    double calculateVolume();
    void addFormInputs(HashMap<String, JComponent> componentList);
}
