package fr.noskillworld.api;

import fr.noskillworld.api.database.DatabaseManager;
import fr.noskillworld.api.honorranks.impl.HonorRanksHandlerImpl;

import java.util.logging.Logger;

public class NSWAPI {

    public static NSWAPI api;

    private final Logger logger;

    private static DatabaseManager databaseManager;
    private static HonorRanksHandlerImpl honorRanksHandler;

    public NSWAPI() {
        api = this;
        this.logger = Logger.getLogger("NSWAPI");

        databaseManager = new DatabaseManager();
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