package fr.noskillworld.api;

import fr.noskillworld.api.database.DatabaseManager;
import fr.noskillworld.api.honorranks.impl.HonorRanksHandlerImpl;
import fr.noskillworld.api.utils.Credentials;

import java.util.logging.Logger;

public class NSWAPI {

    private static NSWAPI api;

    private final Logger logger;

    private static DatabaseManager databaseManager;
    private static HonorRanksHandlerImpl honorRanksHandler;

    public NSWAPI(Credentials credentials) {
        api = this;
        this.logger = Logger.getLogger("NSWAPI");

        databaseManager = new DatabaseManager(credentials);
        honorRanksHandler = new HonorRanksHandlerImpl();
    }

    public Logger getLogger() {
        return logger;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static HonorRanksHandlerImpl getHonorRanksHandler() {
        return honorRanksHandler;
    }

    public static NSWAPI getAPI() {
        return api;
    }
}