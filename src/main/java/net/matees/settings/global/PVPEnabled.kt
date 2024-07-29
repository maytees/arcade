package net.matees.settings.global;

import org.bukkit.Material;

import net.matees.settings.BooleanSetting;
import net.matees.settings.Global;

public class PVPEnabled extends BooleanSetting implements Global {

    private static final PVPEnabled INSTANCE = new PVPEnabled();
    private Boolean setting = true;

    private PVPEnabled() {
    }

    public static PVPEnabled getInstance() {
        return INSTANCE;
    }

    @Override
    public void onChange() {
    }

    @Override
    public String getName() {
        return "PVP Enabled";
    }

    @Override
    public String getDescription() {
        return "Enable or disable PVP";
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
        return Material.IRON_SWORD;
    }

    @Override
    public int getMenuItemSlot() {
        return 14;
    }

}
