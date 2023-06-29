package me.darkwinged.raven.struts;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.darkwinged.raven.struts.enums.OnlineStatus;
import me.darkwinged.raven.utilites.RavenAPI;
import me.darkwinged.raven.utilites.Utils;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class User {

    // VARIABLES
    @Getter
    private final UUID uuid;            // The UUID of the player
    @Getter
    private int level;                  // Their server level
    @Getter
    private int experience;             // Their server experience
    @Getter @Setter
    private String rank;                // The rank of the player e.g. Default, VIP, VIP+ etc
    @Getter @Setter
    private int ravenCoins;             // coins they purchase from the shop
    // CACHES
    @Getter @Setter
    private int shadowEssence;          // This is used to craft mythic caches
    @Getter @Setter
    private int cachesFound;            // This is how many caches they have found
    @Getter @Setter
    private int cachesOpened;           // This is how many caches they have opened
    @Getter @Setter
    private int cachesCrafted;          // This is how many caches they have crafted
    @Getter @Setter
    private int cachesGifted;           // This is how many gift packs they sent to a friend
    @Getter @Setter
    private int cachesReceived;         // This is how many gift packs they received
    // TIMESTAMPS
    @Getter @Setter
    private LocalDateTime firstJoin;    // This is the first time the player joined the network
    @Getter @Setter
    private LocalDateTime recentJoin;   // This is their most recent join time
    @Getter @Setter
    private LocalDateTime lastSeen;     // This is the last time they left
    // TOGGLE VARIABLES
    @Getter @Setter
    private boolean playerVisibility;   // Keeps track of if they can see other players inside lobbies
    @Getter @Setter
    private boolean petVisibility;      // Keeps track of if they can see pets inside lobbies
    @Getter @Setter
    private boolean autoSpawnPet;       // When joining a lobby, your pet will automatically spawn
    @Getter @Setter
    private boolean lobbyNight;         // Toggles day/night inside lobbies
    @Getter @Setter
    private boolean autoDenyGifts;      // When someone gifts you a gift-pack you can decline it.
    @Getter @Setter
    private OnlineStatus onlineStatus;  // The status of the player
    // NOTIFICATIONS
    @Getter @Setter
    private boolean allowPartyInvites;          // Determines if other players can send them a party invite
    @Getter @Setter
    private boolean allowFriendRequests;        // Determines if other players can send them a friend request
    @Getter @Setter
    private boolean allowFriendNotifications;   // Determines if they receive friend join/leave messages
    @Getter @Setter
    private boolean allowMessages;              // Determines if other players can send them a private message
    // SETTINGS
    @Getter @Setter
    private boolean lobbyProtection;          // Requires you to type /lobby command twice to avoid accidents
    @Getter @Setter
    private boolean chatVisibility;           // Determines if you see the chat or not
    @Getter @Setter
    private boolean guildChatVisibility;      // Determines if you see the guild chat or not
    @Getter @Setter
    private boolean lobbyJoinMessage;         // (Requires MVP+) Determines if a message is sent upon joining a lobby
    @Getter @Setter
    private boolean filterPublicMessages;     // If a word is deemed inappropriate it will be filtered
    @Getter @Setter
    private boolean filterPartyMessages;      // If a word is deemed inappropriate it will be filtered
    @Getter @Setter
    private boolean filterGuildMessages;      // If a word is deemed inappropriate it will be filtered
    @Getter @Setter
    private boolean filterPrivateMessages;    // If a word is deemed inappropriate it will be filtered

    /**
     * This is the minimum amount of data that is required to be stored in the database.
     * @param uuid The UUID of the player
     * @param rank The rank of the player (default: Default)
     */
    public User(UUID uuid, String rank) {
        this.uuid = uuid;
        this.rank = rank;
    }

    // Due to experience changing, we need to update the experience bar.

    /**
     * Sets the experience of the player and updates the experience bar.
     * @param experience The amount of experience to set
     */
    public void setExperience(int experience) {
        this.experience = experience;
        if (this.experience >= Utils.getExperienceRequiredForLevel(this.level + 1)) {
            this.level += 1;
            this.experience = 0;
        }
        RavenAPI.updateExperienceBar(this);
    }

    /**
     * Sets the level of the player and updates the experience bar.
     * @param level The level to set
     */
    public void setLevel(int level) {
        this.level = level;
        RavenAPI.updateExperienceBar(this);
    }

    /**
     * Adds experience to the player and updates the experience bar.
     * @param amount The amount of experience to add
     */
    public void addExperience(int amount) {
        this.experience += amount;
        if (this.experience >= Utils.getExperienceRequiredForLevel(this.level + 1)) {
            this.level += 1;
            this.experience = 0;
        }
        RavenAPI.updateExperienceBar(this);
    }

    /**
     * Removes experience from the player and updates the experience bar.
     * @param amount The amount of experience to remove
     */
    public void removeExperience(int amount) {
        this.experience -= amount;
        if (this.experience >= Utils.getExperienceRequiredForLevel(this.level + 1)) {
            this.level += 1;
            this.experience = 0;
        }
        RavenAPI.updateExperienceBar(this);
    }

    /**
     * Adds a level to the player and updates the experience bar.
     * @param amount The amount of levels to add
     */
    public void addLevel(int amount) {
        this.level += amount;
        RavenAPI.updateExperienceBar(this);
    }

    /**
     * Removes a level from the player and updates the experience bar.
     * @param amount The amount of levels to remove
     */
    public void removeLevel(int amount) {
        this.level -= amount;
        RavenAPI.updateExperienceBar(this);
    }

    /**
     * @param amount The amount of raven coins to check
     * @return If the player has enough raven coins
     */
    public boolean hasEnoughRavenCoins(int amount) {
        return this.ravenCoins >= amount;
    }

    // Overriding methods
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof User user)) return false;
        return user.getUuid().equals(this.uuid);
    }

    @Override
    public int hashCode() {
        return this.uuid.hashCode();
    }

}
