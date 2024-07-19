package fr.noskillworld.api.honorranks.rewards;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.honorranks.HonorRanks;
import fr.noskillworld.api.honorranks.rewards.impl.*;

import java.util.*;

public class RewardHandler {

    private final NSWAPI nswapi;

    public final HashMap<HonorRanks, List<HonorRankReward>> rankRewards;
    public final HashMap<UUID, List<HonorRankReward>> claimedRewards;

    public RewardHandler(NSWAPI api) {
        this.nswapi = api;
        this.rankRewards = new HashMap<>();
        this.claimedRewards = new HashMap<>();
        setupRewards();
    }

    public void setRewardClaimed(UUID uuid, HonorRankReward reward) {
        if (!claimedRewards.containsKey(uuid) || claimedRewards.get(uuid) == null) {
            claimedRewards.put(uuid, new ArrayList<>());
        }
        String rewards = retrieveClaimedRewards(uuid);

        if (rewards == null || rewards.equalsIgnoreCase("null")) {
            rewards = reward.getId();
        } else {
            rewards = rewards + "," + reward.getId();
        }
        claimedRewards.get(uuid).add(reward);
        nswapi.getDatabaseManager().getRequestSender().setClaimedRewards(uuid, rewards);
    }

    public void initClaimedRewards(UUID uuid) {
        if (claimedRewards.containsKey(uuid) || claimedRewards.get(uuid) != null) {
            return;
        }
        String[] rewards = retrieveClaimedRewards(uuid).split(",");
        List<HonorRankReward> rewardList = rankRewards.get(nswapi.getHonorRanksHandler().getPlayerRank(uuid));

        if (rewards[0].equalsIgnoreCase("NULL")) {
            return;
        }
        claimedRewards.put(uuid, new ArrayList<>());
        for (String reward : rewards) {
            for (HonorRankReward r : rewardList) {
                if (reward.equalsIgnoreCase(r.getId())) {
                    claimedRewards.get(uuid).add(r);
                }
            }
        }
    }

    public boolean hasClaimedReward(UUID uuid, HonorRankReward reward) {
        if (!claimedRewards.containsKey(uuid) || claimedRewards.get(uuid) == null) {
            return false;
        }
        for (HonorRankReward r : claimedRewards.get(uuid)) {
            if (r.equals(reward)) {
                return true;
            }
        }
        return false;
    }

    private String retrieveClaimedRewards(UUID uuid) {
        return nswapi.getDatabaseManager().getRequestSender().retrieveClaimedRewards(uuid);
    }

    private void addRewards(HonorRanks rank, HonorRankReward... rewards) {
        if (rewards == null || rewards.length == 0) return;
        rankRewards.put(rank, Arrays.asList(rewards));
    }

    private void setupRewards() {
        //Rank 1 rewards
        addRewards(HonorRanks.Rank_1,
                new NSCoinReward(500)
        );

        //Rank 2 rewards
        addRewards(HonorRanks.Rank_2,
                new NSCoinReward(1000),
                new TopCommandReward(),
                new BottomCommandReward(),
                new ITFToggleCommandReward()
        );

        //Rank 3 rewards
        addRewards(HonorRanks.Rank_3,
                new NSCoinReward(2000),
                new UpCommandReward(),
                new DownCommandReward(),
                new ITFToggleModeCommandReward(),
                new ITFScanCommandReward()
        );

        //Rank 4 rewards
        addRewards(HonorRanks.Rank_4,
                new NSCoinReward(5000),
                new CraftCommandReward()
        );

        //Rank 5 rewards
        addRewards(HonorRanks.Rank_5,
                new NSCoinReward(8000),
                new EcCommandReward()
        );

        //Rank 6 rewards
        addRewards(HonorRanks.Rank_6,
                new NSCoinReward(10000),
                new FurnaceCommandReward()
        );
    }
}
