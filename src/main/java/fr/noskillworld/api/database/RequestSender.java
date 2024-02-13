package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.entities.NSWPlayer;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.UUID;

public class RequestSender {

    private String query;
    private RequestsHandler requestsHandler;

    public RequestSender() {
        this.query = null;
    }

    public void initPlayerData(@NotNull NSWPlayer player) {
        query = String.format(Queries.INIT_PLAYER_DATA.getQuery(), player.getUniqueId().toString(), player.getName());
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void updatePlayerData(@NotNull NSWPlayer player, int rankId, long honorPoints) {
        query = String.format(Queries.UPDATE_PLAYER_DATA.getQuery(), rankId, (int) honorPoints, player.getUniqueId().toString());
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public int getPlayerRankId(@NotNull NSWPlayer player) {
        query = String.format(Queries.RETRIEVE_PLAYER_RANK.getQuery(), player.getUniqueId().toString());
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();
        int result = 0;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = requestsHandler.resultSet.getInt("rankId");
            }
        } catch (SQLException e) {
            NSWAPI.getAPI().getLogger().severe("SQLException: " + e.getMessage());
            NSWAPI.getAPI().getLogger().severe("SQLState: " + e.getSQLState());
            NSWAPI.getAPI().getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public long getPlayerPoints(@NotNull NSWPlayer player) {
        query = String.format(Queries.RETRIEVE_PLAYER_POINTS.getQuery(), player.getUniqueId().toString());
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();
        long result = 0;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = requestsHandler.resultSet.getLong("honorPoints");
            }
        } catch (SQLException e) {
            NSWAPI.getAPI().getLogger().severe("SQLException: " + e.getMessage());
            NSWAPI.getAPI().getLogger().severe("SQLState: " + e.getSQLState());
            NSWAPI.getAPI().getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public NSWPlayer getPlayerByName(String playerName) {
        query = String.format(Queries.RETRIEVE_PLAYER_NAME.getQuery(), playerName);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();
        NSWPlayer result = null;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = NSWAPI.getAPI().getPlayerByName(requestsHandler.resultSet.getString("playerName"));
            }
        } catch (SQLException e) {
            NSWAPI.getAPI().getLogger().severe("SQLException: " + e.getMessage());
            NSWAPI.getAPI().getLogger().severe("SQLState: " + e.getSQLState());
            NSWAPI.getAPI().getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public NSWPlayer getPlayer(@NotNull NSWPlayer player) {
        query = String.format(Queries.RETRIEVE_PLAYER.getQuery(), player.getUniqueId().toString());
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();
        NSWPlayer result = null;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = NSWAPI.getAPI().getPlayerByUuid(UUID.fromString(requestsHandler.resultSet.getString("uuid")));
            }
        } catch (SQLException e) {
            NSWAPI.getAPI().getLogger().severe("SQLException: " + e.getMessage());
            NSWAPI.getAPI().getLogger().severe("SQLState: " + e.getSQLState());
            NSWAPI.getAPI().getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public void deleteReport(int id) {
        query = String.format(Queries.DELETE_REPORT.getQuery(), id);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void markReportAsResolved(int id) {
        query = String.format(Queries.MARK_REPORT_RESOLVED.getQuery(), id);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void setDeathCount(int count, UUID uuid) {
        query = String.format(Queries.UPDATE_DEATH_COUNT.getQuery(), count, uuid);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void setKillCount(int count, UUID uuid) {
        query = String.format(Queries.UPDATE_KILL_COUNT.getQuery(), count, uuid);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void setTimePlayed(long time, UUID uuid) {
        query = String.format(Queries.UPDATE_TIME_PLAYED.getQuery(), time, uuid);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public int getReportsCount() {
        query = Queries.RETRIEVE_REPORTS_COUNTS.getQuery();
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();
        int result = 0;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = requestsHandler.resultSet.getInt(1);
            }
        } catch (SQLException e) {
            NSWAPI.getAPI().getLogger().severe("SQLException: " + e.getMessage());
            NSWAPI.getAPI().getLogger().severe("SQLState: " + e.getSQLState());
            NSWAPI.getAPI().getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public void createTables() {
        NSWAPI.getAPI().getServerHandler().getExecutor().execute(this::createPlayerDataTable);
    }

    private void createPlayerDataTable() {
        query = Queries.CREATE_PLAYER_DATA_TABLE.getQuery();
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }
}
