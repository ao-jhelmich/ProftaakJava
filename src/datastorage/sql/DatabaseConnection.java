package datastorage.sql;

import datastorage.txt.Reader;

import java.io.File;
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
        boolean isConnected = false;
        Reader reader = new Reader(new File("./conf.properties"));

        HashMap<String, String> properties = reader.readProperties();
        String url = "jdbc:mysql://"+ properties.getOrDefault("DB_HOST", "localhost") +"/"+ properties.getOrDefault("DB_NAME", "proftaakjava") +"?" + properties.getOrDefault("DB_OPTIONS", "serverTimezone=UTC");

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, properties.getOrDefault("DB_USER", "root"), properties.getOrDefault("DB_PASS", ""));

                if (connection != null) {
                    statement = connection.createStatement();
                }

                isConnected = true;
            } catch (SQLException e) {
                e.printStackTrace();
                isConnected = false;
            }
        } else {
            isConnected = true;
        }

        try {
            if (connection != null && !statement.isClosed()) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isConnected;
    }

    public boolean connectionIsOpen() {
        boolean open = false;

        if (connection != null && statement != null) {
            try {
                open = !connection.isClosed() && !statement.isClosed();
            } catch (SQLException e) {
                e.printStackTrace();
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
            connection.close();

            connection = null;
            statement = null;
        } catch (Exception e) {
            e.printStackTrace();
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
                e.printStackTrace();
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

            try {
                statement.executeUpdate();
                result = true;
            } catch (SQLException e) {
                e.printStackTrace();
                result = false;
            }
        }

        return result;
    }
}
