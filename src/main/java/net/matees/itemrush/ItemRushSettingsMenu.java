package net.matees.itemrush;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.settings.Setting;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemRushSettingsMenu extends Menu {
    public ItemRushSettingsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Item Rush Settings";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent inventoryClickEvent) throws MenuManagerNotSetupException, MenuManagerException {
        for(Setting setting : ItemRush.getInstance().getSettings()) {
            if(setting.getMenuItem().isSimilar(inventoryClickEvent.getCurrentItem())) {
                setting.handleItemClick(inventoryClickEvent);

                // Updates?
                inventoryClickEvent.getInventory().clear();
                setting.setMenuItem(setting.getMenuItem());
                this.setMenuItems();

                inventoryClickEvent.getWhoClicked().sendMessage(setting.getSetting().toString());
            }
        }
    }

    @Override
    public void setMenuItems() {
        for(Setting setting : ItemRush.getInstance().getSettings()) {
            inventory.setItem(setting.getMenuItemSlot(), setting.getMenuItem());
        }
    }
}
