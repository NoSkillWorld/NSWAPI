package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;

import java.sql.SQLException;

public class RequestSender {

    private String query;
    private RequestsHandler requestsHandler;

    public RequestSender() {
        this.query = null;
    }

    public void test() {
        query = String.format(Queries.TEST.getQuery(), 20);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.retrieveData(query);

        try {
            while (requestsHandler.resultSet.next()) {
               NSWAPI.getAPI().getLogger().info("DEBUG REQ: " + requestsHandler.resultSet.getString("playerName"));
            }
        } catch (SQLException e) {
            NSWAPI.getAPI().getLogger().severe("SQLException: " + e.getMessage());
            NSWAPI.getAPI().getLogger().severe("SQLState: " + e.getSQLState());
            NSWAPI.getAPI().getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
    }
}
