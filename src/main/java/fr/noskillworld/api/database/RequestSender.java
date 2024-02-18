package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.entities.NSWPlayer;
import fr.noskillworld.api.reports.Report;
import fr.noskillworld.api.reports.ReportType;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

    public List<NSWPlayer> getPlayers() {
        query = Queries.RETRIEVE_ALL_PLAYERS.getQuery();
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        String name;
        UUID uuid;
        List<NSWPlayer> result = new ArrayList<>();

        requestsHandler.retrieveData(query);
        try {
            while (requestsHandler.resultSet.next()) {
                name = requestsHandler.resultSet.getString("playerName");
                uuid = UUID.fromString(requestsHandler.resultSet.getString("uuid"));
                result.add(new NSWPlayer(name, uuid));
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

    public List<Report> getReports() {
        query = Queries.RETRIEVE_ALL_REPORTS.getQuery();
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        int id;
        UUID creatorUuid;
        UUID reportedUuid;
        ReportType type;
        String reason;
        boolean isResolved;
        Timestamp timestamp;
        List<Report> result = new ArrayList<>();

        requestsHandler.retrieveData(query);
        try {
            while (requestsHandler.resultSet.next()) {
                id = requestsHandler.resultSet.getInt("id");
                creatorUuid = UUID.fromString(requestsHandler.resultSet.getString("creatorUuid"));
                reportedUuid = UUID.fromString(requestsHandler.resultSet.getString("reportedUuid"));
                type = ReportType.getTypeById(requestsHandler.resultSet.getInt("typeId"));
                reason = requestsHandler.resultSet.getString("reason");
                isResolved = requestsHandler.resultSet.getBoolean("isResolved");
                timestamp = requestsHandler.resultSet.getTimestamp("date");
                result.add(new Report(id, creatorUuid, reportedUuid, type, reason, isResolved, timestamp));
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

    public void createReport(@NotNull NSWPlayer creator, @NotNull NSWPlayer reported, @NotNull ReportType type, String reason) {
        query = String.format(Queries.CREATE_REPORT.getQuery(), creator.getUniqueId(), creator.getName(), reported.getUniqueId(), reported.getName(), type.geTypeId(), type.getDisplayName(), reason);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
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

    public void markReportAsUnresolved(int id) {
        query = String.format(Queries.MARK_REPORT_UNRESOLVED.getQuery(), id);
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void setMinecraftStats(int deathCount, int killCount, long timePlayed, UUID uuid) {
        query = String.format(Queries.UPDATE_MC_STATS.getQuery(), deathCount, killCount, timePlayed, uuid);
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
        createPlayerDataTable();
    }

    private void createPlayerDataTable() {
        query = Queries.CREATE_PLAYER_DATA_TABLE.getQuery();
        requestsHandler = NSWAPI.getAPI().getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }
}
