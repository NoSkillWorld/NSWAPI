package fr.noskillworld.api;

import fr.noskillworld.api.database.DatabaseManager;
import fr.noskillworld.api.entities.NSWPlayer;
import fr.noskillworld.api.honorranks.impl.HonorRanksHandlerImpl;
import fr.noskillworld.api.reports.impl.ReportHandlerImpl;
import fr.noskillworld.api.utils.Credentials;
import fr.noskillworld.api.utils.ServerHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class NSWAPI {

    private static NSWAPI api;

    private final Logger logger;

    private final DatabaseManager databaseManager;
    private final HonorRanksHandlerImpl honorRanksHandler;
    private final ReportHandlerImpl reportHandler;
    private final ServerHandler serverHandler;

    private static Credentials creds;

    private List<NSWPlayer> players;

    private NSWAPI() {
        api = this;
        this.logger = Logger.getLogger("NSWAPI");

        this.serverHandler = new ServerHandler();
        this.databaseManager = new DatabaseManager(api, creds);
        this.honorRanksHandler = new HonorRanksHandlerImpl(api);
        this.reportHandler = new ReportHandlerImpl(api);

        getServerHandler().getExecutor().execute(() -> {
            getDatabaseManager().getRequestSender().createTables();
            init_players();
        });
    }

    @Contract("_ -> new")
    public static @NotNull NSWAPI create(Credentials credentials) {
        creds = credentials;
        return new NSWAPI();
    }

    public NSWPlayer getPlayerByName(String name) {
        for (NSWPlayer player : getPlayers()) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public NSWPlayer getPlayerByUuid(UUID uuid) {
        for (NSWPlayer player : getPlayers()) {
            if (player.getUniqueId().equals(uuid)) {
                return player;
            }
        }
        return null;
    }

    public List<NSWPlayer> getPlayers() {
        return players;
    }

    private void init_players() {
        players = getDatabaseManager().getRequestSender().getPlayers();
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

    public ReportHandlerImpl getReportHandler() {
        return reportHandler;
    }

    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public static NSWAPI getAPI() {
        return api;
    }

    public boolean hasJoinedOnce(UUID uuid) {
        NSWPlayer player = NSWAPI.getAPI().getPlayerByUuid(uuid);
        if (player == null) {
            return false;
        }
        return getPlayerByName(player.getName()) != null;
    }
}