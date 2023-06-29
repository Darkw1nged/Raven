package me.darkwinged.raven.managers;

import lombok.Getter;
import lombok.Setter;
import me.darkwinged.raven.struts.*;

import java.util.*;

public class UserManager {

    @Getter @Setter
    private static List<User> users = new ArrayList<>();
    @Getter @Setter
    private static Map<UUID, Party> userParty = new HashMap<>();
    @Getter @Setter
    private static Map<UUID, Guild> userGuild = new HashMap<>();
    @Getter @Setter
    private static Map<UUID, List<Achievement>> userAchievements = new HashMap<>();
    @Getter @Setter
    private static Map<UUID, List<Quest>> userQuests = new HashMap<>();
    @Getter @Setter
    private static Map<UUID, List<Booster>> userBoosters = new HashMap<>();
    @Getter @Setter
    public static Map<UUID, List<Cache>> userCaches = new HashMap<>();
    @Getter @Setter
    public static Map<UUID, List<GiftPack>> userGiftPacks = new HashMap<>();
    @Getter @Setter
    private static Map<UUID, List<Milestone>> userGiftMilestones = new HashMap<>();
    @Getter @Setter
    private static Map<UUID, List<UUID>> userPendingFriends = new HashMap<>();
    @Getter @Setter
    private static Map<UUID, List<Friend>> userFriends = new HashMap<>();
    @Getter @Setter
    private static Map<UUID, List<UUID>> userBlockedPlayers = new HashMap<>();

    // Blocked Players Methods

    /**
     * Check if a player is blocked by another player
     * @param uuid The player that is blocking
     * @param blockedUUID The player to check if they are blocked
     * @return true if the player is blocked, false if not
     */
    public static boolean isBlocked(UUID uuid, UUID blockedUUID) {
        if (userBlockedPlayers.containsKey(uuid)) {
            List<UUID> blocked = userBlockedPlayers.get(uuid);
            for (UUID u : blocked) {
                if (u.equals(blockedUUID)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add a player to the blocked list
     * @param uuid The player that is blocking
     * @param blockedUUID The player to block
     */
    public static void addBlocked(UUID uuid, UUID blockedUUID) {
        if (userBlockedPlayers.containsKey(uuid)) {
            List<UUID> blocked = userBlockedPlayers.get(uuid);
            blocked.add(blockedUUID);
        }
    }

    /**
     * Remove a player from the blocked list
     * @param uuid The player that is blocking
     * @param blockedUUID The player to unblock
     */
    public static void removeBlocked(UUID uuid, UUID blockedUUID) {
        if (userBlockedPlayers.containsKey(uuid)) {
            List<UUID> blocked = userBlockedPlayers.get(uuid);
            blocked.removeIf(u -> u.equals(blockedUUID));
        }
    }


    // Friend Methods

    /**
     * Check if a player is friends with another player
     * @param uuid The player that wants to check if they are friends
     * @param friendUUID The player to check if they are friends with
     * @return true if the player is friends, false if not
     */
    public static boolean hasFriend(UUID uuid, UUID friendUUID) {
        if (userFriends.containsKey(uuid)) {
            List<Friend> friends = userFriends.get(uuid);
            for (Friend f : friends) {
                if (f.getFriend().equals(friendUUID)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the friend object of a player
     * @param uuid The player that wants to get the friend object
     * @param friendUUID The friend to get the object of
     * @return The friend object
     */
    public static Friend getFriend(UUID uuid, UUID friendUUID) {
        if (userFriends.containsKey(uuid)) {
            List<Friend> friends = userFriends.get(uuid);
            for (Friend f : friends) {
                if (f.getFriend().equals(friendUUID)) {
                    return f;
                }
            }
        }
        return null;
    }

    /**
     * Add a friend to a player
     * @param uuid The player that wants to add a friend
     * @param friend The friend to add
     */
    public static void addFriend(UUID uuid, Friend friend) {
        if (userFriends.containsKey(uuid)) {
            List<Friend> friends = userFriends.get(uuid);
            friends.add(friend);
        }
    }

    /**
     * Remove a friend from a player
     * @param uuid The player that wants to remove a friend
     * @param friendUUID The friend to remove
     */
    public static void removeFriend(UUID uuid, UUID friendUUID) {
        if (userFriends.containsKey(uuid)) {
            List<Friend> friends = userFriends.get(uuid);
            friends.removeIf(f -> f.getFriend().equals(friendUUID));
        }
    }

    // Pending Friend Methods
    public static boolean pendingFriend(UUID uuid, UUID friendUUID) {
        if (userPendingFriends.containsKey(uuid)) {
            List<UUID> pending = userPendingFriends.get(uuid);
            for (UUID u : pending) {
                if (u.equals(friendUUID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addPendingFriend(UUID uuid, UUID friendUUID) {
        if (userPendingFriends.containsKey(uuid)) {
            List<UUID> pending = userPendingFriends.get(uuid);
            pending.add(friendUUID);
        }
    }

    public static void removePendingFriend(UUID uuid, UUID friendUUID) {
        if (userPendingFriends.containsKey(uuid)) {
            List<UUID> pending = userPendingFriends.get(uuid);
            pending.removeIf(u -> u.equals(friendUUID));
        }
    }


}
