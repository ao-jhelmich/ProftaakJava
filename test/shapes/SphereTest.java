package shapes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void calculateVolume() {
        Sphere sphere = new Sphere(5);

        double result = sphere.calculateVolume();

        assertEquals((4 / 3.0) * Math.PI * (5 * 5 * 5), result);
    }

    @Test
    void getFormInputs() {
        Sphere sphere = new Sphere(5);

        ArrayList inputs = sphere.getFormInputs();

        assertEquals("[Radius:]", inputs.toString());
    }
}