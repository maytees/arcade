package net.matees.itemrush.settings;

import net.matees.Setting;

public class MaxItemCount implements Setting<Integer> {

    private Integer maxItemCount = 64;

    @Override
    public String getName() {
        return "Max Item Count";
    }

    @Override
    public Integer getSetting() {
        return maxItemCount;
    }

    public void setSetting(Integer setting) {
        this.maxItemCount = setting;
    }
}
