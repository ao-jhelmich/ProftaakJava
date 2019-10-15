package datastorage.sql;

import datastorage.DataStorageInterface;
import domain.ShapeController;
import shapes.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ShapeDAO implements DataStorageInterface {
    private final ShapeController controller;
    private DatabaseConnection connection;

    public ShapeDAO() {
        this.connection = new DatabaseConnection();
        this.controller = new ShapeController();
    }

    @Override
    public ArrayList<Shape> getAllShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();

        if (connection.openConnection()) {
            ResultSet resultset = connection.executeSQLSelectStatement(
                    "SELECT * FROM shapes;");

            if (resultset != null) {
                try {
                    while (resultset.next()) {
                        Shape shape = null;

                        String shapeType = resultset.getString("type");
                        int id = resultset.getInt("id");
                        Double radius = resultset.getDouble("radius");
                        Double width = resultset.getDouble("width");
                        Double length = resultset.getDouble("length");
                        Double height = resultset.getDouble("height");

                        switch (shapeType) {
                            case "cone":
                                shape = new Cone(id, radius, height);
                                break;
                            case "cube":
                                shape = new Cube(id, length, width, height);
                                break;
                            case "cylinder":
                                shape = new Cylinder(id, radius, height);
                                break;
                            case "sphere":
                                shape = new Sphere(id, radius);
                                break;
                            case "squarePyramid":
                                shape = new SquarePyramid(id, length, width, height);
                                break;
                        }

                        shapes.add(shape);
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
        if (connection.openConnection()) {
            String query = "INSERT INTO shapes ";
            HashMap<Integer, String> params = new HashMap<>();

            if (shape.getType().equals("cone") || shape.getType().equals("cylinder")) {
                query += "(type, radius, height) VALUES (?, ?, ?);";
                params.put(1, shape.getType());
                if (shape.getType().equals("cone")) {
                    params.put(2, "" + ((Cone) shape).getRadius());
                    params.put(3, "" + ((Cone) shape).getHeight());
                } else {
                    params.put(2, "" + ((Cylinder) shape).getRadius());
                    params.put(3, "" + ((Cylinder) shape).getHeight());
                }
            } else if (shape.getType().equals("cube") || shape.getType().equals("squarePyramid")) {
                query += "(type, length, width, height) VALUES (?, ?, ?, ?);";
                params.put(1, shape.getType());
                if (shape.getType().equals("cube")) {
                    params.put(2, "" + ((Cube) shape).getLength());
                    params.put(3, "" + ((Cube) shape).getWidth());
                    params.put(4, "" + ((Cube) shape).getHeight());
                } else {
                    params.put(2, "" + ((SquarePyramid) shape).getLength());
                    params.put(3, "" + ((SquarePyramid) shape).getWidth());
                    params.put(4, "" + ((SquarePyramid) shape).getHeight());
                }
            } else {
                query += "(type, radius) VALUES (?, ?);";
                params.put(1, shape.getType());
                params.put(2, "" + ((Sphere) shape).getRadius());
            }

            try {
                System.out.println(connection.executeSqlDmlStatement(query, params));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            connection.closeConnection();
        }
    }

    @Override
    public void deleteShape(Shape shape) {
        if (connection.openConnection()) {
            String query = "DELETE FROM shapes WHERE id = ?;";
            HashMap<Integer, String> params = new HashMap<>();
            params.put(1, "" + shape.getId());

            try {
                System.out.println(connection.executeSqlDmlStatement(query, params));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            connection.closeConnection();
        }
    }
}
