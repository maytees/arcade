package net.matees.arcade.deathswap.settings

import net.matees.settings.IntegerSetting
import org.bukkit.Material

class TimeBetween : IntegerSetting() {
    override var setting: Int? = 60 // Seconds

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