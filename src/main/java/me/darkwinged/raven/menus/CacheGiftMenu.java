package me.darkwinged.raven.menus;

import me.darkwinged.raven.items.Items_Cache;
import me.darkwinged.raven.Raven;
import me.darkwinged.raven.struts.*;
import me.darkwinged.raven.utilites.DataCache;
import me.darkwinged.raven.utilites.Menu;
import me.darkwinged.raven.utilites.MenuOwnerUtil;
import me.darkwinged.raven.utilites.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class CacheGiftMenu extends Menu {

    private final User user;

    public CacheGiftMenu(MenuOwnerUtil menuOwnerUtil) {
        super(menuOwnerUtil);
        this.user = DataCache.users.get(menuOwnerUtil.getOwner().getUniqueId());
    }

    @Override
    public String getMenuName() {
        return "Gift Inventory";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        User user = DataCache.users.get(player.getUniqueId());

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        if (item.isSimilar(Items_Cache.back())) {
            player.closeInventory();
            new CacheMenu(new MenuOwnerUtil(player)).open();
        }

        if (item.hasItemMeta()) {
            // Gift Pack
            if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Raven.getInstance, "gift-pack"), PersistentDataType.INTEGER)) {
                int amount = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Raven.getInstance, "gift-pack"), PersistentDataType.INTEGER);
                if (user.getGiftPacks().stream().anyMatch(pack -> pack.amount() == amount)) {
                    GiftPack pack = user.getGiftPacks().stream().filter(p -> p.amount() == amount).findFirst().orElse(null);
                    if (pack != null) {
//                        new CacheGiftFriendMenu(new MenuOwnerUtil(player), pack).open();
                        user.getGiftPacks().remove(pack);
                        user.setCachesGifted(user.getCachesGifted() + 1);
                        new CacheGiftMenu(new MenuOwnerUtil(player)).open();
                    } else {
                        player.sendMessage(Utils.color("&cAn error has occurred!"));
                    }
                }
            }

            // Milestone
            if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Raven.getInstance, "milestone"), PersistentDataType.INTEGER)) {
                int milestone = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Raven.getInstance, "milestone"), PersistentDataType.INTEGER);
                switch (milestone) {
                    case 1 -> {
                        Milestone foundMilestone = user.getGiftMilestones().stream().filter(m -> m.getRequired() == 1).findFirst().orElse(null);
                        ClaimMilestone(player, foundMilestone);
                    }
                    case 5 -> {
                        Milestone foundMilestone = user.getGiftMilestones().stream().filter(m -> m.getRequired() == 5).findFirst().orElse(null);
                        ClaimMilestone(player, foundMilestone);
                    }
                    case 10 -> {
                        Milestone foundMilestone = user.getGiftMilestones().stream().filter(m -> m.getRequired() == 10).findFirst().orElse(null);
                        ClaimMilestone(player, foundMilestone);
                    }
                    case 25 -> {
                        Milestone foundMilestone = user.getGiftMilestones().stream().filter(m -> m.getRequired() == 25).findFirst().orElse(null);
                        ClaimMilestone(player, foundMilestone);
                    }
                    case 50 -> {
                        Milestone foundMilestone = user.getGiftMilestones().stream().filter(m -> m.getRequired() == 50).findFirst().orElse(null);
                        ClaimMilestone(player, foundMilestone);
                    }
                    case 100 -> {
                        Milestone foundMilestone = user.getGiftMilestones().stream().filter(m -> m.getRequired() == 100).findFirst().orElse(null);
                        ClaimMilestone(player, foundMilestone);
                    }
                }
            }
        }

    }

    @Override
    public void setMenuItems() {
        if (user.getGiftPacks() == null || user.getGiftPacks().isEmpty()) {
            inventory.setItem(22, Items_Cache.noGiftPacksFound());
        } else {
            int i = 0;
            for (GiftPack pack : user.getGiftPacks()) {
                inventory.setItem(i, Items_Cache.giftBox(pack.amount()));
                i++;
            }
        }

        inventory.setItem(37, Items_Cache.contributor(user));
        inventory.setItem(38, Items_Cache.milestone(user, 1, new MilestoneReward(UUID.randomUUID(), DataCache.cacheGiftMilestoneOneCommands, DataCache.cacheGiftMilestoneOne)));
        inventory.setItem(39, Items_Cache.milestone(user, 5, new MilestoneReward(UUID.randomUUID(), DataCache.cacheGiftMilestoneTwoCommands, DataCache.cacheGiftMilestoneTwo)));
        inventory.setItem(40, Items_Cache.milestone(user, 10, new MilestoneReward(UUID.randomUUID(), DataCache.cacheGiftMilestoneThreeCommands, DataCache.cacheGiftMilestoneThree)));
        inventory.setItem(41, Items_Cache.milestone(user, 25, new MilestoneReward(UUID.randomUUID(), DataCache.cacheGiftMilestoneFourCommands, DataCache.cacheGiftMilestoneFour)));
        inventory.setItem(42, Items_Cache.milestone(user, 50, new MilestoneReward(UUID.randomUUID(), DataCache.cacheGiftMilestoneFiveCommands, DataCache.cacheGiftMilestoneFive)));
        inventory.setItem(43, Items_Cache.milestone(user, 100, new MilestoneReward(UUID.randomUUID(), DataCache.cacheGiftMilestoneSixCommands, DataCache.cacheGiftMilestoneSix)));

        inventory.setItem(49, Items_Cache.back());
    }

    private void ClaimMilestone(Player player, Milestone foundMilestone) {
        System.out.println("Attempting to claim milestone");
        if (foundMilestone != null && !foundMilestone.isClaimed()) {
            foundMilestone.setClaimed(true);
            foundMilestone.getReward().getCommands().forEach(command -> {
                String cmd = command.replace("%player%", player.getName());
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
            });

            player.sendMessage(Utils.color("&aYou have claimed the milestone reward!"));
            player.openInventory(new CacheGiftMenu(new MenuOwnerUtil(player)).getInventory());
        }
    }
}
