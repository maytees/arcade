package net.matees.arcade.lavarise.settings

import net.matees.settings.AbstractIntegerSetting
import org.bukkit.Material

class TimeToRise private constructor() : AbstractIntegerSetting() {
    override var setting: Int? = 5 // Seconds

    override val name: String
        get() = "Time To Rise"

    override val description: String
        get() = "Set how often lava rises. Can't change during minigame."

    override val menuItemMaterial: Material
        get() = Material.REPEATER

    override val menuItemSlot: Int
        get() = 15

    companion object {
        val instance: TimeToRise = TimeToRise()
    }
}
