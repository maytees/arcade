package net.matees.arcade.mobrush.settings

import net.matees.settings.AbstractBooleanSetting
import org.bukkit.Material

class EnablePeacefulMobs private constructor() : AbstractBooleanSetting() {
    override var setting: Boolean? = true

    override val name: String
        get() = "Enable Peaceful Mobs"

    override val description: String
        get() = "Allows spawning of peaceful mobs"

    override val menuItemMaterial: Material
        get() = Material.SHEEP_SPAWN_EGG

    override val menuItemSlot: Int
        get() = 14

    companion object {
        val instance: EnablePeacefulMobs = EnablePeacefulMobs()
    }
}
