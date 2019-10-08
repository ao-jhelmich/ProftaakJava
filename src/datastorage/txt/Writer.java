package datastorage.txt;

import datastorage.DataStorageInterface;
import shapes.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer implements DataStorageInterface {
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

    public void writeShape (Shape shape) {
        write(shape.toString());
//        if (shape instanceof Cone) {
//            write(
//                shape.toString()
//            );
//        } else if (shape instanceof Cube) {
//            write(
//                new Cube(
//                    Double.parseDouble(editPanel.getText("Length textField")),
//                    Double.parseDouble(editPanel.getText("Width textField")),
//                    Double.parseDouble(editPanel.getText("Height textField"))
//                ).toString()
//            );
//        } else if (shape instanceof Cylinder) {
//            write(
//                new Cylinder(
//                    Double.parseDouble(editPanel.getText("Radius textField")),
//                    Double.parseDouble(editPanel.getText("Height textField"))
//                ).toString()
//            );
//        } else if (shape instanceof Sphere) {
//            write(
//                    new Sphere(
//                            Double.parseDouble(editPanel.getText("Radius textField"))
//                    ).toString()
//            );
//        } else if (shape instanceof SquarePyramid) {
//            write(
//                    new SquarePyramid(
//                            Double.parseDouble(editPanel.getText("Length textField")),
//                            Double.parseDouble(editPanel.getText("Width textField")),
//                            Double.parseDouble(editPanel.getText("Height textField"))
//                    ).toString()
//            );
//        }

        closeWriter();
    }
}
