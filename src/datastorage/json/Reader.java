package datastorage.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import shapes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader implements Closeable {

    File file = new File("file.json");
    private Scanner reader;
    private JsonReader jsonReader;

    public ArrayList<Shape> readAll() throws FileNotFoundException {
        ArrayList<Shape> shapes = new ArrayList<>();
        JsonObject jsonObject;

        reader = new Scanner(this.file);

        this.jsonReader = new JsonReader(new FileReader(this.file));
        while(reader.hasNextLine()){
            jsonObject = new Gson().fromJson(reader.nextLine(), JsonObject.class);
            shapes.add(convertToShape(jsonObject));
        }

        return shapes;
    }

    private Shape convertToShape(JsonObject jsonObject){
        Shape shape = null;

        System.out.println(jsonObject);
//        Integer id = jsonObject.get("id").isJsonNull() ? jsonObject.get("id").getAsInt();
        String type = jsonObject.get("type").getAsString();
        Double length = jsonObject.get("length") == null ? -1 : jsonObject.get("length").getAsDouble();
        Double width = jsonObject.get("width") == null ? -1 : jsonObject.get("width").getAsDouble();
        Double height = jsonObject.get("height") == null ? -1 : jsonObject.get("height").getAsDouble();
        Double radius = jsonObject.get("radius") == null ? -1 : jsonObject.get("radius").getAsDouble();

        switch(type){
            case "cone":
                shape = new Cone(radius, height);
                break;
            case "cube":
                shape = new Cube(length, width, height);
                break;
            case "cylinder":
                shape = new Cylinder(radius, height);
                break;
            case "sphere":
                shape = new Sphere(radius);
                break;
            case "squarePyramid":
                shape = new SquarePyramid(length, width, height);
                break;
        }

        return shape;
    }

    @Override
    public void close() throws IOException {
        this.jsonReader.close();
    }
}
