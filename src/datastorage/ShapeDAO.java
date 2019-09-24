package datastorage;

import shapes.Shape;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShapeDAO {
    public ShapeDAO() {
    }

    public List<String> all() {
        List<String> shapes = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();
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
                        Double heigth = resultset.getDouble("heigth");

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
}
