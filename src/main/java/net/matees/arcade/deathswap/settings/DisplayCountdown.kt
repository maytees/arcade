package net.matees.arcade.deathswap.settings

import net.matees.settings.BooleanSetting
import net.matees.settings.IntegerSetting
import org.bukkit.Material

class DisplayCountdown : BooleanSetting() {
    override var setting: Boolean? = true // Seconds

    override val name: String
        get() = "Display Countdown"

    override val description: String
        get() = "Set whether to display countdown till' swap."


    override val menuItemMaterial: Material
        get() = Material.EMERALD_BLOCK

    override val menuItemSlot: Int
        get() = 11

    companion object {
        val instance: DisplayCountdown = DisplayCountdown()
    }
}
