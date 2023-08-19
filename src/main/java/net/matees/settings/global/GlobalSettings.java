package net.matees.settings.global;

import java.util.ArrayList;
import java.util.List;

import net.matees.settings.Setting;

public class GlobalSettings {
    private final static GlobalSettings INSTANCE = new GlobalSettings();
    private List<Setting> settings = List.of(
            EnableWorldBorder.getInstance(),
            WorldBorderSize.getInstance());

    private GlobalSettings() {
    }

    public static GlobalSettings getInstance() {
        return INSTANCE;
    }

    public List<Setting> getSettings() {
        return this.settings;
    }

    public Setting getSetting(String name) {
        for (Setting setting : this.getSettings()) {
            if (setting.getName().equals(name)) {
                return setting;
            }
        }

        return null;
    }
}
