package shapes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest {
    @Test
    void calculateVolume() {
        // Arrange
        Cube cube = new Cube(2,2,2);

        // Act
        double result = cube.calculateVolume();

        // Assert
        assertEquals( 8, result);
    }

    @Test
    void getFormInputs() {
        // Arrange
        Cube cube = new Cube(5,5, 5);

        // Act
        ArrayList<String> result = cube.getFormInputs();

        // Assert
        assertEquals("[Length:, Width:, Height:]", result.toString());
    }
}