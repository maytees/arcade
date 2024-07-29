package net.matees.arcade.mobrush.settings;

import org.bukkit.Material;

import net.matees.settings.BooleanSetting;

public class RandomMobCount extends BooleanSetting {

    public Boolean randomMobCount = true;

    private static final RandomMobCount INSTANCE = new RandomMobCount();

    private RandomMobCount() {

    }

    public static RandomMobCount getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Random Mob Count";
    }

    @Override
    public String getDescription() {
        return "Toggle random amount of mobs spawned";
    }

    @Override
    public Boolean getSetting() {
        return this.randomMobCount;
    }

    @Override
    public void setSetting(Boolean setting) {
        this.randomMobCount = setting;
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
