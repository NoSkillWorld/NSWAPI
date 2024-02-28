package fr.noskillworld.api.database;

public enum Queries {
    //Update data reqs
    INIT_PLAYER_DATA("INSERT INTO core_playerdata (uuid, playerName, rankId, honorPoints) VALUES ('%s', '%s', 0, 0);"),
    UPDATE_MC_STATS("UPDATE core_playerdata SET deathCount = %d, killCount = %d, timePlayed = %d WHERE uuid = '%s';"),
    CREATE_REPORT("INSERT INTO core_reports (creatorUuid, creatorName, reportedUuid, reportedName, typeId, typeName, reason) VALUES ('%s', '%s', '%s', '%s', '%d', '%s', '%s');"),
    DELETE_REPORT("DELETE FROM core_reports WHERE id = %d;"),
    MARK_REPORT_RESOLVED("UPDATE core_reports SET isResolved = 1 WHERE id = %d;"),
    MARK_REPORT_UNRESOLVED("UPDATE core_reports SET isResolved = 0 WHERE id = %d;"),
    UPDATE_PLAYER_DATA("UPDATE core_playerdata SET rankId = %d, honorPoints = %d WHERE uuid = '%s';"),

    //Retrieve data reqs
    RETRIEVE_PLAYER_RANK("SELECT rankId FROM core_playerdata WHERE uuid = '%s';"),
    RETRIEVE_PLAYER_POINTS("SELECT honorPoints FROM core_playerdata WHERE uuid = '%s';"),
    RETRIEVE_PLAYER_NAME("SELECT playerName FROM core_playerdata WHERE playerName = '%s';"),
    RETRIEVE_ALL_PLAYERS("SELECT * FROM core_playerdata;"),
    RETRIEVE_ALL_REPORTS("SELECT * FROM core_reports;"),
    RETRIEVE_REPORTS_BY_NAME_ASC("SELECT * FROM core_reports ORDER BY reportedName;"),
    RETRIEVE_REPORTS_BY_NAME_DESC("SELECT * FROM core_reports ORDER BY reportedName DESC;"),
    RETRIEVE_REPORTS_BY_DATE_ASC("SELECT * FROM core_reports ORDER BY date;"),
    RETRIEVE_REPORTS_BY_DATE_DESC("SELECT * FROM core_reports ORDER BY date DESC;"),
    RETRIEVE_PLAYER("SELECT uuid FROM core_playerdata WHERE uuid = '%s';"),
    RETRIEVE_REPORTS_COUNTS("SELECT COUNT(*) FROM core_reports;"),

    //Create tables reqs
    CREATE_PLAYER_DATA_TABLE("""
            CREATE TABLE IF NOT EXISTS core_playerdata
            (
                id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                uuid VARCHAR(36) NOT NULL,
                playerName VARCHAR(50) NOT NULL,
                rankId INT(1) NOT NULL,
                honorPoints INT(5) NOT NULL,
                deathCount INT(5),
                killCount INT(5),
                timePlayed BIGINT
            );
            """);

    private final String message;

    Queries(String query) {
        this.message = query;
    }

    public String getQuery() {
        return this.message;
    }
}
