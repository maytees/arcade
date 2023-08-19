package net.matees.arcade.manhunt;

import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.Minigame;
import net.matees.settings.SettingsMenu;

public class ManhuntSettingsMenu extends SettingsMenu {

    public ManhuntSettingsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Minigame getMinigame() {
        return Manhunt.getInstance();
    }

    @Override
    public String getMenuName() {
        return "Manhunt Settings";
    }

}
