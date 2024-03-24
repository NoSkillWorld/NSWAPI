package fr.noskillworld.api.entities;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.honorranks.HonorRanks;

import java.util.UUID;

public class NSWPlayer {

    private final String name;
    private final UUID uuid;

    public NSWPlayer(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public long getHonorPoints() {
        return NSWAPI.getAPI().getHonorRanksHandler().getPlayerPoints(uuid);
    }

    public HonorRanks getHonorRank() {
        return NSWAPI.getAPI().getHonorRanksHandler().getPlayerRank(uuid);
    }
}
