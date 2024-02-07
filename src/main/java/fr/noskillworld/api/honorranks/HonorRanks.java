package fr.noskillworld.api.honorranks;

import java.util.HashMap;
import java.util.Map;

public enum HonorRanks {
    Rank_1(1, 10, "<SOLID:8afc6e>"),
    Rank_2(2, 30, "<SOLID:65e5a0>"),
    Rank_3(3, 60, "<SOLID:42cfcf>"),
    Rank_4(4, 100, "<SOLID:379ec4>"),
    Rank_5(5, 150, "<SOLID:2c70ba>"),
    Rank_6(6, 250, "<SOLID:9C1FA4>§l"),
    ;

    private static final Map<Integer, HonorRanks> BY_ID = new HashMap<>();

    static {
        for (HonorRanks i : values()) {
            BY_ID.put(i.rankId, i);
        }
    }

    private final int rankId;
    private final long honorPoints;
    private final String colorCode;

    HonorRanks(int lvl, long pts, String code) {
        this.rankId = lvl;
        this.honorPoints = pts;
        this.colorCode = code;
    }

    public int getRankId() {
        return this.rankId;
    }

    public long getHonorPoints() {
        return this.honorPoints;
    }

    public String getColorCode() {
        return this.colorCode;
    }

    public static HonorRanks getRankById(int id) {
        return HonorRanks.BY_ID.get(id);
    }
}
