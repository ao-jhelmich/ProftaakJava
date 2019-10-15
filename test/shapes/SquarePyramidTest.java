package shapes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SquarePyramidTest {

    @Test
    void calculateVolume() {
        SquarePyramid squarePyramid = new SquarePyramid (5,5,5);

        double result = squarePyramid.calculateVolume();

        assertEquals((1.0 / 3) * 5 * 5 * 5, result);
    }

    @Test
    void getFormInputs() {
        SquarePyramid squarePyramid = new SquarePyramid(5,5,5);

        ArrayList inputs = squarePyramid.getFormInputs();

        assertEquals("[Length:, Width:, Height:]", inputs.toString());
    }
}