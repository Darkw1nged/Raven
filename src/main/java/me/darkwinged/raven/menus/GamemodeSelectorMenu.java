package me.darkwinged.raven.menus;

import me.darkwinged.raven.utilites.Menu;
import me.darkwinged.raven.utilites.MenuOwnerUtil;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GamemodeSelectorMenu extends Menu {

    public GamemodeSelectorMenu(MenuOwnerUtil menuOwnerUtil) {
        super(menuOwnerUtil);
    }

    @Override
    public String getMenuName() {
        return "Game Menu";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }

    @Override
    public void setMenuItems() {

    }
}
