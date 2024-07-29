package net.matees.arcade.lavarise;

import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.arcade.Minigame;
import net.matees.settings.SettingsMenu;

public class LavaRiseSettingsMenu extends SettingsMenu {

    public LavaRiseSettingsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Minigame getMinigame() {
        return LavaRise.getInstance();
    }

    @Override
    public String getMenuName() {
        return "Lava Rise Settings";
    }

}
