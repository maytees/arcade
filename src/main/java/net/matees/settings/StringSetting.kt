package net.matees.settings

import org.bukkit.entity.Player

abstract class StringSetting : Setting<String?>() {
    override fun setIntValue(value: Int) {
    }

    override fun setBooleanValue(value: Boolean) {
    }

    override fun setStringValue(value: String?) {
        setting = value
    }

    override fun setPlayersValue(value: List<Player?>?) {
    }
}
