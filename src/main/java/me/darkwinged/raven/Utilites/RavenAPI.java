package me.darkwinged.raven.Utilites;


import me.darkwinged.raven.Raven;
import me.darkwinged.raven.Stuts.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RavenAPI {

    /**
     * This will check if the user exists in the database.
     *
     * @return If the user exists in the database.
     */
    public static boolean userExists(UUID uuid) {
        try {
            Connection connection = Raven.sqlLibrary.getConnection();
            if (connection == null) return false;

            String query = "SELECT * FROM `players` WHERE `uuid` = '" + uuid.toString() + "';";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (Exception e) {
            Log("Unable to check if user exists in database.", e);
        }
        return false;
    }

    /**
     * This will get the user from the database.
     *
     * @param uuid The UUID of the user.
     * @return The user from the database.
     */
    public static User getUser(UUID uuid) {
        try {
            Connection connection = Raven.sqlLibrary.getConnection();
            if (connection == null) return null;

            // Get the user from the database.
            String query = "SELECT * FROM `players` WHERE `uuid` = '" + uuid.toString() + "';";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            if (!rs.next()) return null;

            /**
             *     // Lists
             *     @Getter @Setter
             *     private List<Achievement> achievements;
             *     @Getter @Setter
             *     private List<Quest> quests;
             *     @Getter @Setter
             *     private List<Booster> boosters;
             *     @Getter @Setter
             *     private List<Cache> caches;
             */

            User user = new User(
                    UUID.fromString(rs.getString("uuid")),
                    rs.getInt("raven_coins"),
                    rs.getInt("experience"),
                    rs.getInt("level"),
                    rs.getString("rank"),
                    rs.getInt("shadow_essence"),
                    LocalDateTime.parse(rs.getString("first_join"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.parse(rs.getString("recent_join"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.parse(rs.getString("last_seen"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    rs.getInt("caches_gifted"),
                    rs.getInt("caches_received"),
                    rs.getInt("caches_found"),
                    rs.getInt("caches_opened"),
                    null,
                    null,
                    rs.getInt("friend_notifications") == 1,
                    rs.getInt("player_visibility") == 1,
                    new ArrayList<>(), // TODO Achievements
                    new ArrayList<>(), // TODO Quests
                    new ArrayList<>(), // TODO Boosters
                    new ArrayList<>(), // TODO Caches
                    new ArrayList<>(), // TODO Gifts
                    DataCache.defaultUserMilestones, // TODO Milestones
                    new ArrayList<>(), // Pending friends
                    new ArrayList<>(), // Friends
                    new ArrayList<>() // Blocked players
            );

            // Get party
            query = "SELECT * FROM `party_members` WHERE `member_uuid` = '" + uuid.toString() + "';";
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                UUID party_uuid = UUID.fromString(rs.getString("party_uuid"));

                query = "SELECT * FROM `parties` WHERE `uuid` = '" + party_uuid.toString() + "';";
                statement = connection.prepareStatement(query);
                ResultSet rs2 = statement.executeQuery();
                if (rs2.next()) {
                    Party party = new Party(
                            UUID.fromString(rs2.getString("uuid")),
                            UUID.fromString(rs2.getString("owner_uuid")),
                            new ArrayList<>(), // Members
                            new ArrayList<>(), // Invited members
                            rs2.getInt("open") == 1
                    );

                    // Get party members
                    query = "SELECT * FROM `party_members` WHERE `party_uuid` = '" + party_uuid.toString() + "';";
                    statement = connection.prepareStatement(query);
                    ResultSet rs3 = statement.executeQuery();
                    while (rs3.next()) {
                        party.getMembers().add(UUID.fromString(rs3.getString("member_uuid")));
                    }

                    // Get party invited members
                    query = "SELECT * FROM `invited_party_members` WHERE `party_uuid` = '" + party_uuid.toString() + "';";
                    statement = connection.prepareStatement(query);

                    rs3 = statement.executeQuery();
                    while (rs3.next()) {
                        party.getInvited().add(UUID.fromString(rs3.getString("member_uuid")));
                    }

                    user.setParty(party);
                }
            }

            // Get guild
            query = "SELECT * FROM `guild_members` WHERE `member_uuid` = '" + uuid.toString() + "';";
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                UUID guild_uuid = UUID.fromString(rs.getString("guild_uuid"));

                query = "SELECT * FROM `guilds` WHERE `uuid` = '" + guild_uuid.toString() + "';";
                statement = connection.prepareStatement(query);
                ResultSet rs2 = statement.executeQuery();
                if (rs2.next()) {
                    Guild guild = new Guild(
                            UUID.fromString(rs2.getString("uuid")),
                            UUID.fromString(rs2.getString("owner_uuid")),
                            rs2.getString("name"),
                            rs2.getString("prefix"),
                            rs2.getString("description"),
                            rs2.getInt("open") == 1,
                            LocalDateTime.parse(rs.getString("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            new ArrayList<>(), // Members
                            new ArrayList<>(), // Invited members
                            new ArrayList<>() // Banned members
                    );

                    // Get guild members
                    query = "SELECT * FROM `guild_members` WHERE `guild_uuid` = '" + guild_uuid.toString() + "';";
                    statement = connection.prepareStatement(query);
                    ResultSet rs3 = statement.executeQuery();
                    while (rs3.next()) {
                        guild.getMembers().add(UUID.fromString(rs3.getString("member_uuid")));
                    }

                    // Get guild invited members
                    query = "SELECT * FROM `invited_guild_members` WHERE `guild_uuid` = '" + guild_uuid.toString() + "';";
                    statement = connection.prepareStatement(query);
                    rs3 = statement.executeQuery();
                    while (rs3.next()) {
                        guild.getInvitedMembers().add(UUID.fromString(rs3.getString("member_uuid")));
                    }

                    // Get guild banned members
                    query = "SELECT * FROM `banned_guild_members` WHERE `guild_uuid` = '" + guild_uuid.toString() + "';";
                    statement = connection.prepareStatement(query);
                    rs3 = statement.executeQuery();
                    while (rs3.next()) {
                        guild.getBannedMembers().add(UUID.fromString(rs3.getString("member_uuid")));
                    }

                    user.setGuild(guild);
                }
            }

            // Get blocked players
            query = "SELECT * FROM `blocked_players` WHERE `player_uuid` = '" + uuid.toString() + "';";
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                user.getBlockedPlayers().add(UUID.fromString(rs.getString("blocked_uuid")));
            }

            // Get friends
            query = "SELECT * FROM `friends` WHERE `player_uuid` = '" + uuid.toString() + "';";
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Friend friend = new Friend(
                        UUID.fromString(rs.getString("uuid")),
                        UUID.fromString(rs.getString("friend_uuid")),
                        rs.getInt("best") == 1,
                        rs.getString("friend_nickname"),
                        LocalDateTime.parse(rs.getString("friends_since"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                );

                user.getFriends().add(friend);
            }

            return user;
        } catch (Exception e) {
            Log("Unable to get user from database.", e);
        }

        return null;
    }

    /**
     * This will save the user to the database.
     *
     * @param user The user to save.
     * @return If the user was saved successfully.
     */
    public static boolean saveUser(User user) {
        try {
            Connection connection = Raven.sqlLibrary.getConnection();
            if (connection == null) return false;

            if (userExists(user.getUuid())) {
                String query = "UPDATE `players` SET " +
                        "`raven_coins` = " + user.getRaven_coins() + ", " +
                        "`experience` = " + user.getExperience() + ", " +
                        "`level` = " + user.getLevel() + ", " +
                        "`rank` = '" + user.getRank() + "', " +
                        "`shadow_essence` = " + user.getShadow_essence() + ", " +
                        "`first_join` = '" + user.getFirst_join().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                        "`recent_join` = '" + user.getRecent_join().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                        "`last_seen` = '" + user.getLast_seen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                        "`caches_gifted` = " + user.getCachesGifted() + ", " +
                        "`caches_received` = " + user.getCachesReceived() + ", " +
                        "`caches_found` = " + user.getCachesFound() + ", " +
                        "`caches_opened` = " + user.getCachesOpened() + ", " +
                        "`friend_notifications` = " + (user.isFriendNotifications() ? 1 : 0) + ", " +
                        "`player_visibility` = " + (user.isPlayerVisibility() ? 1 : 0) + " " +
                        "WHERE `uuid` = '" + user.getUuid().toString() + "';";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();
            } else {
                String query = "INSERT INTO `players` (`uuid`, `raven_coins`, `experience`, `level`, `rank`, `shadow_essence`, `first_join`, `recent_join`, `last_seen`, `caches_gifted`, `caches_received`, `caches_found`, `caches_opened`, `friend_notifications`, `player_visibility`) VALUES " +
                        "('" + user.getUuid().toString() + "', " +
                        user.getRaven_coins() + ", " +
                        user.getExperience() + ", " +
                        user.getLevel() + ", " +
                        "'" + user.getRank() + "', " +
                        user.getShadow_essence() + ", " +
                        "'" + user.getFirst_join().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                        "'" + user.getRecent_join().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                        "'" + user.getLast_seen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                        user.getCachesGifted() + ", " +
                        user.getCachesReceived() + ", " +
                        user.getCachesFound() + ", " +
                        user.getCachesOpened() + "," +
                        (user.isFriendNotifications() ? 1 : 0) + "," +
                        (user.isPlayerVisibility() ? 1 : 0) + ");";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.execute();
            }

            if (saveUserFriends(user)) {
                Log("Saved user friends for " + user.getUuid().toString() + " to database.");
            }

            Log("Saved user " + user.getUuid().toString() + " to database.");
            return true;
        } catch (Exception e) {
            Log("Unable to save user to database.", e);
        }
        return false;
    }

    /**
     * This will save the user friends to the database.
     * This will also save the user blocked players to the database.
     *
     * @param user The user to save.
     * @return If the user was saved successfully.
     */
    public static boolean saveUserFriends(User user) {
        try {
            Connection connection = Raven.sqlLibrary.getConnection();
            if (connection == null) return false;

            String query;
            PreparedStatement statement;
            ResultSet rs;

            // Save blocked players
            if (user.getBlockedPlayers() != null) {
                for (UUID blockedPlayer : user.getBlockedPlayers()) {
                    query = "SELECT * FROM `blocked_players` WHERE `player_uuid` = '" + user.getUuid().toString() + "' AND `blocked_uuid` = '" + blockedPlayer.toString() + "';";
                    statement = connection.prepareStatement(query);
                    rs = statement.executeQuery();
                    if (!rs.next()) {
                        query = "INSERT INTO `blocked_players` (`player_uuid`, `blocked_uuid`, `blocked_since`) VALUES" +
                                "('" + user.getUuid().toString() + "', '" + blockedPlayer.toString() + "', " +
                                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
                        statement = connection.prepareStatement(query);
                        statement.execute();
                    }
                }

                // We need to delete any blocked players that are no longer blocked.
                query = "SELECT * FROM `blocked_players` WHERE `player_uuid` = '" + user.getUuid().toString() + "';";
                statement = connection.prepareStatement(query);
                rs = statement.executeQuery();
                while (rs.next()) {
                    UUID blockedPlayer = UUID.fromString(rs.getString("blocked_uuid"));
                    if (!user.getBlockedPlayers().contains(blockedPlayer)) {
                        query = "DELETE FROM `blocked_players` WHERE `player_uuid` = '" + user.getUuid().toString() + "' AND `blocked_uuid` = '" + blockedPlayer.toString() + "';";
                        statement = connection.prepareStatement(query);
                        statement.execute();
                    }
                }
            }

            // Save friends
            if (user.getFriends() != null) {
                for (Friend friend : user.getFriends()) {
                    query = "SELECT * FROM `friends` WHERE `player_uuid` = '" + user.getUuid().toString() + "' AND `friend_uuid` = '" + friend.getUuid().toString() + "';";
                    statement = connection.prepareStatement(query);
                    rs = statement.executeQuery();
                    if (!rs.next()) {
                        query = "INSERT INTO `friends` (`player_uuid`, `friend_uuid`, `friend_since`) VALUES" +
                                "('" + user.getUuid().toString() + "', '" + friend.getUuid().toString() + "', " +
                                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
                    } else {
                        query = "UPDATE `friends` SET `friend_nickname` = '" + friend.getNickname() + "', `best_friends` = " + (friend.isBestFriend() ? 1 : 0) + " WHERE `player_uuid` = '" + user.getUuid().toString() + "' AND `friend_uuid` = '" + friend.getUuid().toString() + "';";
                    }
                    statement = connection.prepareStatement(query);
                    statement.execute();
                }

                // We need to delete any friends that are no longer friends.
                query = "SELECT * FROM `friends` WHERE `player_uuid` = '" + user.getUuid().toString() + "';";
                statement = connection.prepareStatement(query);
                rs = statement.executeQuery();
                while (rs.next()) {
                    UUID friendUUID = UUID.fromString(rs.getString("friend_uuid"));
                    if (user.getFriends().stream().noneMatch(friend -> friend.getUuid().equals(friendUUID))) {
                        query = "DELETE FROM `friends` WHERE `player_uuid` = '" + user.getUuid().toString() + "' AND `friend_uuid` = '" + friendUUID.toString() + "';";
                        statement = connection.prepareStatement(query);
                        statement.execute();
                    }
                }
            }

            return true;
        } catch (Exception e) {
            Log("Unable to save user friends to database.", e);
        }
        return false;
    }

    /**
     * This will update the users experience bar.
     *
     * @param user The user to update.
     */
    public static void updateExperienceBar(User user) {
        int experience = user.getExperience();
        int level = user.getLevel();
        int experienceNeeded = Utils.getExperienceRequiredForLevel(level + 1);
        double experiencePercentage = (double) experience / experienceNeeded;

        // Set the experience bar.
        Bukkit.getPlayer(user.getUuid()).setExp((float) experiencePercentage);
        // Set the level.
        Bukkit.getPlayer(user.getUuid()).setLevel(level);
    }

    private static Reward getReward(Connection connection, ResultSet questRS) throws SQLException {
        String rewardUUID = questRS.getString("reward");
        String rewardQuery = "SELECT * FROM `rewards` WHERE `uuid` = '" + rewardUUID + "';";
        PreparedStatement rewardStatement = connection.prepareStatement(rewardQuery);
        ResultSet rewardRS = rewardStatement.executeQuery();
        if (!rewardRS.next()) return null;

        return new Reward(UUID.fromString(rewardUUID), rewardRS.getString("message"),
                new ItemStack(Material.AIR), 0, rewardRS.getInt("coins"), rewardRS.getInt("experience"));
    }

    private static void Log(String message, Object... args) {
        System.out.println("[RavenAPI] " + message + "\n" + Arrays.toString(args));
    }

}
