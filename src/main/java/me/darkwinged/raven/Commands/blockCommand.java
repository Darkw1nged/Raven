package me.darkwinged.raven.Commands;

import me.darkwinged.raven.Stuts.Friend;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.DataCache;
import me.darkwinged.raven.Utilites.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class blockCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("block")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(Utils.color("&cYou must be a player to execute this command."));
                return true;
            }
            User user = DataCache.users.get(player.getUniqueId());
            if (user == null) {
                player.sendMessage(Utils.color("&cAn error occurred while getting your data."));
                return true;
            }

            if (args.length < 1) {
                player.sendMessage(Utils.color("&cUsage: /block <player>"));
                return true;
            }

            Player target = player.getServer().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Utils.color("&cThat player does not exist."));
                return true;
            }

            if (target == player) {
                player.sendMessage(Utils.color("&cYou cannot block yourself."));
                return true;
            }

            if (user.hasBlockedPlayer(target.getUniqueId())) {
                player.sendMessage(Utils.color("&cYou have already blocked " + target.getName() + "."));
                return true;
            }

            if (user.hasFriend(target.getUniqueId())) {
                user.removeFriend(user.getFriend(target.getUniqueId()));
                User targetUser = DataCache.users.get(target.getUniqueId());
                if (targetUser != null) {
                    targetUser.removeFriend(targetUser.getFriend(player.getUniqueId()));
                }
                return true;
            }

            user.blockPlayer(target.getUniqueId());
            player.sendMessage(Utils.color("&aYou have blocked " + target.getName() + "."));
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("block")) {
            if (args.length == 1) {
                return Bukkit.getOnlinePlayers().stream().map(OfflinePlayer::getName).toList();
            }
        }
        return null;
    }
}
