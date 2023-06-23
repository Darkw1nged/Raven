package me.darkwinged.raven.Utilites;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Hopper;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WorldUtils {

    public static String updateWeather(World world) { return world.hasStorm() ? "Sunny" : "Raining"; }

    public static List<Chunk> getLoadedChunks(World world) {
        return Arrays.asList(world.getLoadedChunks());
    }

    public boolean isFullyGrown(Block block) {
        return ((Ageable) block.getBlockData()).getAge() == ((Ageable) block.getBlockData()).getMaximumAge();
    }

    public static List<Entity> getNearbyEntities(Location location, double radius) {
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : location.getWorld().getEntities()) {
            if (entity.getLocation().distance(location) <= radius) {
                entities.add(entity);
            }
        }
        return entities;
    }

    public static boolean isHopperEmpty(Hopper hopper) {
        for (int i = 0; i < hopper.getInventory().getSize(); i++) {
            if (hopper.getInventory().getItem(i) != null) {
                return false;
            }
        }
        return true;
    }

    public static List<Entity> getEntitiesInChunk(Chunk chunk, boolean excludePlayers) {
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : chunk.getEntities()) {
            if (excludePlayers && entity instanceof Player) {
                continue;
            }
            entities.add(entity);
        }
        return entities;
    }

    public static List<Entity> getEntitiesInWorld(World world, boolean excludePlayers) {
        List<Entity> entities = new ArrayList<>();
        for (Chunk chunk : world.getLoadedChunks()) {
            for (Entity entity : chunk.getEntities()) {
                if (excludePlayers && entity instanceof Player) {
                    continue;
                }
                entities.add(entity);
            }
        }
        return entities;
    }

    public static List<Entity> getAnimalsInChunk(Chunk chunk) {
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : chunk.getEntities()) {
            if (entity instanceof Animals) {
                entities.add(entity);
            }
        }
        return entities;
    }

    public static List<Entity> getAnimalsInWorld(World world) {
        List<Entity> entities = new ArrayList<>();
        for (Chunk chunk : world.getLoadedChunks()) {
            for (Entity entity : chunk.getEntities()) {
                if (entity instanceof Animals) {
                    entities.add(entity);
                }
            }
        }
        return entities;
    }

    public static List<Entity> getMobsInChunk(Chunk chunk) {
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : chunk.getEntities()) {
            if (entity instanceof Monster) {
                entities.add(entity);
            }
        }
        return entities;
    }

    public static List<Entity> getMobsInWorld(World world) {
        List<Entity> entities = new ArrayList<>();
        for (Chunk chunk : world.getLoadedChunks()) {
            for (Entity entity : chunk.getEntities()) {
                if (entity instanceof Monster) {
                    entities.add(entity);
                }
            }
        }
        return entities;
    }

    public static  List<BlockState> getSpawnersInChunk(Chunk chunk) {
        List<BlockState> blocks = new ArrayList<>();
        for (BlockState block : chunk.getTileEntities()) {
            if (block.getType() == Material.SPAWNER) {
                blocks.add(block);
            }
        }
        return blocks;
    }

    public static List<BlockState> getSpawnersInWorld(World world) {
        List<BlockState> blocks = new ArrayList<>();
        for (Chunk chunk : world.getLoadedChunks()) {
            for (BlockState block : chunk.getTileEntities()) {
                if (block.getType() == Material.SPAWNER) {
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public static List<BlockState> getActiveHoppersInChunk(Chunk chunk) {
        List<BlockState> blocks = new ArrayList<>();
        for (BlockState block : chunk.getTileEntities()) {
            if (block.getType() == Material.HOPPER) {
                Hopper hopper = (Hopper) block;
                if (!isHopperEmpty(hopper)) {
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public static List<BlockState> getActiveHoppersInWorld(World world) {
        List<BlockState> blocks = new ArrayList<>();
        for (Chunk chunk : world.getLoadedChunks()) {
            for (BlockState block : chunk.getTileEntities()) {
                if (block.getType() == Material.HOPPER) {
                    Hopper hopper = (Hopper) block;
                    if (!isHopperEmpty(hopper)) {
                        blocks.add(block);
                    }
                }
            }
        }
        return blocks;
    }

    public static List<BlockState> getInactiveHoppersInChunk(Chunk chunk) {
        List<BlockState> blocks = new ArrayList<>();
        for (BlockState block : chunk.getTileEntities()) {
            if (block.getType() == Material.HOPPER) {
                Hopper hopper = (Hopper) block;
                if (isHopperEmpty(hopper)) {
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public static List<BlockState> getInactiveHoppersInWorld(World world) {
        List<BlockState> blocks = new ArrayList<>();
        for (Chunk chunk : world.getLoadedChunks()) {
            for (BlockState block : chunk.getTileEntities()) {
                if (block.getType() == Material.HOPPER) {
                    Hopper hopper = (Hopper) block;
                    if (isHopperEmpty(hopper)) {
                        blocks.add(block);
                    }
                }
            }
        }
        return blocks;
    }

    public static Biome getBiomeAtLocation(Location location) {
        return location.getBlock().getBiome();
    }

    public static int getHighestBlockYAtLocation(Location location) {
        return location.getWorld().getHighestBlockYAt(location);
    }

    public static WeatherType getWeather(World world) {
        return world.hasStorm() ? WeatherType.DOWNFALL : WeatherType.CLEAR;
    }

    public static void setWeather(World world, WeatherType weather) {
        world.setStorm(weather == WeatherType.DOWNFALL);
    }

    public static void setTime(World world, long time) {
        world.setTime(time);
    }

    public static int getTotalEntitiesCount(World world) {
        return world.getEntities().size();
    }

    public static List<Player> getPlayersInWorld(World world) {
        List<Player> players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().equals(world)) {
                players.add(player);
            }
        }
        return players;
    }

    public static Block getBlockAt(Location location) {
        return location.getBlock();
    }

    public static void setBlockAt(Location location, Material material, BlockData data) {
        Block block = location.getBlock();
        block.setType(material);
        block.setBlockData(data);
    }

    public static List<Player> getPlayersNearLocation(Location location, double radius) {
        List<Player> players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().distance(location) <= radius) {
                players.add(player);
            }
        }
        return players;
    }

    public static void generateWorldBorder(World world, Location center, int radius, int borderSize) {
        WorldBorder border = world.getWorldBorder();
        border.setCenter(center);
        border.setSize(radius * 2);
        border.setWarningDistance(5);
        border.setWarningTime(5);
        border.setDamageAmount(1.0);
        border.setDamageBuffer(5.0);
    }

    public static long getWorldSize(String worldName) {
        File worldFolder = new File(Bukkit.getWorldContainer(), worldName);
        long size = 0;
        if (worldFolder.exists()) {
            for (File file : Objects.requireNonNull(worldFolder.listFiles())) {
                size += file.length();
                if (file.isDirectory()) {
                    size += getFolderSize(file);
                }
            }
        }
        return size;
    }

    private static long getFolderSize(File folder) {
        long size = 0;
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            size += file.length();
            if (file.isDirectory()) {
                size += getFolderSize(file);
            }
        }
        return size;
    }

    public static boolean isLocationWithinRadius(Location location, Location center, double radius) {
        if (location.getWorld() != center.getWorld()) {
            return false;
        }
        double dx = location.getX() - center.getX();
        double dy = location.getY() - center.getY();
        double dz = location.getZ() - center.getZ();
        double distanceSquared = dx * dx + dy * dy + dz * dz;
        return distanceSquared <= radius * radius;
    }

    public static String getFriendlyMaterialName(Material material) {
        String[] parts = material.name().toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(part.substring(0, 1).toUpperCase()).append(part.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public static boolean isCropFullyGrown(Block block) {
        Ageable ageable = (Ageable)block.getBlockData();
        return (ageable.getAge() == ageable.getMaximumAge());
    }

    public static String getTime(Player player) {
        long rawTime = player.getWorld().getTime();
        long hours = rawTime / 1000L + 6L;
        long rawMinutes = rawTime % 1000L * 60L / 1000L;
        String clock = "am";
        if (hours >= 12L) {
            hours -= 12L;
            clock = "pm";
        }
        if (hours >= 12L) {
            hours -= 12L;
            clock = "am";
        }
        if (hours == 0L)
            hours = 12L;
        String minutes = "0" + rawMinutes;
        minutes = minutes.substring(minutes.length() - 2);
        return hours + ":" + minutes + "&e" + clock;
    }

}
