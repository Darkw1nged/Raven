package me.darkwinged.raven.menus;

import me.darkwinged.raven.items.Items_Profile;
import me.darkwinged.raven.struts.User;
import me.darkwinged.raven.utilites.DataCache;
import me.darkwinged.raven.utilites.Menu;
import me.darkwinged.raven.utilites.MenuOwnerUtil;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ProfileMenu extends Menu {

    private final User user;

    public ProfileMenu(MenuOwnerUtil menuOwnerUtil) {
        super(menuOwnerUtil);
        this.user = DataCache.users.get(menuOwnerUtil.getOwner().getUniqueId());
    }

    @Override
    public String getMenuName() {
        return "My Profile";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }

    @Override
    public void setMenuItems() {
        inventory.setItem(2, Items_Profile.recentPlayers());
        inventory.setItem(3, Items_Profile.friends());
        inventory.setItem(4, Items_Profile.profile(user));
        inventory.setItem(5, Items_Profile.party());
        inventory.setItem(6, Items_Profile.guild());

        for (int i=9; i < 18; i++) {
            inventory.setItem(i, FILLER_GLASS);
        }

        inventory.setItem(21, Items_Profile.boosters());
        inventory.setItem(22, Items_Profile.character(user));
        inventory.setItem(23, Items_Profile.stats());

        inventory.setItem(30, Items_Profile.achievements());
        inventory.setItem(31, Items_Profile.level(user));
        inventory.setItem(32, Items_Profile.quests());

        inventory.setItem(39, Items_Profile.appearance());
        inventory.setItem(41, Items_Profile.recentGames());


    }
}
