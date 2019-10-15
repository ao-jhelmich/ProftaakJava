package shapes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void calculateVolume() {
        Cylinder cylinder = new Cylinder(5,5);

        double result = cylinder.calculateVolume();

        assertEquals(Math.PI * (5 * 5) * 5, result);
    }

    @Test
    void getFormInputs() {
        Cylinder cylinder = new Cylinder(5,5);

        ArrayList inputs = cylinder.getFormInputs();

        assertEquals("[Radius:, Height:]", inputs.toString());
    }
}