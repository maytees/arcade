package net.matees.settings.global;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import net.matees.settings.BooleanSetting;
import net.matees.settings.Global;

public class EnableWorldBorder extends BooleanSetting implements Global {

    private static final EnableWorldBorder INSTANCE = new EnableWorldBorder();
    private Boolean setting = false;

    private EnableWorldBorder() {
    }

    public static EnableWorldBorder getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Enable World Border";
    }

    @Override
    public String getDescription() {
        return "Determintes whether there is a world border";
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
        return Material.CYAN_GLAZED_TERRACOTTA;
    }

    @Override
    public int getMenuItemSlot() {
        return 10;
    }

    @Override
    public void onChange() {
        int size = (int) GlobalSettings.getInstance().getSetting("World Border Size").getSetting();

        Bukkit.getWorld("world").getWorldBorder()
                .setSize(this.getSetting()
                        ? (double) size
                        : (double) 30000000);
    }

}
