package net.matees.arcade.mobrush.settings

import net.matees.settings.BooleanSetting
import org.bukkit.Material

class RandomMobCount private constructor() : BooleanSetting() {
    private var randomMobCount: Boolean = true

    override val name: String
        get() = "Random Mob Count"

    override val description: String
        get() = "Toggle random amount of mobs spawned"

    override var setting: Boolean?
        get() = this.randomMobCount
        set(setting) {
            if (setting != null) {
                this.randomMobCount = setting
            }
        }

    override val menuItemMaterial: Material
        get() = Material.REDSTONE_BLOCK

    override val menuItemSlot: Int
        get() = 11

    companion object {
        val instance: RandomMobCount = RandomMobCount()
    }
}
