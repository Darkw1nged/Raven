package me.darkwinged.raven.Menus;

import me.darkwinged.raven.Utilites.Menu;
import me.darkwinged.raven.Utilites.MenuOwnerUtil;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CollectiblesMenu extends Menu {

    public CollectiblesMenu(MenuOwnerUtil menuOwnerUtil) {
        super(menuOwnerUtil);
    }

    @Override
    public String getMenuName() {
        return "Collectibles";
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
