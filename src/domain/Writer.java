package domain;

import shapes.*;

import java.io.*;

public class Writer {
    private PrintWriter printWriter;

    public void write(String value){
        printWriter.println(value);
    }

    public void closeWriter(){
        this.printWriter.close();
    }

    public Writer() {
        File file = new File("file.txt");

        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    public void writeShape(String selectedShape, String radius, String height, String length, String width) {
        switch(selectedShape) {
            case "cone":
                this.write(
                        new Cone(Double.parseDouble(radius), Double.parseDouble(height)).toString()
                );
                break;
            case "cube":
                this.write(
                        new Cube(Double.parseDouble(length), Double.parseDouble(width), Double.parseDouble(height)).toString()
                );
                break;
            case "cylinder":
                this.write(
                        new Cylinder(Double.parseDouble(radius), Double.parseDouble(height)).toString()
                );
                break;
            case "sphere":
                this.write(
                        new Sphere(Double.parseDouble(radius)).toString()
                );
                break;
            case "squarePyramid":
                this.write(
                    new SquarePyramid(Double.parseDouble(length), Double.parseDouble(width), Double.parseDouble(length)).toString()
            );
                break;
        }

        this.closeWriter();
    }
}
