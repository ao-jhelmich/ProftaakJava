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

    public void writeShape(Shape shape) {
        if (shape.getId() != 0) {
            update(shape);
        } else {
            write(shape.toString());
        }
    }

    public void update(Shape shape) {
        //TODO Update shape
        try {
            BufferedReader file = new BufferedReader(new FileReader("file.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                line = shape.toString();
                inputBuffer.append(line);
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
        File inputFile = new File("file.txt");
        File tempFile = new File("temp.txt");

        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            PrintWriter printWriter = new PrintWriter(tempFile)
        ) {
            printWriter.write("");
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                int currentId = Integer.parseInt(currentLine.split(":", 2)[0]);
                if (currentId == shape.getId()) {
                    continue;
                }
                printWriter.write(currentLine + "\n");
            }

            if (tempFile.renameTo(inputFile)) {
                System.out.println("Removed shape on line " + shape.getId());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
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
