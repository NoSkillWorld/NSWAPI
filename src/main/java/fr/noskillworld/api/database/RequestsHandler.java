package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestsHandler {

    private final NSWAPI nswapi;

    public Statement statement;
    public ResultSet resultSet;

    public RequestsHandler(NSWAPI api) {
        this.nswapi = api;
        this.statement = null;
        this.resultSet = null;
    }

    public void retrieveData(String query) {
        if (!isConnected()) {
            nswapi.getDatabaseManager().getConnector().connect();
        }

        try {
            statement = nswapi.getDatabaseManager().getConnector().getConn().createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        }
    }

    public void updateData(String query) {
        if (!isConnected()) {
            nswapi.getDatabaseManager().getConnector().connect();
        }

        try {
            statement = nswapi.getDatabaseManager().getConnector().getConn().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        }
    }

    public void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            } // ignore
            resultSet = null;
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignored) {
            } // ignore
            statement = null;
        }
    }

    private boolean isConnected() {
        return nswapi.getDatabaseManager().getConnector().isConnected();
    }
}
