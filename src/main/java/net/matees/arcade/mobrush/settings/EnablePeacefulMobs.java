package net.matees.arcade.mobrush.settings;

import org.bukkit.Material;

import net.matees.settings.BooleanSetting;

public class EnablePeacefulMobs extends BooleanSetting {

    private Boolean setting = true;
    private static final EnablePeacefulMobs INSTANCE = new EnablePeacefulMobs();

    private EnablePeacefulMobs() {
    }

    public static EnablePeacefulMobs getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Enable Peaceful Mobs";
    }

    @Override
    public String getDescription() {
        return "Allows spawning of peaceful mobs";
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
        return Material.SHEEP_SPAWN_EGG;
    }

    @Override
    public int getMenuItemSlot() {
        return 14;
    }

}
