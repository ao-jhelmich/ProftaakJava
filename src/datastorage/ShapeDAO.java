package datastorage;

import shapes.Shape;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShapeDAO {
    private DatabaseConnection connection;

    public ShapeDAO() {
        this.connection = new DatabaseConnection();
    }

    public List<String> all() {
        List<String> shapes = new ArrayList<>();

        if (connection.openConnection()) {
            ResultSet resultset = connection.executeSQLSelectStatement(
                    "SELECT * FROM shapes;");

            if (resultset != null) {
                try {
                    while (resultset.next()) {
                        String shapeType = resultset.getString("type");
                        Double radius = resultset.getDouble("radius");
                        Double width = resultset.getDouble("width");
                        Double length = resultset.getDouble("length");
                        Double height = resultset.getDouble("height");

                        shapes.add(shapeType);
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    shapes.clear();
                }
            }

            connection.closeConnection();
        }

        return shapes;
    }

    public void saveShape(String selectedShape, String radius, String height, String length, String width) {
        if (connection.openConnection()) {
            String query = "INSERT INTO shapes ";
            HashMap<Integer, String> params = new HashMap<>();

            if (selectedShape.equals("cone") || selectedShape.equals("cylinder")) {
                query += "(type, radius, height) VALUES (?, ?, ?);";
                params.put(1, selectedShape);
                params.put(2, radius);
                params.put(3, height);
            } else if(selectedShape.equals("cube") || selectedShape.equals("squarePyramid")) {
                query += "(type, length, width, height) VALUES (?, ?, ?, ?);";
                params.put(1, selectedShape);
                params.put(2, length);
                params.put(3, width);
                params.put(4, height);
            } else {
                query += "(type, radius) VALUES (?, ?);";
                params.put(1, selectedShape);
                params.put(2, radius);
            }
            
            try {
                System.out.println(connection.executeSqlDmlStatement(query, params));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            connection.closeConnection();
        }
    }
}
