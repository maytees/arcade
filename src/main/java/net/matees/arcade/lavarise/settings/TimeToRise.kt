package net.matees.arcade.lavarise.settings

import net.matees.settings.IntegerSetting
import org.bukkit.Material

class TimeToRise private constructor() : IntegerSetting() {
    override var setting: Int? = 5 // SEconds

    override val name: String
        get() = "Time To Rise"

    override val description: String
        get() = "Set how often lava rises. Can't change during minigame."

    override val menuItemMaterial: Material
        get() = Material.RABBIT_FOOT

    override val menuItemSlot: Int
        get() = 15

    companion object {
        val instance: TimeToRise = TimeToRise()
    }
}
