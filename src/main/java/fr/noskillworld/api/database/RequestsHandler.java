package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestsHandler {

    public Statement statement;
    public ResultSet resultSet;

    public RequestsHandler() {
        statement = null;
        resultSet = null;
    }

    public void retrieveData(String query) {
        if (!isConnected()) {
            NSWAPI.getDatabaseManager().getConnector().connect();
        }

        try {
            statement = NSWAPI.getDatabaseManager().getConnector().getConn().createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            NSWAPI.getAPI().getLogger().severe("SQLException: " + e.getMessage());
            NSWAPI.getAPI().getLogger().severe("SQLState: " + e.getSQLState());
            NSWAPI.getAPI().getLogger().severe("VendorError: " + e.getErrorCode());
        }
    }

    public void updateData(String query) {
        if (!isConnected()) {
            NSWAPI.getDatabaseManager().getConnector().connect();
        }

        try {
            statement = NSWAPI.getDatabaseManager().getConnector().getConn().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            NSWAPI.getAPI().getLogger().severe("SQLException: " + e.getMessage());
            NSWAPI.getAPI().getLogger().severe("SQLState: " + e.getSQLState());
            NSWAPI.getAPI().getLogger().severe("VendorError: " + e.getErrorCode());
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
        return NSWAPI.getDatabaseManager().getConnector().isConnected();
    }
}
