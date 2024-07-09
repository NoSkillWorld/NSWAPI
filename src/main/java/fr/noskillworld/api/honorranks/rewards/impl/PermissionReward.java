package fr.noskillworld.api.honorranks.rewards.impl;

import fr.noskillworld.api.honorranks.rewards.HonorRankReward;
import fr.noskillworld.api.honorranks.rewards.RewardType;

public class PermissionReward implements HonorRankReward {

    private final String permission;
    private final String name;
    private final String description;

    public PermissionReward(String permission, String name, String desc) {
        this.permission = permission;
        this.name = name;
        this.description = desc;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.PERMISSION;
    }

    @Override
    public Object getReward() {
        return this.permission;
    }
}
