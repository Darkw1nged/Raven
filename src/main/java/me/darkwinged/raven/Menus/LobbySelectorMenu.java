package me.darkwinged.raven.Menus;

import me.darkwinged.raven.Items.Items_LobbySelector;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.DataCache;
import me.darkwinged.raven.Utilites.Menu;
import me.darkwinged.raven.Utilites.MenuOwnerUtil;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LobbySelectorMenu extends Menu {

    private final User user;

    public LobbySelectorMenu(MenuOwnerUtil menuOwnerUtil) {
        super(menuOwnerUtil);
        this.user = DataCache.users.get(menuOwnerUtil.getOwnerUUID());
    }

    @Override
    public String getMenuName() {
        return "Lobby Selector";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }

    @Override
    public void setMenuItems() {
        for (int i = 1; i < getSlots() + 1; i++) {
            inventory.setItem(i - 1, Items_LobbySelector.joinable(user, i));
        }
    }
}
