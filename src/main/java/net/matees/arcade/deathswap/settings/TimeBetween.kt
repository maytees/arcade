package net.matees.arcade.deathswap.settings

import net.matees.settings.AbstractIntegerSetting
import org.bukkit.Material

class TimeBetween : AbstractIntegerSetting() {
    override var setting: Int? = 60 // Seconds

    override val min: Int
        get() = 1

    override val name: String
        get() = "Time Between"

    override val description: String
        get() = "Set how often a swap occurs. Changes after current swap is over."


    override val menuItemMaterial: Material
        get() = Material.REPEATER

    override val menuItemSlot: Int
        get() = 10

    companion object {
        val instance: TimeBetween = TimeBetween()
    }
}
