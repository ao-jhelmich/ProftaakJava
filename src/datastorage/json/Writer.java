package datastorage.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import shapes.Shape;

import java.io.*;

public class Writer implements AutoCloseable {
    private File file = new File("file.json");
    private PrintWriter printWriter;

    public Writer() {
        try {
            this.printWriter = new PrintWriter(new FileWriter(this.file, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Shape shape) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(this.file));
            StringBuilder inputBuffer = new StringBuilder();
            String currentLine;
            JsonObject jsonObject;

            while ((currentLine = file.readLine()) != null) {
                jsonObject = new Gson().fromJson(currentLine, JsonObject.class);
                int currentId = jsonObject.get("id") == null ? -1 : jsonObject.get("id").getAsInt();
                if (currentId == shape.getId()) {
                    currentLine = shape.toJsonString();
                }
                inputBuffer.append(currentLine);
                inputBuffer.append('\n');
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream(this.file);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(String value) {
        System.out.println("Added: " + value);
        printWriter.println(value);
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
