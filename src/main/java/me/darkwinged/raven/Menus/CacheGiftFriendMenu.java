package me.darkwinged.raven.Menus;

import me.darkwinged.raven.Stuts.GiftPack;
import me.darkwinged.raven.Utilites.MenuOwnerUtil;
import me.darkwinged.raven.Utilites.PaginatedMenu;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CacheGiftFriendMenu extends PaginatedMenu {

    private final GiftPack giftPack;

    public CacheGiftFriendMenu(MenuOwnerUtil menuOwnerUtil, GiftPack giftPack) {
        super(menuOwnerUtil);
        this.giftPack = giftPack;
    }

    @Override
    public String getMenuName() {
        return "Available Friends";
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
