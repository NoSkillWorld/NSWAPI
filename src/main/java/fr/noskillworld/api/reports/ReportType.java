package fr.noskillworld.api.reports;

import java.util.HashMap;
import java.util.Map;

public enum ReportType {
    CHEAT(1, "Cheat", "Le joueur triche"),
    GRIEF(2, "Grief", "Le joueur grief"),
    CHAT_SPAM(3, "Spam", "Le joueur spam/flood dans le chat"),
    CHAT_PUB(4, "Pub", "Le joueur fait de la publicité"),
    CHAT_INSULTS(5, "Insultes", "Le joueur insulte dans le chat"),
    ;

    private static final Map<Integer, ReportType> BY_ID = new HashMap<>();
    private static final Map<String, ReportType> BY_NAME = new HashMap<>();

    static {
        for (ReportType i : values()) {
            BY_ID.put(i.typeId, i);
            BY_NAME.put(i.getDisplayName(), i);
        }
    }

    private final int typeId;
    private final String displayName;
    private final String desc;

    ReportType(int id, String name, String desc) {
        this.typeId = id;
        this.displayName = name;
        this.desc = desc;
    }

    public int geTypeId() {
        return this.typeId;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static ReportType getTypeByName(String name) {
        return ReportType.BY_NAME.get(name);
    }

    public static ReportType getTypeById(int id) {
        return ReportType.BY_ID.get(id);
    }
}
