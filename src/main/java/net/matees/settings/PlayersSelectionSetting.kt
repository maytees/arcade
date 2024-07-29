package net.matees.settings;

import java.util.List;

import org.bukkit.entity.Player;

public abstract class PlayersSelectionSetting extends Setting<List<Player>> {

    @Override
    public void setIntValue(int value) {
    }

    @Override
    public void setBooleanValue(boolean value) {

    }

    @Override
    public void setStringValue(String value) {
    }

    @Override
    public void setPlayersValue(List<Player> value) {
        setSetting(value);
    }
}
