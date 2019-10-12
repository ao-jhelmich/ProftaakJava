package datastorage.txt;

import datastorage.DataStorageInterface;
import shapes.*;

import java.io.*;

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
            error.printStackTrace();
        }
    }

    public void writeShape (Shape shape) {
        write(shape.toString()); //TODO Check first if shape exists in file & update if necessary
        closeWriter();
    }

    public Reader deleteShape(Shape shape, Reader reader) {
        File inputFile = new File("file.txt");
        File tempFile = new File("temp.txt");

        reader.close(); //TODO Fix delete function & delete shape

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            datastorage.txt.Writer writer = new Writer(tempFile);

            String currentLine;
            int count = 0;

            while ((currentLine = bufferedReader.readLine()) != null) {
                if (count == shape.getId()) continue;
                System.out.println(count + " - " + shape.getId());
                writer.write(currentLine);
                count++;
            }
            writer.closeWriter();
            bufferedReader.close();
            boolean success = tempFile.renameTo(inputFile);

            if (success) {
                System.out.println("Removed shape on line " + shape.getId());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return new datastorage.txt.Reader();
    }
}
