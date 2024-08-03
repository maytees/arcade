package net.matees.arcade.mobrush.settings

import net.matees.settings.AbstractBooleanSetting
import org.bukkit.Material

class EnableHostileMobs private constructor() : AbstractBooleanSetting() {
    override var setting: Boolean? = true

    override val name: String
        get() = "Enable Hostile Mobs"

    override val description: String
        get() = "Allows spawning of hostile mobs"

    override val menuItemMaterial: Material
        get() = Material.CREEPER_SPAWN_EGG

    override val menuItemSlot: Int
        get() = 13

    companion object {
        val instance: EnableHostileMobs = EnableHostileMobs()
    }
}
