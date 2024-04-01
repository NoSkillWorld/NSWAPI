package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.utils.Credentials;

public class DatabaseManager {

    private final Connector connector;
    private final RequestsHandler requestsHandler;
    private final RequestSender requestSender;

    public DatabaseManager(NSWAPI api, Credentials credentials) {
        this.connector = new Connector(api, credentials);
        this.requestsHandler = new RequestsHandler(api);
        this.requestSender = new RequestSender(api);
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
