package me.darkwinged.raven.commands;

import me.darkwinged.raven.managers.UserManager;
import me.darkwinged.raven.struts.Cache;
import me.darkwinged.raven.struts.enums.Ranks;
import me.darkwinged.raven.struts.GiftPack;
import me.darkwinged.raven.struts.User;
import me.darkwinged.raven.utilites.DataCache;
import me.darkwinged.raven.utilites.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.*;

public class CacheCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("cache")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(Utils.color("&cYou must be a player to execute this command!"));
                return true;
            }
            User user = DataCache.users.get(player.getUniqueId());
            if (user == null) {
                sender.sendMessage(Utils.color("&cYour data is still loading, please wait a few seconds. Or rejoin the server."));
                return true;
            }

            System.out.println(args.length);

            if (user.getRank().equals(Ranks.ADMIN.getName()) || user.getRank().equals(Ranks.OWNER.getName())) {
                if (args.length < 1 || args.length > 6) {
                    showHelpMenu(player);
                    return true;
                }

                if (args[0].equalsIgnoreCase("help")) {
                    showHelpMenu(player);
                    return true;
                }
                if (args[0].equalsIgnoreCase("place")) {
                    Location loc = player.getLocation();
                    loc.setY(loc.getY() + 2);
                    // move the chest 2 blocks in the direction the player is facing
                    loc.add(loc.getDirection().multiply(2));
                    // set the block to an ender chest
                    loc.getBlock().setType(Material.ENDER_CHEST);
                    // move the chest down 2 blocks
                    loc.setY(loc.getY() - 2);
                    // set the block to an end portal
                    loc.getBlock().setType(Material.END_PORTAL);

                    player.sendMessage(Utils.color("&aSuccessfully created a Mythic Cache!"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("give")) {
                    if (args.length < 4) {
                        player.sendMessage(Utils.color("&c/cache give <player> <cache|pack> <rating|amount> <expiryInDays|total> (total)"));
                        return true;
                    }
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                    User targetUser = DataCache.users.get(target.getUniqueId());
                    if (targetUser == null) {
                        player.sendMessage(Utils.color("&cThat player does not exist!"));
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("cache")) {
                        if (args.length < 5) {
                            player.sendMessage(Utils.color("&c/cache give <player> <cache|pack> <rating|amount> <expiryInDays|total> (total)"));
                            return true;
                        }
                        int rating = Math.min(5, Integer.parseInt(args[3]));
                        int expiry = Integer.parseInt(args[4]);
                        LocalDateTime expiryDate = LocalDateTime.now().plusDays(expiry);
                        int total = 1;
                        if (args.length == 6) {
                            try {
                                total = Integer.parseInt(args[5]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(Utils.color("&c/cache give <player> <cache|pack> <rating|amount> <expiryInDays|total> (total)"));
                                return true;
                            }
                        }

                        for (int i = 0; i < total; i++) {
                            Cache cache = new Cache(UUID.randomUUID(), "", rating, new Date(), expiryDate, new ArrayList<>());
                            UserManager.getUserCaches().get(target.getUniqueId()).add(cache);
                        }
                        player.sendMessage(Utils.color("&aSuccessfully gave " + target.getName() + " " + total + " cache" + (total > 1 ? "s" : "") + "!"));
                        return true;
                    }
                    else if (args[2].equalsIgnoreCase("pack")) {
                        if (args.length < 4) {
                            player.sendMessage(Utils.color("&c/cache give <player> <cache|pack> <rating|amount> <expiryInDays|total> (total)"));
                            return true;
                        }
                        int amount = Integer.parseInt(args[3]);
                        List<Cache> caches = new ArrayList<>();
                        Random random = new Random();
                        int total = 1;
                        if (args.length == 5) {
                            try {
                                total = Integer.parseInt(args[4]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(Utils.color("&c/cache give <player> <cache|pack> <rating|amount> <expiryInDays|total> (total)"));
                                return true;
                            }
                        }

                        for (int i=0; i<total; i++) {
                            for (int j = 0; j < amount; j++) {
                                random.setSeed(System.nanoTime());
                                LocalDateTime expiryDate = LocalDateTime.now().plusDays(random.nextInt(30));
                                caches.add(new Cache(UUID.randomUUID(), "", 5, new Date(), expiryDate, new ArrayList<>()));
                            }
                            GiftPack giftPack = new GiftPack(UUID.randomUUID(), amount, caches);
                            UserManager.getUserGiftPacks().get(target.getUniqueId()).add(giftPack);

                            caches.clear();
                        }

                        if (total == 1) {
                            player.sendMessage(Utils.color("&aSuccessfully gave " + target.getName() + " " + amount + " caches!"));
                        } else {
                            player.sendMessage(Utils.color("&aSuccessfully gave " + target.getName() + " " + total + " packs of " + amount + " caches!"));
                        }
                        return true;
                    }
                    return true;
                }
            } else {
                sender.sendMessage(Utils.color("&cYou do not have permission to execute this command!"));
            }
            return true;
        }

        return false;
    }

    private boolean showHelpMenu(Player player) {
        player.sendMessage(Utils.color("&aCache Commands:"));
        player.sendMessage(Utils.color("&e/cache give <player> <cache|pack> <rating|amount> <expiryInDays|total> <total> &b- Give players either a gift pack or a cache"));
        player.sendMessage(Utils.color("&e/cache help &b- View the help menu"));
        player.sendMessage(Utils.color("&e/cache place &b- Place a cache at your current location"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player player) {
            User user = DataCache.users.get(player.getUniqueId());
            if (user == null) {
                return null;
            }

            if (user.getRank().equals(Ranks.ADMIN.getName()) || user.getRank().equals(Ranks.OWNER.getName())) {
                if (args.length == 1) {
                    return List.of("help", "place", "give");
                }

                if (args.length == 2) {
                    return Bukkit.getOnlinePlayers().stream().map(OfflinePlayer::getName).toList();
                }

                if (args.length == 3) {
                    return List.of("cache", "pack");
                }

                if (args.length == 4) {
                    if (args[2].equalsIgnoreCase("cache")) {
                        return List.of("<rating>");
                    }
                    if (args[2].equalsIgnoreCase("pack")) {
                        return List.of("<amount>");
                    }
                }

                if (args.length == 5) {
                    if (args[2].equalsIgnoreCase("cache")) {
                        return List.of("<expiryInDays>");
                    } else if (args[2].equalsIgnoreCase("pack")) {
                        return List.of("(total)");
                    }
                }

                if (args.length == 6 && args[2].equalsIgnoreCase("cache")) {
                    return List.of("(total)");
                }
            }
        }
        return null;
    }
}
