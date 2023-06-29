package me.darkwinged.raven.struts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class Cache {

    @Getter
    private final UUID uuid;
    @Getter
    private final String name;
    @Getter @Setter
    private int rarity;
    @Getter @Setter
    private Date found;
    @Getter @Setter
    private LocalDateTime expireInDays;
    @Getter @Setter
    private List<CacheReward> rewards;

    public void addReward(CacheReward reward) {
        rewards.add(reward);
    }

    public void removeReward(CacheReward reward) {
        rewards.remove(reward);
    }

}
