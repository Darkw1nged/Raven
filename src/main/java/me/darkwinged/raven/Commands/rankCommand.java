package me.darkwinged.raven.Commands;

import me.darkwinged.raven.Stuts.Enums.Ranks;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.DataCache;
import me.darkwinged.raven.Utilites.RavenAPI;
import me.darkwinged.raven.Utilites.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class rankCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rank")) {
            if (sender instanceof Player player) {
                User user = DataCache.users.get(player.getUniqueId());
                if (user == null) {
                    user = RavenAPI.getUser(player.getUniqueId());
                    if (user == null) {
                        player.sendMessage("§cAn error occurred while getting your data.");
                        return true;
                    }
                    DataCache.users.put(player.getUniqueId(), user);
                }

                if (!user.getRank().equalsIgnoreCase("admin") && !user.getRank().equalsIgnoreCase("owner")) {
                    player.sendMessage(Utils.color("&cYou do not have permission to use this command."));
                    return true;
                }
            }

            if (args.length < 2) {
                sender.sendMessage(Utils.color("&cUsage: /rank <player> <rank>"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(Utils.color("&cThat player does not exist."));
                return true;
            }

            User targetUser = DataCache.users.get(target.getUniqueId());
            if (targetUser == null) {
                targetUser = RavenAPI.getUser(target.getUniqueId());
                if (targetUser == null) {
                    sender.sendMessage(Utils.color("&cAn error occurred while getting " + target.getName() + " data."));
                    return true;
                }
                DataCache.users.put(target.getUniqueId(), targetUser);
            }

            String rank = args[1];
            for (Ranks value : Ranks.values()) {
                rank = rank.substring(0, 1).toUpperCase() + rank.substring(1).toLowerCase();
                if (value.name().equalsIgnoreCase(rank)) {
                    targetUser.setRank(rank);
                    sender.sendMessage(Utils.color("&aYou have set " + target.getName() + "'s rank to " + rank + "."));
                    return true;
                }
            }
            sender.sendMessage(Utils.color("&cThat rank does not exist."));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rank")) {
            if (args.length == 1) {
                return Bukkit.getOnlinePlayers().stream().map(OfflinePlayer::getName).toList();
            } else if (args.length == 2) {
                return Arrays.stream(Ranks.values()).map(Ranks::name).toList();
            }
        }
        return null;
    }
}
