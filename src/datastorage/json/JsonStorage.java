package datastorage.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import datastorage.DataStorageInterface;
import shapes.Shape;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class JsonStorage implements DataStorageInterface {
    private Reader reader;

    public JsonStorage() {
        this.reader = new Reader();
    }

    @Override
    public ArrayList<Shape> getAllShapes() {
        try {
            return reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeShape(Shape shape) {
        try (Writer writer = new Writer()) {
            if (shape.getId() != 0) {
                writer.update(shape);
            } else {
                reader = new datastorage.json.Reader();
                int lastId = reader.getLastId();
                shape.setId(lastId);
                writer.write(shape.toJsonString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteShape(Shape shape) {
        try (BufferedReader file = new BufferedReader(new FileReader("file.json"))) {
            StringBuilder inputBuffer = new StringBuilder();
            String currentLine;

            while ((currentLine = file.readLine()) != null) {
                int currentId = new Gson().fromJson(currentLine, JsonObject.class).get("id").getAsInt();
                if (currentId == shape.getId()) {
                    continue;
                }
                inputBuffer.append(currentLine);
                inputBuffer.append('\n');
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream("file.json");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
