package shapes;

public class Cube implements Shape {
    private double length;

    private double width;

    private double height;

    public Cube(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double calculateVolume() {
        return length * width * height;
    }

    @Override
    public String toString() {
        return "cube:" + length + ":" + width + ":" + height;
    }
}