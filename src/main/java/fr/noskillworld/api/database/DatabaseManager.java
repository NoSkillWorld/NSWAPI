package fr.noskillworld.api.database;

public class DatabaseManager {

    private final Connector connector;
    private final RequestsHandler requestsHandler;
    private final RequestSender requestSender;

    public DatabaseManager() {
        this.connector = new Connector();
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
