package net.matees.settings.global;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import net.matees.settings.Global;
import net.matees.settings.IntegerSetting;

public class WorldBorderSize extends IntegerSetting implements Global {
    private static final WorldBorderSize INSTANCE = new WorldBorderSize();
    private int setting = 500;

    private WorldBorderSize() {
    }

    public static WorldBorderSize getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "World Border Size";
    }

    @Override
    public String getDescription() {
        return "Set world border size";
    }

    @Override
    public Integer getSetting() {
        return this.setting;
    }

    @Override
    public void setSetting(Integer setting) {
        this.setting = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.IRON_BARS;
    }

    @Override
    public int getMenuItemSlot() {
        return 11;
    }

    @Override
    public void onChange() {
        Bukkit.getWorld("world").getWorldBorder().setSize((double) this.getSetting());
    }

}
