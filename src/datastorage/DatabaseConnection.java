package datastorage;

import java.sql.*;
import java.util.HashMap;

public class DatabaseConnection {
    private Connection connection;
    private Statement statement;

    public DatabaseConnection() {
        connection = null;
        statement = null;
    }

    public boolean openConnection() {
        boolean result = false;

        if (connection == null) {
            try {
                // Try to create a connection with the library database
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost/proftaakjava?serverTimezone=UTC", "root", "");

                if (connection != null) {
                    statement = connection.createStatement();
                }

                result = true;
            } catch (SQLException e) {
                System.out.println(e);
                result = false;
            }
        } else {
            // A connection was already initalized.
            result = true;
        }

        return result;
    }

    public boolean connectionIsOpen() {
        boolean open = false;

        if (connection != null && statement != null) {
            try {
                open = !connection.isClosed() && !statement.isClosed();
            } catch (SQLException e) {
                System.out.println(e);
                open = false;
            }
        }
        // Else, at least one the connection or statement fields is null, so
        // no valid connection.

        return open;
    }

    public void closeConnection() {
        try {
            statement.close();

            // Close the connection
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet executeSQLSelectStatement(String query) {
        ResultSet resultset = null;

        // First, check whether a some query was passed and the connection with
        // the database.
        if (query != null && connectionIsOpen()) {
            // Then, if succeeded, execute the query.
            try {
                resultset = statement.executeQuery(query);
            } catch (SQLException e) {
                System.out.println(e);
                resultset = null;
            }
        }

        return resultset;
    }

    /**
     * Dml: data manipulation language
     * @param query The SQL query that will be executed
     * @param params
     * @return true if execution of the SQL statement was successful, false
     * otherwise.
     */
    public boolean executeSqlDmlStatement(String query, HashMap<Integer, String> params) throws SQLException {
        boolean result = false;

        if (query != null && connectionIsOpen()) {
            PreparedStatement statement = connection.prepareStatement(query);

            params.forEach((k, v) -> {
                try {
                    if (v.matches("-?\\d+")) {
                        statement.setInt(k, Integer.parseInt(v));
                    } else {
                        statement.setString(k, v);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(statement);
            try {
                statement.executeUpdate();
                result = true;
            } catch (SQLException e) {
                System.out.println(e);
                result = false;
            }
        }

        return result;
    }

    public void executeSQLInsertStatement() {
    }
}
