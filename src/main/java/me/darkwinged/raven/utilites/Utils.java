package me.darkwinged.raven.utilites;

import me.darkwinged.raven.Raven;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {

    private static final long serverStartTime = Raven.serverStartTime;

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String word : list)
            newList.add(ChatColor.translateAlternateColorCodes('&', word));
        return newList;
    }

    public static String formatAmount(int number) {
        return NumberFormat.getInstance(new Locale("en", "US")).format(number);
    }

    public static String formatAmount(double number) {
        return NumberFormat.getInstance(new Locale("en", "US")).format(number);
    }

    public static String convertDate(LocalDateTime date) {
        // Get the time difference between the given date and the current date
        long currentTimeMillis = System.currentTimeMillis();
        long timeDifference = date.toInstant(ZoneOffset.UTC).toEpochMilli() - currentTimeMillis;

        // Calculate the time components in milliseconds
        long weeks = TimeUnit.MILLISECONDS.toDays(timeDifference) / 7;
        long days = TimeUnit.MILLISECONDS.toDays(timeDifference) % 7;
        long hours = TimeUnit.MILLISECONDS.toHours(timeDifference) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeDifference) % 60;

        // Create a StringBuilder to build the result string
        StringBuilder resultBuilder = new StringBuilder();
        int componentCount = 0; // Counter for the number of components added

        if (weeks > 0) {
            resultBuilder.append(weeks).append(" week");
            if (weeks > 1) {
                resultBuilder.append("s");
            }
            resultBuilder.append(", ");

            if (days == 0) {
                return resultBuilder.substring(0, resultBuilder.length() - 2);
            }
            componentCount++;
        }

        if (days > 0) {
            resultBuilder.append(days).append(" day");
            if (days > 1) {
                resultBuilder.append("s");
            }
            resultBuilder.append(", ");

            if (hours == 0) {
                return resultBuilder.substring(0, resultBuilder.length() - 2);
            }
            componentCount++;
        }

        if (hours > 0 && componentCount < 2) {
            resultBuilder.append(hours).append(" hour");
            if (hours > 1) {
                resultBuilder.append("s");
            }
            resultBuilder.append(", ");

            if (minutes == 0) {
                return resultBuilder.substring(0, resultBuilder.length() - 2);
            }
        }

        if (minutes > 0 && componentCount < 2) {
            resultBuilder.append(minutes).append(" minute");
            if (minutes > 1) {
                resultBuilder.append("s");
            }
            resultBuilder.append(", ");

            if (seconds == 0) {
                return resultBuilder.substring(0, resultBuilder.length() - 2);
            }
        }

        if (seconds > 0 && componentCount < 2) {
            resultBuilder.append(seconds).append(" second");
            if (seconds > 1) {
                resultBuilder.append("s");
            }
            resultBuilder.append(", ");
        }

        // remove the last comma and any trailing space
        int lastCommaIndex = resultBuilder.lastIndexOf(",");
        if (lastCommaIndex > 0) {
            resultBuilder.deleteCharAt(lastCommaIndex);
        }

        return resultBuilder.toString();
    }

    public static Location getPlayerLocation(Player player) {
        return player.getLocation();
    }

    public static World getPlayerWorld(Player player) {
        return player.getWorld();
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getVersion();
    }

    public static int getPlayerCount() {
        return Bukkit.getServer().getOnlinePlayers().size();
    }

    public static double getServerTPS() {
        return Math.round(Bukkit.getServer().getTPS()[0] * 100.0) / 100.0;
    }

    public static String getServerRAMUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();
        return "Heap Usage: " + formatAmount(heapUsage.getUsed() / 1024 / 1024) + "MB/" + formatAmount(heapUsage.getMax() / 1024 / 1024) + "MB" + "\n"
                + "Non-Heap Usage: " + formatAmount(nonHeapUsage.getUsed() / 1024 / 1024) + "MB/" + formatAmount(nonHeapUsage.getMax() / 1024 / 1024) + "MB";
    }

    public static int getOnlinePlayerCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    public static List<Player> getPlayers() {
        return new ArrayList<>(Bukkit.getOnlinePlayers());
    }

    public static String getServerIp() {
        return Bukkit.getIp();
    }

    public static int getServerPort() {
        return Bukkit.getPort();
    }

    public static long getUsedMemory() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L;
    }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / 1024L / 1024L;
    }

    public static String getUptime() {
        long uptime = System.currentTimeMillis() - serverStartTime;
        int seconds = (int)(uptime / 1000L);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        hours %= 24;
        minutes %= 60;
        seconds %= 60;
        String uptimeString = "";
        if (days > 0)
            uptimeString = uptimeString + days + " days, ";
        if (hours > 0)
            uptimeString = uptimeString + hours + " hours, ";
        if (minutes > 0)
            uptimeString = uptimeString + minutes + " minutes, ";
        if (seconds > 0)
            uptimeString = uptimeString + seconds + " seconds";
        return uptimeString;
    }

    public static int getItemDurability(String itemType) {
        switch (itemType) {
            case "WOODEN_SWORD":
            case "WOODEN_PICKAXE":
            case "WOODEN_AXE":
            case "WOODEN_SHOVEL":
            case "WOODEN_HOE":
                return 59;
            case "STONE_SWORD":
            case "STONE_PICKAXE":
            case "STONE_AXE":
            case "STONE_SHOVEL":
            case "STONE_HOE":
                return 131;
            case "IRON_SWORD":
            case "IRON_PICKAXE":
            case "IRON_AXE":
            case "IRON_SHOVEL":
            case "IRON_HOE":
                return 250;
            case "GOLDEN_SWORD":
            case "GOLDEN_PICKAXE":
            case "GOLDEN_AXE":
            case "GOLDEN_SHOVEL":
            case "GOLDEN_HOE":
                return 32;
            case "DIAMOND_SWORD":
            case "DIAMOND_PICKAXE":
            case "DIAMOND_AXE":
            case "DIAMOND_SHOVEL":
            case "DIAMOND_HOE":
                return 1561;
            case "NETHERITE_SWORD":
            case "NETHERITE_PICKAXE":
            case "NETHERITE_AXE":
            case "NETHERITE_SHOVEL":
            case "NETHERITE_HOE":
                return 2031;
            case "LEATHER_HELMET":
                return 55;
            case "LEATHER_CHESTPLATE":
                return 80;
            case "LEATHER_LEGGINGS":
                return 75;
            case "LEATHER_BOOTS":
                return 65;
            case "CHAINMAIL_HELMET":
            case "IRON_HELMET":
                return 165;
            case "CHAINMAIL_CHESTPLATE":
            case "IRON_CHESTPLATE":
                return 240;
            case "CHAINMAIL_LEGGINGS":
            case "IRON_LEGGINGS":
                return 225;
            case "CHAINMAIL_BOOTS":
            case "IRON_BOOTS":
                return 195;
            case "GOLDEN_HELMET":
                return 77;
            case "GOLDEN_CHESTPLATE":
                return 112;
            case "GOLDEN_LEGGINGS":
                return 105;
            case "GOLDEN_BOOTS":
                return 91;
            case "DIAMOND_HELMET":
                return 363;
            case "DIAMOND_CHESTPLATE":
                return 528;
            case "DIAMOND_LEGGINGS":
                return 495;
            case "DIAMOND_BOOTS":
                return 429;
            case "NETHERITE_HELMET":
                return 407;
            case "NETHERITE_CHESTPLATE":
                return 592;
            case "NETHERITE_LEGGINGS":
                return 555;
            case "NETHERITE_BOOTS":
                return 481;
        }
        return 0;
    }

    public static int getExperienceRequiredForLevel(int level) {
        double baseAmount = 10000; // The base amount of XP required for a level-up
        double increment = 2500; // Amount that the XP required for a level-up increases by every level-up

        // Calculate the experience required for the given level
        double experience = (increment / 2) * Math.pow(level, 2) + (baseAmount - increment / 2) * level;
        return (int) experience;
    }


}
