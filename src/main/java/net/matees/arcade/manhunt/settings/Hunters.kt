package net.matees.arcade.manhunt.settings;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import net.matees.settings.PlayersSelectionSetting;

public class Hunters extends PlayersSelectionSetting {

    private List<Player> selectedPlayers;
    private List<Player> unselectedPlayers;
    private List<Player> players;

    private static final Hunters INSTANCE = new Hunters();

    private Hunters() {
    }

    public static Hunters getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Hunters";
    }

    @Override
    public String getDescription() {
        return "Select the hunters";
    }

    @Override
    public List<Player> getSetting() {
        return this.selectedPlayers;
    }

    @Override
    public void setSetting(List<Player> setting) {
        this.selectedPlayers = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.IRON_SWORD;
    }

    @Override
    public int getMenuItemSlot() {
        return 12;
    }

}
