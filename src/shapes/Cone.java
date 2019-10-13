package shapes;

import java.util.ArrayList;

public class Cone implements Shape {
    private final static String type = "cone";
    private int id;
    private double radius;
    private double height;

    public Cone(double radius, double height) {
        this(0, radius, height);
    }

    public Cone(int id, double radius, double height) {
        this.id = id;
        this.radius = radius;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getType() {
        return type;
    }

    public double calculateVolume() {
        return (1.0 / 3) * Math.PI * (radius * radius) * height;
    }

    @Override
    public ArrayList<String> getFormInputs() {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Height:");
        labels.add("Radius:");
        return labels;
    }

    @Override
    public String toString() {
        return id + ":" + type + ":" + radius + ":" + height;
    }
}
