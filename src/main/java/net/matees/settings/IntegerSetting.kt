package net.matees.settings

import org.bukkit.entity.Player

abstract class IntegerSetting : Setting<Int?>() {
    open val min = 1

    override fun setIntValue(value: Int) {
        if(value < min) return
        setting = value
    }

    override fun setBooleanValue(value: Boolean) {
    }

    override fun setStringValue(value: String?) {
    }

    override fun setPlayersValue(value: List<Player?>?) {
    }

}
