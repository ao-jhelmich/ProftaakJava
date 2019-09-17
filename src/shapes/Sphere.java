package shapes;

public class Sphere implements Shape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double calculateVolume() {
        return 4/3 * Math.PI * (radius * radius * radius);
    }

    @Override
    public String toString() {
        return "sphere:" + radius;
    }
}
