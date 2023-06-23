package me.darkwinged.raven.Stuts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class Quest {

    @Getter
    private final UUID uuid;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private List<Reward> rewards;
    @Getter @Setter
    private boolean completed;

    public Quest(UUID uuid, String name, String description, boolean completed) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.rewards = null;
        this.completed = completed;
    }

    public Quest(UUID uuid, String name, String description) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.rewards = null;
        this.completed = false;
    }

    public void addReward(Reward reward) {
        rewards.add(reward);
    }

    public void removeReward(Reward reward) {
        rewards.remove(reward);
    }

    public void clearRewards() {
        rewards.clear();
    }

}
