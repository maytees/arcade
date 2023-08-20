package net.matees.menus;

import org.bukkit.event.inventory.InventoryClickEvent;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.settings.Global;
import net.matees.settings.Setting;
import net.matees.settings.global.GlobalSettings;

public class GlobalSettingsMenu extends Menu {

    public GlobalSettingsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public String getMenuName() {
        return "Global Settings";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) throws MenuManagerNotSetupException, MenuManagerException {
        for (Setting s : GlobalSettings.getInstance().getSettings()) {
            if (s instanceof Global) {
                if (s.getMenuItem().isSimilar(event.getCurrentItem())) {
                    s.handleItemClick(event);

                    // Weird
                    Global setting = (Global) s;
                    setting.onChange();

                    inventory.clear();
                    s.setMenuItem(s.getMenuItem());
                    this.setMenuItems();
                }
            }
        }
    }

    @Override
    public void setMenuItems() {
        for (Setting setting : GlobalSettings.getInstance().getSettings()) {
            inventory.setItem(setting.getMenuItemSlot(), setting.getMenuItem());
        }
    }

}
