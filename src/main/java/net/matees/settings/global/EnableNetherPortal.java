package net.matees.settings.global;

import org.bukkit.Material;

import net.matees.settings.BooleanSetting;
import net.matees.settings.Global;

public class EnableNetherPortal extends BooleanSetting implements Global {

    private static final EnableNetherPortal INSTANCE = new EnableNetherPortal();
    private Boolean setting = false;

    private EnableNetherPortal() {
    }

    public static EnableNetherPortal getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Enable Nether Portal";
    }

    @Override
    public String getDescription() {
        return "Enable or disable nether portals";
    }

    @Override
    public Boolean getSetting() {
        return this.setting;
    }

    @Override
    public void setSetting(Boolean setting) {
        this.setting = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.OBSIDIAN;
    }

    @Override
    public int getMenuItemSlot() {
        return 13;
    }

    @Override
    public void onChange() {
    }

}
