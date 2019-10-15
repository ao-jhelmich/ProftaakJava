package shapes;

import java.util.ArrayList;

public class Sphere implements Shape {
    private final static String type = "sphere";
    private int id;
    private double radius;

    public Sphere(double radius) {
       this(0, radius);
    }

    public Sphere(int id, double radius) {
        this.id = id;
        this.radius = radius;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getType() {
        return type;
    }

    public double calculateVolume() {
        return (4 / 3.0) * Math.PI * (radius * radius * radius);
    }

    @Override
    public ArrayList<String> getFormInputs() {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Radius:");
        return labels;
    }

    @Override
    public String toString() {
        return id + ":" + type + ":" + radius;
    }
}
