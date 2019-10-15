package shapes;

import java.util.ArrayList;

public class Cylinder implements Shape {
    private final static String type = "cylinder";
    private int id;
    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
       this(0, radius, height);
    }

    public Cylinder(int id, double radius, double height) {
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
        return Math.PI * (radius * radius) * height;
    }

    @Override
    public ArrayList<String> getFormInputs() {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Radius:");
        labels.add("Height:");
        return labels;
    }

    @Override
    public String toString() {
        return id + ":" + type + ":" + radius + ":" + height;
    }
}
