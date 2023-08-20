package net.matees.arcade.mobrush;

import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.arcade.Minigame;
import net.matees.settings.SettingsMenu;

public class MobRushSettingsMenu extends SettingsMenu {

    public MobRushSettingsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Mob Rush Settings";
    }

    @Override
    public Minigame getMinigame() {
        return MobRush.getInstance();
    }
}
