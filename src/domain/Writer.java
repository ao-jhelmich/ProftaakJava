package domain;

import shapes.*;
import ui.EditPanel;

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
        this(new File("file.txt"));
    }

    public Writer(File file) {
        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    public void writeShape (Shape shape, EditPanel editPanel) {
        if (shape instanceof Cone) {
            write(
                new Cone(
                    Double.parseDouble(editPanel.getText("Radius textField")),
                    Double.parseDouble(editPanel.getText("Height textField"))
                ).toString()
            );
        } else if (shape instanceof Cube) {
            write(
                new Cube(
                    Double.parseDouble(editPanel.getText("Length textField")),
                    Double.parseDouble(editPanel.getText("Width textField")),
                    Double.parseDouble(editPanel.getText("Height textField"))
                ).toString()
            );
        } else if (shape instanceof Cylinder) {
            write(
                new Cylinder(
                    Double.parseDouble(editPanel.getText("Radius textField")),
                    Double.parseDouble(editPanel.getText("Height textField"))
                ).toString()
            );
        } else if (shape instanceof Sphere) {
            write(
                    new Sphere(
                            Double.parseDouble(editPanel.getText("Radius textField"))
                    ).toString()
            );
        } else if (shape instanceof SquarePyramid) {
            write(
                    new SquarePyramid(
                            Double.parseDouble(editPanel.getText("Length textField")),
                            Double.parseDouble(editPanel.getText("Width textField")),
                            Double.parseDouble(editPanel.getText("Height textField"))
                    ).toString()
            );
        }

        closeWriter();
    }
}
