package me.darkwinged.raven.commands;

import me.darkwinged.raven.managers.UserManager;
import me.darkwinged.raven.struts.Friend;
import me.darkwinged.raven.struts.User;
import me.darkwinged.raven.utilites.DataCache;
import me.darkwinged.raven.utilites.RavenAPI;
import me.darkwinged.raven.utilites.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class FriendCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("friend")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(Utils.color("&cYou must be a player to execute this command!"));
                return true;
            }

            if (args.length < 1) return showHelpMenu(player);
            User user = DataCache.users.get(player.getUniqueId());
            if (user == null) {
                player.sendMessage(Utils.color("&cAn error occurred while loading your user data!"));
                return true;
            }

            // Help menu
            if (args[0].equalsIgnoreCase("help")) {
                return showHelpMenu(player);
            }
            // Send a friend request
            if (args[0].equalsIgnoreCase("add")) {
                if (args.length < 2) {
                    player.sendMessage(Utils.color("&cUsage: /friend add <player>"));
                    return true;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                if (target.getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou cannot add yourself as a friend!"));
                    return true;
                }
                if (target.isOnline()) {
                    player.sendMessage(Utils.color("&cThat player is not online!"));
                    return true;
                }

                User targetUser = RavenAPI.getUser(target.getUniqueId());
                if (targetUser == null || target.getPlayer() == null) {
                    player.sendMessage(Utils.color("&cAn error occurred please try again later!"));
                    return true;
                }

                if (UserManager.hasFriend(user.getUuid(), targetUser.getUuid())) {
                    player.sendMessage(Utils.color("&cYou are already friends with that player!"));
                    return true;
                }
                if (UserManager.isBlocked(target.getUniqueId(), player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou cannot add that player as a friend!"));
                    return true;
                }

                if (UserManager.pendingFriend(targetUser.getUuid(), player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou have already sent a friend request to that player!"));
                    return true;
                }

                UserManager.addPendingFriend(targetUser.getUuid(), player.getUniqueId());
                player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                player.sendMessage(Utils.color("&eYou have send a friend request to " + target.getName() + "! They have 5 minutes to accept!"));
                player.sendMessage(Utils.color("&d" + "-".repeat(53)));

                // Accept Option
                TextComponent accept = new TextComponent(Utils.color("&a&l[ACCEPT]"));
                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend accept " + player.getName()));
                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.color("&aClick to accept!")).create()));
                // Deny Option
                TextComponent deny = new TextComponent(Utils.color("&c&l[DENY]"));
                deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend deny " + player.getName()));
                deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.color("&cClick to deny!")).create()));
                // Block Option
                TextComponent block = new TextComponent(Utils.color("&7&l[BLOCK]"));
                block.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/block " + player.getName()));
                block.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.color("&7Click to block!")).create()));

                target.getPlayer().sendMessage(Utils.color("&d" + "-".repeat(53)));
                target.getPlayer().sendMessage(Utils.color("&eFriend request from &d" + player.getName()));
                target.getPlayer().sendMessage(Utils.color("&ePick one:" + accept + " &e- " + deny + " &e- " + block));
                target.getPlayer().sendMessage(Utils.color("&d" + "-".repeat(53)));
                return true;
            }
            // Accept a friend request
            if (args[0].equalsIgnoreCase("accept")) {
                if (args.length < 2) {
                    player.sendMessage(Utils.color("&cUsage: /friend accept <player>"));
                    return true;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                if (target.getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou cannot accept your own friend request!"));
                    return true;
                }

                User targetUser = RavenAPI.getUser(target.getUniqueId());
                if (targetUser == null) {
                    player.sendMessage(Utils.color("&cAn error occurred please try again later!"));
                    return true;
                }

                if (UserManager.getUserPendingFriends().get(targetUser.getUuid()).isEmpty()) {
                    player.sendMessage(Utils.color("&cYou do not have any pending friend requests!"));
                    return true;
                }
                if (!UserManager.pendingFriend(targetUser.getUuid(), player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cThat player has not sent you a friend request!"));
                    return true;
                }

                UserManager.removePendingFriend(targetUser.getUuid(), player.getUniqueId());

                Friend friend = new Friend(player.getUniqueId(), false, player.getName(), LocalDateTime.now());
                UserManager.addFriend(targetUser.getUuid(), friend);
                friend = new Friend(targetUser.getUuid(), false, target.getName(), LocalDateTime.now());
                UserManager.addFriend(player.getUniqueId(), friend);

                player.sendMessage(Utils.color("&eYou are now friends with " + target.getName() + "!"));
                return true;
            }
            // Deny a friend request
            if (args[0].equalsIgnoreCase("deny")) {
                if (args.length < 2) {
                    player.sendMessage(Utils.color("&cUsage: /friend deny <player>"));
                    return true;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                if (target.getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou cannot deny your own friend request!"));
                    return true;
                }

                User targetUser = RavenAPI.getUser(target.getUniqueId());
                if (targetUser == null) {
                    player.sendMessage(Utils.color("&cAn error occurred please try again later!"));
                    return true;
                }

                if (UserManager.getUserPendingFriends().get(targetUser.getUuid()).isEmpty()) {
                    player.sendMessage(Utils.color("&cYou do not have any pending friend requests!"));
                    return true;
                }
                if (!UserManager.pendingFriend(targetUser.getUuid(), player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cThat player has not sent you a friend request!"));
                    return true;
                }

                UserManager.removePendingFriend(targetUser.getUuid(), player.getUniqueId());
                player.sendMessage(Utils.color("&eYou have denied " + target.getName() + "'s friend request!"));
                return true;
            }
            // Remove a friend
            if (args[0].equalsIgnoreCase("remove")) {
                if (args.length < 2) {
                    player.sendMessage(Utils.color("&cUsage: /friend remove <player>"));
                    return true;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                if (target.getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou cannot remove yourself as a friend!"));
                    return true;
                }

                User targetUser = RavenAPI.getUser(target.getUniqueId());
                if (targetUser == null || target.getPlayer() == null) {
                    player.sendMessage(Utils.color("&cAn error occurred please try again later!"));
                    return true;
                }

                if (!UserManager.hasFriend(player.getUniqueId(), target.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou are not friends with that player!"));
                    return true;
                }

                UserManager.removeFriend(player.getUniqueId(), target.getUniqueId());
                UserManager.removeFriend(target.getUniqueId(), player.getUniqueId());
                player.sendMessage(Utils.color("&eYou have removed " + target.getName() + " as a friend!"));
                if (target.isOnline()) {
                    target.getPlayer().sendMessage(Utils.color("&e" + player.getName() + " has removed you as a friend!"));
                }
                return true;
            }
            // Remove all friends
            if (args[0].equalsIgnoreCase("removeall")) {
                if (UserManager.getUserFriends().get(player.getUniqueId()).isEmpty()) {
                    player.sendMessage(Utils.color("&cYou do not have any friends!"));
                    return true;
                }

                UserManager.getUserFriends().get(player.getUniqueId()).removeIf(friend -> !friend.isBestFriend());
                player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                player.sendMessage(Utils.color("&eYou have removed all friends! Excluding best friends!"));
                player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                return true;
            }
            // Toggle best friend
            if (args[0].equalsIgnoreCase("best")) {
                if (args.length < 2) {
                    player.sendMessage(Utils.color("&cUsage: /friend best <player>"));
                    return true;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                if (target.getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou cannot toggle yourself as a best friend!"));
                    return true;
                }

                User targetUser = RavenAPI.getUser(target.getUniqueId());
                if (targetUser == null) {
                    player.sendMessage(Utils.color("&cAn error occurred please try again later!"));
                    return true;
                }

                if (!UserManager.hasFriend(user.getUuid(), target.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou are not friends with that player!"));
                    return true;
                }

                Friend friend = UserManager.getFriend(user.getUuid(), target.getUniqueId());
                if (friend == null) {
                    player.sendMessage(Utils.color("&cAn error occurred please try again later!"));
                    return true;
                }

                friend.setBestFriend(!friend.isBestFriend());
                player.sendMessage(Utils.color("&eYou have " + (friend.isBestFriend() ? "added" : "removed") +
                        target.getName() + " as a best friend!"));
                return true;
            }
            // List friends
            if (args[0].equalsIgnoreCase("list")) {
                if (args.length < 2) {
                    if (UserManager.getUserFriends().get(player.getUniqueId()) == null || UserManager.getUserFriends().get(player.getUniqueId()).isEmpty()) {
                        player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                        player.sendMessage(Utils.color("&cYou do not have any friends!"));
                        player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                        return true;
                    }

                    player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                    player.sendMessage(Utils.color("&eYour Friends:"));
                    for (Friend friend : UserManager.getUserFriends().get(player.getUniqueId())) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(friend.getFriend());
                        player.sendMessage(Utils.color("&e- " + target.getName()));
                    }
                    player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                    return true;
                }

                if (args[1].equalsIgnoreCase("best")) {
                    List<Friend> bestFriends = UserManager.getUserFriends().get(player.getUniqueId()).stream().filter(Friend::isBestFriend).toList();

                    player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                    player.sendMessage(Utils.color("&eYour Best Friends:"));
                    for (Friend friend : bestFriends) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(friend.getFriend());
                        player.sendMessage(Utils.color("&e- " + target.getName()));
                    }
                    player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                    return true;
                }

                int page;
                try {
                    page = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(Utils.color("&cUsage: /friend list <page/best>"));
                    return true;
                }

                if (page < 1) {
                    player.sendMessage(Utils.color("&cYou cannot view a page less than 1!"));
                    return true;
                }

                List<Friend> friends = UserManager.getUserFriends().get(player.getUniqueId());
                if (friends.isEmpty()) {
                    player.sendMessage(Utils.color("&cYou do not have any friends!"));
                    return true;
                }

                int maxPage = (int) Math.ceil(friends.size() / 10.0);
                if (page > maxPage) {
                    player.sendMessage(Utils.color("&cYou cannot view a page greater than " + maxPage + "!"));
                    return true;
                }

                player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                player.sendMessage(Utils.color("&eYour Friends:"));
                for (int i = (page - 1) * 10; i < page * 10; i++) {
                    if (i >= friends.size()) {
                        break;
                    }

                    OfflinePlayer target = Bukkit.getOfflinePlayer(friends.get(i).getFriend());
                    player.sendMessage(Utils.color("&e- " + target.getName()));
                }
                player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                return true;
            }
            // List requests
            if (args[0].equalsIgnoreCase("requests")) {
                if (UserManager.getUserPendingFriends().get(player.getUniqueId()) == null || UserManager.getUserPendingFriends().get(player.getUniqueId()).isEmpty()) {
                    player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                    player.sendMessage(Utils.color("&cYou do not have any friend requests!"));
                    player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                    return true;
                }

                player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                player.sendMessage(Utils.color("&eYour Friend Requests:"));
                for (UUID request : UserManager.getUserPendingFriends().get(player.getUniqueId())) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(request);
                    player.sendMessage(Utils.color("&e- " + target.getName()));
                }
                player.sendMessage(Utils.color("&d" + "-".repeat(53)));
                return true;
            }
            // Set nickname
            if (args[0].equalsIgnoreCase("nickname")) {
                if (args.length < 3) {
                    player.sendMessage(Utils.color("&cUsage: /friend nickname <player> <nickname>"));
                    return true;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                if (target.getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou cannot set a nickname for yourself!"));
                    return true;
                }

                User targetUser = RavenAPI.getUser(target.getUniqueId());
                if (targetUser == null) {
                    player.sendMessage(Utils.color("&cAn error occurred please try again later!"));
                    return true;
                }

                if (!UserManager.hasFriend(player.getUniqueId(), target.getUniqueId())) {
                    player.sendMessage(Utils.color("&cYou are not friends with that player!"));
                    return true;
                }

                Friend friend = UserManager.getFriend(player.getUniqueId(), target.getUniqueId());
                friend.setNickname(args[2]);
                player.sendMessage(Utils.color("&eYou have set " + target.getName() + "'s nickname to " +
                        args[2] + "!"));
                return true;
            }
            // Toggle notifications
            if (args[0].equalsIgnoreCase("notifications")) {
                user.setAllowFriendNotifications(!user.isAllowFriendNotifications());
                player.sendMessage(Utils.color("&eYou have " + (user.isAllowFriendNotifications() ? "enabled" : "disabled") +
                        " friend notifications!"));
                return true;
            }
        }
        return false;
    }

    private boolean showHelpMenu(Player player) {
        player.sendMessage(Utils.color("&aFriend Commands:"));
        player.sendMessage(Utils.color("&e/friend accept <player> &b- Accept a friend request"));
        player.sendMessage(Utils.color("&e/friend add <player> &b- Add a player as a friend"));
        player.sendMessage(Utils.color("&e/friend best <player> &b- Toggle a player as a best friend"));
        player.sendMessage(Utils.color("&e/friend deny <player> &b- Deny a friend request"));
        player.sendMessage(Utils.color("&e/friend help &b- View the help menu"));
        player.sendMessage(Utils.color("&e/friend list <page/best> &b- List your friends"));
        player.sendMessage(Utils.color("&e/friend nickname <player> <nickname> &b- Set a nickname of a friend"));
        player.sendMessage(Utils.color("&e/friend notifications &b- Toggle friend join/leave notifications"));
        player.sendMessage(Utils.color("&e/friend remove <player> &b- Remove a player from your friends"));
        player.sendMessage(Utils.color("&e/friend removeall &b- Remove all friends (excluding best friends)"));
        player.sendMessage(Utils.color("&e/friend requests <page> &b- View friend requests"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("friend")) {
            if (args.length == 1) {
                return List.of("accept", "add", "best", "deny", "help", "list", "nickname", "notifications", "remove", "removeall", "requests");
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("best") ||
                        args[0].equalsIgnoreCase("deny") || args[0].equalsIgnoreCase("nickname") || args[0].equalsIgnoreCase("remove")) {
                    return Bukkit.getOnlinePlayers().stream().map(OfflinePlayer::getName).toList();
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("list")) {
                    return List.of("<page>", "best");
                } else if (args[0].equalsIgnoreCase("requests")) {
                    return List.of("<page>");
                }
            }
        }
        return null;
    }

}
