package me.darkwinged.raven.utilites.storage;

import me.darkwinged.raven.Raven;

import java.sql.SQLException;

public class SQLTables {

    public static void load() {
        try {
            // Players
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS players (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "raven_coins INT(16) DEFAULT 0," +
                            "experience INT(16) DEFAULT 0," +
                            "level INT(16) DEFAULT 0," +
                            "`rank` VARCHAR(16) DEFAULT 'DEFAULT'," +
                            "shadow_essence INT(16) DEFAULT 0," +
                            "first_join DATETIME  DEFAULT CURRENT_TIMESTAMP," +
                            "recent_join DATETIME  DEFAULT CURRENT_TIMESTAMP," +
                            "last_seen DATETIME  DEFAULT CURRENT_TIMESTAMP," +
                            "caches_gifted INT(16) DEFAULT 0," +
                            "caches_received INT(16) DEFAULT 0," +
                            "caches_found INT(16) DEFAULT 0," +
                            "caches_opened INT(16) DEFAULT 0," +
                            "friend_notifications TINYINT(1) DEFAULT 1," +
                            "player_visibility TINYINT(1) DEFAULT 1," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Blocked Players
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS blocked_players (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "player_uuid VARCHAR(36) NOT NULL," +
                            "blocked_uuid VARCHAR(36) NOT NULL," +
                            "blocked_since DATETIME  DEFAULT CURRENT_TIMESTAMP," +
                            "PRIMARY KEY (uuid, blocked_uuid));"
            ).executeUpdate();

            // Friends
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS friends (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "player_uuid VARCHAR(36) NOT NULL," +
                            "friend_uuid VARCHAR(36) NOT NULL," +
                            "friend_nickname VARCHAR(16)," +
                            "friends_since DATETIME  DEFAULT CURRENT_TIMESTAMP," +
                            "best_friends TINYINT(1) DEFAULT 0," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Guilds
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS guilds (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "owner_uuid VARCHAR(36) NOT NULL," +
                            "name VARCHAR(16) NOT NULL," +
                            "prefix VARCHAR(16)," +
                            "description VARCHAR(16) NOT NULL," +
                            "open TINYINT(1) DEFAULT 0," +
                            "created DATETIME  DEFAULT CURRENT_TIMESTAMP," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Guild Members
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS guild_members (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "member_uuid VARCHAR(36) NOT NULL," +
                            "guild_uuid VARCHAR(36) NOT NULL," +
                            "`rank` VARCHAR(16) DEFAULT 'MEMBER'," +
                            "PRIMARY KEY (uuid, guild_uuid));"
            ).executeUpdate();

            // Invited Guild Members
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS invited_guild_members (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "invited_uuid VARCHAR(36) NOT NULL," +
                            "guild_uuid VARCHAR(36) NOT NULL," +
                            "created DATETIME  DEFAULT CURRENT_TIMESTAMP," +
                            "PRIMARY KEY (uuid, guild_uuid));"
            ).executeUpdate();

            // Banned Guild Members
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS banned_guild_members (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "banned_uuid VARCHAR(36) NOT NULL," +
                            "guild_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid, guild_uuid));"
            ).executeUpdate();

            // Parties
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS parties (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "owner_uuid VARCHAR(36) NOT NULL," +
                            "open TINYINT(1) DEFAULT 0," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Party Members
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS party_members (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "member_uuid VARCHAR(36) NOT NULL," +
                            "party_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid, party_uuid));"
            ).executeUpdate();

            // Invited Party Members
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS invited_party_members (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "invited_uuid VARCHAR(36) NOT NULL," +
                            "party_uuid VARCHAR(36) NOT NULL," +
                            "created DATETIME  DEFAULT CURRENT_TIMESTAMP," +
                            "PRIMARY KEY (uuid, party_uuid));"
            ).executeUpdate();

            // Achievements
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS achievements (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "name VARCHAR(36) NOT NULL," +
                            "description VARCHAR(36) NOT NULL," +
                            "reward_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Player Achievements
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS player_achievements (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "achievement_uuid VARCHAR(36) NOT NULL," +
                            "player_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Quest
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS quests (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "name VARCHAR(36) NOT NULL," +
                            "description VARCHAR(36) NOT NULL," +
                            "reward_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Player Quests
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS player_quests (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "quest_uuid VARCHAR(36) NOT NULL," +
                            "player_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Rewards
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS rewards (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "message VARCHAR(255)," +
                            "item VARCHAR(255)," +
                            "coins INT(16)," +
                            "experience INT(16)," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Booster
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS boosters (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "tier INT(16) NOT NULL," +
                            "experience DECIMAL(16, 2) NOT NULL," +
                            "coin DECIMAL(16, 2) NOT NULL," +
                            "duration LONG NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Player Boosters
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS player_boosters (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "booster_uuid VARCHAR(36) NOT NULL," +
                            "player_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Player Caches
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS player_caches (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "cache_uuid VARCHAR(36) NOT NULL," +
                            "player_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Mystic Caches
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS mystic_caches (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "rairty INT(16) NOT NULL," +
                            "found_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                            "expires_in LONG NOT NULL," +
                            "reward_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Cache Rewards
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS cache_rewards (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "message VARCHAR(255)," +
                            "command VARCHAR(255)," +
                            "coins INT(16)," +
                            "experience INT(16)," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Player Caches
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS player_caches (" +
                            "uuid VARCHAR(36) NOT NULL," + // This is the uuid for the data not the player
                            "cache_uuid VARCHAR(36) NOT NULL," +
                            "player_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Gift Packages
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS gift_packages (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

            // Gift Package Milestones
            Raven.sqlLibrary.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS gift_package_milestones (" +
                            "uuid VARCHAR(36) NOT NULL," +
                            "player_uuid VARCHAR(36) NOT NULL," +
                            "milestone INT(16) NOT NULL," +
                            "reward_uuid VARCHAR(36) NOT NULL," +
                            "PRIMARY KEY (uuid));"
            ).executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

}
