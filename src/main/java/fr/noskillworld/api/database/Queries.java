package fr.noskillworld.api.database;

public enum Queries {
    TEST("SELECT * FROM core_playerdata WHERE honorPoints > %d"),
    ;

    private final String message;

    Queries(String query) {
        this.message = query;
    }

    public String getQuery() {
        return this.message;
    }
}
