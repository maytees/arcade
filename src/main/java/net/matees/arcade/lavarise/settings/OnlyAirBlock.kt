package net.matees.arcade.lavarise.settings

import net.matees.settings.BooleanSetting
import org.bukkit.Material

class OnlyAirBlock private constructor() : BooleanSetting() {
    override var setting: Boolean? = true

    override val name: String
        get() = "Only Air Block"

    override val description: String
        get() = "Determines whether only air blocks should be replaced with lava"

    override val menuItemMaterial: Material
        get() = Material.BARRIER

    override val menuItemSlot: Int
        get() = 10

    companion object {
        val instance: OnlyAirBlock = OnlyAirBlock()
    }
}
