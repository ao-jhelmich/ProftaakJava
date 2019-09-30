package domain;

import shapes.*;
import ui.EditPane;

import java.io.*;

public class Writer {
    private PrintWriter printWriter;

    public void write(String value){
        System.out.println("Added: " + value);
        printWriter.println(value);
    }

    public void closeWriter(){
        printWriter.close();
    }

    public Writer() {
        File file = new File("file.txt");

        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    public void writeShape (Shape shape, EditPane editPane) {
        if (shape instanceof Cone) {
            write(
                new Cone(
                    Double.parseDouble(editPane.getText("Radius textField")),
                    Double.parseDouble(editPane.getText("Height textField"))
                ).toString()
            );
        } else if (shape instanceof Cube) {
            write(
                new Cube(
                    Double.parseDouble(editPane.getText("Length textField")),
                    Double.parseDouble(editPane.getText("Width textField")),
                    Double.parseDouble(editPane.getText("Height textField"))
                ).toString()
            );
        } else if (shape instanceof Cylinder) {
            write(
                new Cylinder(
                    Double.parseDouble(editPane.getText("Radius textField")),
                    Double.parseDouble(editPane.getText("Height textField"))
                ).toString()
            );
        } else if (shape instanceof Sphere) {
            write(
                    new Sphere(
                            Double.parseDouble(editPane.getText("Radius textField"))
                    ).toString()
            );
        } else if (shape instanceof SquarePyramid) {
            write(
                    new SquarePyramid(
                            Double.parseDouble(editPane.getText("Length textField")),
                            Double.parseDouble(editPane.getText("Width textField")),
                            Double.parseDouble(editPane.getText("Height textField"))
                    ).toString()
            );
        }

        closeWriter();
    }
}
