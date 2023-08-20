package net.matees.arcade.mobrush.settings;

import org.bukkit.Material;

import net.matees.settings.BooleanSetting;

public class EnableHostileMobs extends BooleanSetting {

    private boolean setting = true;

    private static final EnableHostileMobs INSTANCE = new EnableHostileMobs();

    private EnableHostileMobs() {
    }

    public static EnableHostileMobs getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Enable Hostile Mobs";
    }

    @Override
    public String getDescription() {
        return "Allows spawning of hostile mobs";
    }

    @Override
    public Boolean getSetting() {
        return setting;
    }

    @Override
    public void setSetting(Boolean setting) {
        this.setting = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.CREEPER_SPAWN_EGG;
    }

    @Override
    public int getMenuItemSlot() {
        return 13;
    }

}
