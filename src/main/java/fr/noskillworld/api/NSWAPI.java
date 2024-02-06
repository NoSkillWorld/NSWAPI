package fr.noskillworld.api;

import fr.noskillworld.api.database.DatabaseManager;

import java.util.logging.Logger;

public class NSWAPI {

    public static NSWAPI api;

    private final Logger logger;

    private static DatabaseManager databaseManager;

    public NSWAPI() {
        api = this;
        this.logger = Logger.getLogger("NSWAPI");

        databaseManager = new DatabaseManager();
    }

    public Logger getLogger() {
        return logger;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static NSWAPI getAPI() {
        return api;
    }
}