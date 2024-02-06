package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;

import java.sql.SQLException;

public class RequestSender {

    private String query;
    private final RequestsHandler requestsHandler;

    public RequestSender() {
        this.query = null;
        this.requestsHandler = NSWAPI.getDatabaseManager().getRequestHandler();
    }

    public void test() {
        query = String.format(Queries.TEST.getQuery(), 20);
        requestsHandler.retrieveData(query);

        try {
            while (requestsHandler.resultSet.next()) {
                System.out.println("TEST: " + requestsHandler.resultSet.getString("playerName"));
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
