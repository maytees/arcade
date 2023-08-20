package net.matees.arcade.lavarise.settings;

import org.bukkit.Material;

import net.matees.settings.BooleanSetting;

public class OnlyAirBlock extends BooleanSetting {

    private static final OnlyAirBlock INSTANCE = new OnlyAirBlock();
    private Boolean setting;

    private OnlyAirBlock() {
    }

    public static OnlyAirBlock getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Only Air Block";
    }

    @Override
    public String getDescription() {
        return "Determines whether only air blocks should be replaced with lava";
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
        return Material.BARRIER;
    }

    @Override
    public int getMenuItemSlot() {
        return 10;
    }

}
