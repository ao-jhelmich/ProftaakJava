package datastorage.sql;

import datastorage.DataStorageInterface;
import shapes.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShapeDAO implements DataStorageInterface {
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

    @Override
    public void writeShape(Shape shape) {
        //TODO Fix
        /*
        if (connection.openConnection()) {
            String query = "INSERT INTO shapes ";
            HashMap<Integer, String> params = new HashMap<>();

            if (shape instanceof Cone || shape instanceof Cylinder) {
                query += "(type, radius, height) VALUES (?, ?, ?);";
                params.put(1, shape.getClass().toString().toLowerCase());
                if (shape instanceof Cone) {
                    params.put(2, ((Cone) shape).getRadius());
                    params.put(3, ((Cone) shape).getHeight());
                } else {
                    params.put(2, ((Cylinder) shape).getRadius());
                    params.put(3, ((Cylinder) shape).getHeight());
                }
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
        */
    }
}
