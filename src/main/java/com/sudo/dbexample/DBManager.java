package com.sudo.dbexample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;

public class DBManager {

    private String host;
    private String port;
    private String username;
    private String password;
    private String dbName;
    private String tableName;
    private Connection connection;

    public DBManager(String host, String port, String username, String password, String dbName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        // This can be added to config
        this.tableName = "testtable";
    }

    /**
     * Execure SQL update, this for inserting/updating
     * @param update SQL update
     * @return true if succeeded
     */
    public boolean executeUpdate(String update) {
        connection = getConnection();

        try {
            Statement s = connection.createStatement();
            s.executeUpdate(update);
            s.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Add one to count of player with given username
     * @param username players username
     * @return true if succeeded
     */
    public boolean addCount(String username) {
        int count = getCount(username);
        count++;
        return setCount(username, count);
    }

    /**
     * Set count of player to given count
     * @param username player's username
     * @param count count to be set to
     * @return true if succeeded
     */
    public boolean setCount(String username, int count) {
        return executeUpdate("UPDATE " + this.tableName + " SET counter=" + count + " WHERE username = \"" + username + "\";");
    }

    /**
     * Execure given SQL query, returng values of given column
     * @param query query to be executed
     * @param column column to return
     * @return query values
     */
    public ArrayList<Integer> executeQuery(String query, String column) {
        connection = getConnection();
        ArrayList<Integer> result = new ArrayList<Integer>();

        try {
            // Query
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);

            while (rs.next()) {
                result.add(rs.getInt(column));
            }

            rs.close();
            s.close();
            connection.close();

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Get count of player with given username
     * @param username player's username
     * @return count of player
     */
    public int getCount(String username) {
        return executeQuery("SELECT * FROM " + this.tableName + " WHERE username = \"" + username + "\";", "counter").get(0);
    }

    /**
     * Initialize DB Connection, required before executing queries or updates
     */
    public void initDB() {
        this.connection = getConnection();
    }

    /**
     * Get connection object
     * @return Connection
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://"
                            + this.host + ":" + this.port + "/" + this.dbName,
                    this.username, this.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    /**
     * Execute query to see if a user exists in the table already
     * @param username player's username
     * @return true if exists
     */
    public boolean userExists(String username) {
        return executeQuery("SELECT * FROM " + this.tableName + " WHERE username = \"" + username + "\";", "counter").size() > 0;
    }

    public boolean addUser(String username) {
        return executeUpdate("INSERT INTO " + this.tableName + " (username, counter) VALUES (\"" + username + "\", 0);");
    }
}
