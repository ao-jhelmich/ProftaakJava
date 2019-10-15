package shapes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConeTest {
    @Test
    void calculateVolume() {
        // Arrange
        Cone cone = new Cone(5,5);

        // Act
        double result = cone.calculateVolume();

        // Assert
        assertEquals( (1.0 / 3) * Math.PI * (5 * 5) * 5, result);
    }

    @Test
    void getFormInputs() {
        // Arrange
        Cone cone = new Cone(5,5);

        // Act
        ArrayList<String> result = cone.getFormInputs();

        // Assert
        assertEquals("[Radius:, Height:]", result.toString());
    }
}