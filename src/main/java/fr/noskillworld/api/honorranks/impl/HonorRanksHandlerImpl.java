package fr.noskillworld.api.honorranks.impl;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.entities.NSWPlayer;
import fr.noskillworld.api.honorranks.HonorRanks;
import fr.noskillworld.api.honorranks.HonorRanksHandler;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HonorRanksHandlerImpl implements HonorRanksHandler {

    private final Map<UUID, @Nullable HonorRanks> playerRank = new HashMap<>();
    private final Map<UUID, Long> playerPoints = new HashMap<>();

    @Override
    public void init(UUID uuid, String name) {
        NSWPlayer player;

        if (NSWAPI.getAPI().hasJoinedOnce(uuid)) {
            player = NSWAPI.getAPI().getPlayerByUuid(uuid);
            int rankId = NSWAPI.getAPI().getDatabaseManager().getRequestSender().getPlayerRankId(player);
            long points = NSWAPI.getAPI().getDatabaseManager().getRequestSender().getPlayerPoints(player);

            playerRank.putIfAbsent(player.getUniqueId(), HonorRanks.getRankById(rankId));
            playerPoints.putIfAbsent(player.getUniqueId(), points);
        } else {
            player = new NSWPlayer(name, uuid);
            playerRank.putIfAbsent(player.getUniqueId(), null);
            playerPoints.putIfAbsent(player.getUniqueId(), 0L);
            NSWAPI.getAPI().getPlayers().add(player);
            NSWAPI.getAPI().getDatabaseManager().getRequestSender().initPlayerData(player);
        }
    }

    @Override
    public void setPlayerPoints(UUID uuid, long amount) {
        playerPoints.replace(uuid, amount);
    }

    @Override
    public void gainPlayerPoints(UUID uuid, long amount) {
        long oldPoints = getPlayerPoints(uuid);

        playerPoints.replace(uuid, oldPoints + amount);
    }

    @Override
    public void resetPlayerRank(UUID uuid) {
        playerRank.replace(uuid, null);
    }

    @Override
    public void upRankPlayer(UUID uuid) {
        if (getNextPlayerRank(uuid) != null) {
            long currentPoints = getPlayerPoints(uuid);
            long pointsNeeded = getPointsNeeded(uuid);

            if (currentPoints >= pointsNeeded) {
                long points = currentPoints - pointsNeeded;
                HonorRanks nextRank = getNextPlayerRank(uuid);

                playerPoints.replace(uuid, points);
                playerRank.replace(uuid, nextRank);
            }
        }
    }

    @Override
    public void forceUpRankPlayer(UUID uuid) {
        if (getNextPlayerRank(uuid) != null) {
            HonorRanks nextRank = getNextPlayerRank(uuid);
            playerRank.replace(uuid, nextRank);
        }
    }

    @Override
    public long getPlayerPoints(UUID uuid) {
        return playerPoints.get(uuid);
    }

    @Override
    public long getPointsNeeded(UUID uuid) {
        if (getNextPlayerRank(uuid) != null) {
            return getNextPlayerRank(uuid).getHonorPoints();
        }
        return 0;
    }

    @Override
    public HonorRanks getPlayerRank(UUID uuid) {
        return playerRank.get(uuid);
    }

    @Override
    public int getPlayerRankId(UUID uuid) {
        if (isRanked(uuid)) {
            return getPlayerRank(uuid).getRankId();
        } else {
            return 0;
        }
    }

    @Override
    public HonorRanks getNextPlayerRank(UUID uuid) {
        int currentRankId = getPlayerRankId(uuid);
        int nextRankId = currentRankId + 1;

        if (currentRankId == 6) {
            return null;
        }
        return HonorRanks.getRankById(nextRankId);
    }

    @Override
    public List<HonorRanks> getPreviousPlayerRanks(UUID uuid) {
        int currentRankId = getPlayerRankId(uuid);
        List<HonorRanks> rankList = new ArrayList<>();

        for (int i = 1; i <= currentRankId; i++) {
            rankList.add(HonorRanks.getRankById(i));
        }
        return rankList;
    }

    @Override
    public String getDisplayName(UUID uuid) {
        return "§fRang d'Honneur " + getPlayerRankFormat(uuid);
    }

    @Override
    public String getPrefix(UUID uuid) {
        return "§8[" + getPlayerRankFormat(uuid) + "§8]";
    }

    @Override
    public String getRanks(UUID uuid) {
        StringBuilder sb = new StringBuilder();

        for (HonorRanks i : HonorRanks.values()) {
            sb.append("§r §8|§f Rang ").append(getRankFormat(i))
                    .append("§r §8(§3").append(i.getHonorPoints())
                    .append(" §7Points d'Honneur§8)");

            if (getPreviousPlayerRanks(uuid).contains(i)) {
                sb.append(" §8[§a✔§8]");
            }
            if (i == getNextPlayerRank(uuid)) {
                sb.append(" §3§l« PROCHAIN");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getPlayerRankFormat(UUID uuid) {
        HonorRanks rank = getPlayerRank(uuid);
        return getRankFormat(rank);
    }

    @Override
    public String getRankFormat(HonorRanks rank) {
        if (rank == null) {
            return "§70";
        } else {
            return IridiumColorAPI.process(rank.getColorCode() + rank.getRankId());
        }
    }

    @Override
    public boolean isRanked(UUID uuid) {
        return playerRank.get(uuid) != null;
    }
}
