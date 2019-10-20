package shapes;

import java.util.ArrayList;

public class SquarePyramid implements Shape {
    private final static String type = "squarePyramid";
    private int id;
    private double length;
    private double width;
    private double height;

    public SquarePyramid(double length, double width, double height) {
        this(0, length, width, height);
    }

    public SquarePyramid(int id, double length, double width, double height) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String getType() {
        return type;
    }

    public double calculateVolume() {
        return (1.0 / 3) * length * width * height;
    }

    @Override
    public int compareTo(Shape that) {
        return this.getId() - that.getId();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public ArrayList<String> getFormInputs() {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Length:");
        labels.add("Width:");
        labels.add("Height:");
        return labels;
    }

    @Override
    public String toString() {
        return id + ":" + type + ":" + length + ":" + width + ":" + height;
    }

    @Override
    public String toJsonString() {
        return "{ \"id\":" + id + ", \"type\":\"" + type + "\", \"length\":" + length + ", \"width\": " + width + ", \"height\":" + height + "}";
    }
}
