package net.matees.itemrush.settings;

import net.matees.Setting;

public class RandomItemCount implements Setting<Boolean> {

    public Boolean randomItemCount = true;

    @Override
    public String getName() {
        return "Random Item Count";
    }

    @Override
    public Boolean getSetting() {
        return this.randomItemCount;
    }

    @Override
    public void setSetting(Boolean setting) {
        this.randomItemCount = setting;
    }
}
