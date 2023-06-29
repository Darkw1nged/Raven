package me.darkwinged.raven.menus;

import me.darkwinged.raven.items.Items_LobbySelector;
import me.darkwinged.raven.struts.User;
import me.darkwinged.raven.utilites.DataCache;
import me.darkwinged.raven.utilites.Menu;
import me.darkwinged.raven.utilites.MenuOwnerUtil;
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
