package fr.noskillworld.api.database;

public enum Queries {
    //Update data reqs
    INIT_PLAYER_DATA("INSERT INTO core_playerdata (uuid, playerName, rankId, honorPoints) VALUES ('%s', '%s', 0, 0);"),
    UPDATE_DEATH_COUNT("UPDATE core_playerdata SET deathCount = %d WHERE uuid = '%s';"),
    UPDATE_KILL_COUNT("UPDATE core_playerdata SET killCount = %d WHERE uuid = '%s';"),
    UPDATE_TIME_PLAYED("UPDATE core_playerdata SET timePlayed = %d WHERE uuid = '%s';"),
    DELETE_REPORT("DELETE FROM core_reports WHERE id = %d;"),
    MARK_REPORT_RESOLVED("UPDATE core_reports SET isResolved = 1 WHERE id = %d;"),
    UPDATE_PLAYER_DATA("UPDATE core_playerdata SET rankId = %d, honorPoints = %d WHERE uuid = '%s';"),

    //Retrieve data reqs
    RETRIEVE_PLAYER_RANK("SELECT rankId FROM core_playerdata WHERE uuid = '%s';"),
    RETRIEVE_PLAYER_POINTS("SELECT honorPoints FROM core_playerdata WHERE uuid = '%s';"),
    RETRIEVE_PLAYER_NAME("SELECT playerName FROM core_playerdata WHERE playerName = '%s';"),
    RETRIEVE_ALL_PLAYERS("SELECT * FROM core_playerdata;"),
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
