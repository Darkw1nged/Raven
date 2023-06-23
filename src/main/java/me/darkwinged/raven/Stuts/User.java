package me.darkwinged.raven.Stuts;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.darkwinged.raven.Utilites.RavenAPI;
import me.darkwinged.raven.Utilites.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class User {

    // Variables
    @Getter
    private final UUID uuid;
    @Getter @Setter
    private int raven_coins;
    @Getter
    private int experience;
    @Getter
    private int level;
    @Getter @Setter
    private String rank;
    @Getter @Setter
    private int shadow_essence;
    @Getter @Setter
    private LocalDateTime first_join;
    @Getter @Setter
    private LocalDateTime recent_join;
    @Getter @Setter
    private LocalDateTime last_seen;
    @Getter @Setter
    private int cachesGifted;
    @Getter @Setter
    private int cachesReceived;
    @Getter @Setter
    private int cachesFound;
    @Getter @Setter
    private int cachesOpened;
    @Getter @Setter
    private Party party;
    @Getter @Setter
    private Guild guild;
    @Getter @Setter
    private boolean friendNotifications;
    @Getter @Setter
    private boolean playerVisibility;

    // Lists
    @Getter @Setter
    private List<Achievement> achievements;
    @Getter @Setter
    private List<Quest> quests;
    @Getter @Setter
    private List<Booster> boosters;
    @Getter @Setter
    private List<Cache> caches;
    @Getter @Setter
    private List<GiftPack> giftPacks;
    @Getter @Setter
    private List<Milestone> giftMilestones;
    @Getter @Setter
    private List<UUID> pendingFriends;
    @Getter @Setter
    private List<Friend> friends;
    @Getter @Setter
    private List<UUID> blockedPlayers;

    public User(UUID uuid, int raven_coins, int experience, int level, String rank, int shadow_essence) {
        this.uuid = uuid;
        this.raven_coins = raven_coins;
        this.experience = experience;
        this.level = level;
        this.rank = rank;
        this.shadow_essence = shadow_essence;

        // default values for lists
        this.achievements = new ArrayList<>();
        this.quests = new ArrayList<>();
        this.boosters = new ArrayList<>();
        this.caches = new ArrayList<>();
        this.pendingFriends = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.blockedPlayers = new ArrayList<>();
    }

    // Due to experience changing, we need to update the experience bar.
    public void setExperience(int experience) {
        this.experience = experience;
        if (this.experience >= Utils.getExperienceRequiredForLevel(this.level + 1)) {
            this.level += 1;
            this.experience = 0;
        }
        RavenAPI.updateExperienceBar(this);
    }
    public void setLevel(int level) {
        this.level = level;
        RavenAPI.updateExperienceBar(this);
    }
    public void addExperience(int amount) {
        this.experience += amount;
        if (this.experience >= Utils.getExperienceRequiredForLevel(this.level + 1)) {
            this.level += 1;
            this.experience = 0;
        }
        RavenAPI.updateExperienceBar(this);
    }
    public void removeExperience(int amount) {
        this.experience -= amount;
        if (this.experience >= Utils.getExperienceRequiredForLevel(this.level + 1)) {
            this.level += 1;
            this.experience = 0;
        }
        RavenAPI.updateExperienceBar(this);
    }
    public void addLevel(int amount) {
        this.level += amount;
        RavenAPI.updateExperienceBar(this);
    }
    public void removeLevel(int amount) {
        this.level -= amount;
        RavenAPI.updateExperienceBar(this);
    }

    // Methods
    public void addRavenCoins(int amount) {
        this.raven_coins += amount;
    }

    public void removeRavenCoins(int amount) {
        this.raven_coins -= amount;
    }

    public void addShadowEssence(int amount) {
        this.shadow_essence += amount;
    }

    public void removeShadowEssence(int amount) {
        this.shadow_essence -= amount;
    }

    public void addAchievement(Achievement achievement) {
        this.achievements.add(achievement);
    }

    public void removeAchievement(Achievement achievement) {
        this.achievements.remove(achievement);
    }

    public void addQuest(Quest quest) {
        this.quests.add(quest);
    }

    public void removeQuest(Quest quest) {
        this.quests.remove(quest);
    }

    public void addBooster(Booster booster) {
        this.boosters.add(booster);
    }

    public void removeBooster(Booster booster) {
        this.boosters.remove(booster);
    }

    public void addCache(Cache cache) {
        this.caches.add(cache);
    }

    public void removeCache(Cache cache) {
        this.caches.remove(cache);
    }

    public void addPendingFriend(UUID uuid) {
        this.pendingFriends.add(uuid);
    }

    public void removePendingFriend(UUID uuid) {
        this.pendingFriends.remove(uuid);
    }

    public void addFriend(Friend friend) {
        this.friends.add(friend);
    }

    public void removeFriend(Friend friend) {
        this.friends.remove(friend);
    }

    public void blockPlayer(UUID uuid) {
        this.blockedPlayers.add(uuid);
    }

    public void unblockPlayer(UUID uuid) {
        this.blockedPlayers.remove(uuid);
    }

    // Special Getters
    public Friend getFriend(UUID uuid) {
        return this.friends.stream().filter(friend -> friend.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    // Checks
    public boolean hasCompletedAchievement(Achievement achievement) {
        return this.achievements.contains(achievement);
    }
    public boolean hasCompletedQuest(Quest quest) {
        return this.quests.contains(quest);
    }
    public boolean hasBlockedPlayer(UUID uuid) {
        return this.blockedPlayers.contains(uuid);
    }
    public boolean hasFriend(UUID uuid) {
        return this.friends.stream().anyMatch(friend -> friend.getUuid().equals(uuid));
    }

    @Override
    public String toString() {
        return "User{" +
                "\n\tuuid=" + uuid +
                ",\n\t raven_coins=" + raven_coins +
                ",\n\t experience=" + experience +
                ",\n\t level=" + level +
                ",\n\t rank='" + rank + '\'' +
                ",\n\t shadow_essence=" + shadow_essence +
                ",\n\t first_join=" + first_join +
                ",\n\t recent_join=" + recent_join +
                ",\n\t last_seen=" + last_seen +
                ",\n\t cachesGifted=" + cachesGifted +
                ",\n\t cachesReceived=" + cachesReceived +
                ",\n\t cachesFound=" + cachesFound +
                ",\n\t cachesOpened=" + cachesOpened +
                ",\n\t party=" + party +
                ",\n\t guild=" + guild +
                ",\n\t friendNotifications=" + friendNotifications +
                ",\n\t playerVisibility=" + playerVisibility +
                ",\n\t achievements=" + achievements +
                ",\n\t quests=" + quests +
                ",\n\t boosters=" + boosters +
                ",\n\t caches=" + caches +
                ",\n\t giftPacks=" + giftPacks +
                ",\n\t giftMilestones=" + giftMilestones +
                ",\n\t pendingFriends=" + pendingFriends +
                ",\n\t friends=" + friends +
                ",\n\t blockedPlayers=" + blockedPlayers +
                "\n}";
    }

}
