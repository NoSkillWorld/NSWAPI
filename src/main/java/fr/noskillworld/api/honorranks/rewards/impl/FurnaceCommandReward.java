package fr.noskillworld.api.honorranks.rewards.impl;

import fr.noskillworld.api.honorranks.rewards.HonorRankReward;
import fr.noskillworld.api.honorranks.rewards.RewardType;

public class FurnaceCommandReward implements HonorRankReward {

    @Override
    public String getName() {
        return "/furnace";
    }

    @Override
    public String getDescription() {
        return "Accès à la commande /furnace";
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.COMMAND;
    }

    @Override
    public Object getReward() {
        return "nsw.command.furnace";
    }
}
