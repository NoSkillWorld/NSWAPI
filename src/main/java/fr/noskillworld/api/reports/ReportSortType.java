package fr.noskillworld.api.reports;

public enum ReportSortType {
    DATE_ASC("§aDate §8(§7plus vieux§8)"),
    DATE_DESC("§aDate §8(§7plus récent§8)"),
    PLAYER_ASC("§aNom du joueur §8(§7A > Z§8)"),
    PLAYER_DESC("§aNom du joueur §8(§7A < Z§8)");

    private final String name;

    ReportSortType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
