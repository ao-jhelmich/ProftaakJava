package datastorage.txt;

import shapes.Shape;

import java.io.*;

public class Writer implements AutoCloseable {
    private PrintWriter printWriter;

    public void write(String value) {
        System.out.println("Added: " + value);
        printWriter.println(value);
    }

    public Writer() {
        this(new File("file.txt"));
    }

    public Writer(File file) {
        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void update(Shape shape) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("file.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String currentLine;

            while ((currentLine = file.readLine()) != null) {
                int currentId = Integer.parseInt(currentLine.split(":", 2)[0]);
                if (currentId == shape.getId()) {
                    currentLine = shape.toString();
                }
                inputBuffer.append(currentLine);
                inputBuffer.append('\n');
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream("file.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteShape(Shape shape) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("file.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String currentLine;

            while ((currentLine = file.readLine()) != null) {
                int currentId = Integer.parseInt(currentLine.split(":", 2)[0]);
                if (currentId == shape.getId()) {
                    continue;
                }
                inputBuffer.append(currentLine);
                inputBuffer.append('\n');
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream("file.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
