package fr.noskillworld.api.database;

import fr.noskillworld.api.utils.Credentials;

public class DatabaseManager {

    private final Connector connector;
    private final RequestsHandler requestsHandler;
    private final RequestSender requestSender;

    public DatabaseManager(Credentials credentials) {
        this.connector = new Connector(credentials);
        this.requestsHandler = new RequestsHandler();
        this.requestSender = new RequestSender();
    }

    public Connector getConnector() {
        return this.connector;
    }

    public RequestsHandler getRequestHandler() {
        return this.requestsHandler;
    }

    public RequestSender getRequestSender() {
        return this.requestSender;
    }
}
