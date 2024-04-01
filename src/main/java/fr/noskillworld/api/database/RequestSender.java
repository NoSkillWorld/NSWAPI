package fr.noskillworld.api.database;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.entities.NSWPlayer;
import fr.noskillworld.api.reports.Report;
import fr.noskillworld.api.reports.ReportSortType;
import fr.noskillworld.api.reports.ReportType;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RequestSender {

    private final NSWAPI nswapi;

    private String query;
    private RequestsHandler requestsHandler;

    public RequestSender(NSWAPI api) {
        this.nswapi = api;
        this.query = null;
    }

    public void initPlayerData(@NotNull NSWPlayer player) {
        query = String.format(Queries.INIT_PLAYER_DATA.getQuery(), player.getUniqueId().toString(), player.getName());
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void updatePlayerData(@NotNull NSWPlayer player, int rankId, long honorPoints) {
        query = String.format(Queries.UPDATE_PLAYER_DATA.getQuery(), rankId, (int) honorPoints, player.getUniqueId().toString());
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public int getPlayerRankId(@NotNull NSWPlayer player) {
        query = String.format(Queries.RETRIEVE_PLAYER_RANK.getQuery(), player.getUniqueId().toString());
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();
        int result = 0;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = requestsHandler.resultSet.getInt("rankId");
            }
        } catch (SQLException e) {
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public long getPlayerPoints(@NotNull NSWPlayer player) {
        query = String.format(Queries.RETRIEVE_PLAYER_POINTS.getQuery(), player.getUniqueId().toString());
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();
        long result = 0;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = requestsHandler.resultSet.getLong("honorPoints");
            }
        } catch (SQLException e) {
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public List<NSWPlayer> getPlayers() {
        query = Queries.RETRIEVE_ALL_PLAYERS.getQuery();
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

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
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public List<Report> getReports(@NotNull ReportSortType sortType) {
        switch (sortType) {
            case PLAYER_DESC -> query = Queries.RETRIEVE_REPORTS_BY_NAME_ASC.getQuery();
            case PLAYER_ASC -> query = Queries.RETRIEVE_REPORTS_BY_NAME_DESC.getQuery();
            case DATE_DESC -> query = Queries.RETRIEVE_REPORTS_BY_DATE_DESC.getQuery();
            case DATE_ASC -> query = Queries.RETRIEVE_REPORTS_BY_DATE_ASC.getQuery();
            default -> query = Queries.RETRIEVE_ALL_REPORTS.getQuery();
        }
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

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
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public void updateKitUses(@NotNull NSWPlayer player, int value) {
        query = String.format(Queries.UPDATE_KIT_USES.getQuery(), player.getUniqueId().toString(), value);
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public int getKitUses(@NotNull NSWPlayer player) {
        query = String.format(Queries.RETRIEVE_KIT_USES.getQuery(), player.getUniqueId().toString());
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();
        int result = 0;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = requestsHandler.resultSet.getInt("kitUses");
            }
        } catch (SQLException e) {
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
        } finally {
            requestsHandler.close();
        }
        return result;
    }

    public void createReport(@NotNull NSWPlayer creator, @NotNull NSWPlayer reported, @NotNull ReportType type, String reason) {
        query = String.format(Queries.CREATE_REPORT.getQuery(), creator.getUniqueId(), creator.getName(), reported.getUniqueId(), reported.getName(), type.geTypeId(), type.getDisplayName(), reason);
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void deleteReport(int id) {
        query = String.format(Queries.DELETE_REPORT.getQuery(), id);
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void markReportAsResolved(int id) {
        query = String.format(Queries.MARK_REPORT_RESOLVED.getQuery(), id);
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void markReportAsUnresolved(int id) {
        query = String.format(Queries.MARK_REPORT_UNRESOLVED.getQuery(), id);
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public void setMinecraftStats(int deathCount, int killCount, long timePlayed, UUID uuid) {
        query = String.format(Queries.UPDATE_MC_STATS.getQuery(), deathCount, killCount, timePlayed, uuid);
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }

    public int getReportsCount() {
        query = Queries.RETRIEVE_REPORTS_COUNTS.getQuery();
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();
        int result = 0;

        requestsHandler.retrieveData(query);
        try {
            if (requestsHandler.resultSet.next()) {
                result = requestsHandler.resultSet.getInt(1);
            }
        } catch (SQLException e) {
            nswapi.getLogger().severe("SQLException: " + e.getMessage());
            nswapi.getLogger().severe("SQLState: " + e.getSQLState());
            nswapi.getLogger().severe("VendorError: " + e.getErrorCode());
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
        requestsHandler = nswapi.getDatabaseManager().getRequestHandler();

        requestsHandler.updateData(query);
        requestsHandler.close();
    }
}
