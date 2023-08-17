package net.matees.itemrush.settings;

import net.matees.settings.BooleanSetting;

import java.util.Random;

import org.bukkit.Material;

public class RandomItemCount extends BooleanSetting {

    public Boolean randomItemCount = true;

    private static final RandomItemCount INSTANCE = new RandomItemCount();

    private RandomItemCount() {
    }

    public static RandomItemCount getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Random Item Count";
    }

    @Override
    public String getDescription() {
        return "Toggle random amount of items dropped";
    }

    @Override
    public Boolean getSetting() {
        return this.randomItemCount;
    }

    @Override
    public void setSetting(Boolean setting) {
        this.randomItemCount = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.REDSTONE_BLOCK;
    }

    @Override
    public int getMenuItemSlot() {
        return 11;
    }
}
