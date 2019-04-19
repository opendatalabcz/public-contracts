package eu.profinit.publiccontracts.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionFactory {

    private String server;
    private String port;
    private String database;
    private String user;
    private String pass;

    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://" + server + ":" + port + "/" + database;
                connection = DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return connection;
    }


    public void setServer(String server) {
        this.server = server;
    }


    public void setPort(String port) {
        this.port = port;
    }


    public void setDatabase(String database) {
        this.database = database;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public void setPass(String pass) {
        this.pass = pass;
    }
}
