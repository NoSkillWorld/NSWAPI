package fr.noskillworld.api.honorranks.rewards;

import fr.noskillworld.api.honorranks.HonorRanks;
import fr.noskillworld.api.honorranks.rewards.impl.*;

import java.util.*;

public class RewardHandler {

    public final HashMap<HonorRanks, List<HonorRankReward>> rankRewards;
    public final HashMap<UUID, List<HonorRankReward>> claimedRewards;

    public RewardHandler() {
        this.rankRewards = new HashMap<>();
        this.claimedRewards = new HashMap<>();
        setupRewards();
    }

    public void setRewardClaimed(UUID uuid, HonorRankReward reward) {
        if (!claimedRewards.containsKey(uuid) || claimedRewards.get(uuid) == null) {
            claimedRewards.put(uuid, new ArrayList<>());
        }
        claimedRewards.get(uuid).add(reward);
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
