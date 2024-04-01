package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.utils.Credentials;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private final NSWAPI nswapi;

    private Connection conn;

    private final String user;
    private final String password;
    private final String name;

    public Connector(NSWAPI api, @NotNull Credentials credentials) {
        this.nswapi = api;
        this.user = credentials.getDBUser();
        this.password = credentials.getDBPassword();
        this.name = credentials.getDBName();

        connect();
    }

    public void connect() {
        if (user == null || password == null || name == null) {
            nswapi.getLogger().severe("Credentials cannot be empty! Aborting connection.");
            return;
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + name, user, password);
        } catch (SQLException e) {
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        }
    }

    public boolean isConnected() {
        try {
            return conn.isValid(1000);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        }
    }

    public Connection getConn() {
        return conn;
    }
}
