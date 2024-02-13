package fr.noskillworld.api;

import fr.noskillworld.api.database.DatabaseManager;
import fr.noskillworld.api.entities.NSWPlayer;
import fr.noskillworld.api.honorranks.impl.HonorRanksHandlerImpl;
import fr.noskillworld.api.utils.Credentials;
import fr.noskillworld.api.utils.ServerHandler;

import java.util.UUID;
import java.util.logging.Logger;

public class NSWAPI {

    private static NSWAPI api;

    private final Logger logger;

    private final DatabaseManager databaseManager;
    private final HonorRanksHandlerImpl honorRanksHandler;
    private final ServerHandler serverHandler;

    private static Credentials creds;

    private NSWAPI() {
        api = this;
        this.logger = Logger.getLogger("NSWAPI");

        this.databaseManager = new DatabaseManager(creds);
        this.honorRanksHandler = new HonorRanksHandlerImpl();
        this.serverHandler = new ServerHandler();
    }

    public static NSWAPI create(Credentials credentials) {
        creds = credentials;
        return new NSWAPI();
    }

    public NSWPlayer getPlayerByName(String name) {
        return null; //TODO
    }

    public NSWPlayer getPlayerByUuid(UUID uuid) {
        return null; //TODO
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

    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public static NSWAPI getAPI() {
        return api;
    }

    public boolean hasJoinedOnce(NSWPlayer player) {
        return getDatabaseManager().getRequestSender().getPlayer(player) != null;
    }
}