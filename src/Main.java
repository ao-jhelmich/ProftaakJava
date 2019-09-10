import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Cube cube = new Cube();
        Random r = new Random();

        System.out.println(cube.calculateVolume());
    }
}
