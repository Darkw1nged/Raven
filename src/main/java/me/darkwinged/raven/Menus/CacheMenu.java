package me.darkwinged.raven.Menus;

import me.darkwinged.raven.Items.Items_Cache;
import me.darkwinged.raven.Stuts.Cache;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.DataCache;
import me.darkwinged.raven.Utilites.Menu;
import me.darkwinged.raven.Utilites.MenuOwnerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CacheMenu extends Menu {

    private final User user;

    public CacheMenu(MenuOwnerUtil menuOwnerUtil) {
        super(menuOwnerUtil);
        this.user = DataCache.users.get(menuOwnerUtil.getOwner().getUniqueId());
    }

    @Override
    public String getMenuName() {
        return "Mythic Cache";
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

        if (item.isSimilar(Items_Cache.gift(user))) {
            new CacheGiftMenu(new MenuOwnerUtil(player)).open();
        } else if (item.isSimilar(Items_Cache.openMulti(user))) {
//            player.openInventory(new CacheOpenMultiMenu(new MenuOwnerUtil(player)).getInventory());
        } else if (item.isSimilar(Items_Cache.craftCaches(user))) {
//            player.openInventory(new CacheCraftMenu(new MenuOwnerUtil(player)).getInventory());
        }
    }

    @Override
    public void setMenuItems() {
        if (user.getCaches() == null || user.getCaches().isEmpty()) {
            inventory.setItem(22, Items_Cache.noMysticCachesFound());
        } else {
            int i = 0;
            for (Cache cache : user.getCaches()) {
                inventory.setItem(i, Items_Cache.create(cache.getRarity(), cache.getExpireInDays(), cache.getRewards()));
                i++;
            }
        }

        inventory.setItem(47, Items_Cache.gift(user));
        inventory.setItem(48, Items_Cache.openMulti(user));
        inventory.setItem(50, Items_Cache.information());
        inventory.setItem(51, Items_Cache.craftCaches(user));
    }

}
