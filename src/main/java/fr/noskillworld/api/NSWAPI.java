package fr.noskillworld.api;

import fr.noskillworld.api.database.DatabaseManager;
import fr.noskillworld.api.honorranks.impl.HonorRanksHandlerImpl;
import fr.noskillworld.api.utils.Credentials;

import java.util.logging.Logger;

public class NSWAPI {

    private static NSWAPI api;

    private final Logger logger;

    private final DatabaseManager databaseManager;
    private final HonorRanksHandlerImpl honorRanksHandler;

    private static Credentials creds;

    private NSWAPI() {
        api = this;
        this.logger = Logger.getLogger("NSWAPI");

        this.databaseManager = new DatabaseManager(creds);
        this.honorRanksHandler = new HonorRanksHandlerImpl();
    }

    public static NSWAPI create(Credentials credentials) {
        creds = credentials;
        return new NSWAPI();
    }

    public Logger getLogger() {
        return logger;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public HonorRanksHandlerImpl getHonorRanksHandler() {
        return honorRanksHandler;
    }

    public static NSWAPI getAPI() {
        return api;
    }
}