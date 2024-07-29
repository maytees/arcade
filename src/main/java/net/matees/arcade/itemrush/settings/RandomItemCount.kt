package net.matees.arcade.itemrush.settings

import net.matees.settings.BooleanSetting
import org.bukkit.Material

class RandomItemCount private constructor() : BooleanSetting() {
    var randomItemCount: Boolean = true

    override val name: String
        get() = "Random Item Count"

    override val description: String
        get() = "Toggle random amount of items dropped"

    override var setting: Boolean?
        get() = this.randomItemCount
        set(setting) {
            if (setting != null) {
                this.randomItemCount = setting
            }
        }

    override val menuItemMaterial: Material
        get() = Material.REDSTONE_BLOCK

    override val menuItemSlot: Int
        get() = 11

    companion object {
        val instance: RandomItemCount = RandomItemCount()
    }
}
