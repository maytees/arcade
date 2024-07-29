package net.matees.settings

import org.bukkit.entity.Player

abstract class PlayersSelectionSetting : Setting<List<Player?>?>() {
    override fun setIntValue(value: Int) {
    }

    override fun setBooleanValue(value: Boolean) {
    }

    override fun setStringValue(value: String?) {
    }

    override fun setPlayersValue(value: List<Player?>?) {
        setting = value
    }
}
