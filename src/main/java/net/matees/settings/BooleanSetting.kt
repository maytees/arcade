package net.matees.settings

import org.bukkit.entity.Player

abstract class BooleanSetting : Setting<Boolean?>() {
    override fun setIntValue(value: Int) {
    }

    override fun setBooleanValue(value: Boolean) {
        setting = value
    }

    override fun setStringValue(value: String?) {
    }

    override fun setPlayersValue(value: List<Player?>?) {
    }
}
