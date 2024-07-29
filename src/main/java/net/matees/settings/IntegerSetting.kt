package net.matees.settings

import org.bukkit.entity.Player

abstract class IntegerSetting : Setting<Int?>() {
    override fun setIntValue(value: Int) {
        setting = value
    }

    override fun setBooleanValue(value: Boolean) {
    }

    override fun setStringValue(value: String?) {
    }

    override fun setPlayersValue(value: List<Player?>?) {
    }
}
