package net.matees.arcade.itemrush;

import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.arcade.Minigame;
import net.matees.settings.SettingsMenu;

public class ItemRushSettingsMenu extends SettingsMenu {
    public ItemRushSettingsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Item Rush Settings";
    }

    @Override
    public Minigame getMinigame() {
        return ItemRush.getInstance();
    }
}
