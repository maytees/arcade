package net.matees.arcade.deathswap.settings

import net.matees.settings.AbstractBooleanSetting
import org.bukkit.Material

class MentionWho : AbstractBooleanSetting() {
    override var setting: Boolean? = true // Seconds

    override val name: String
        get() = "Mention Who"

    override val description: String
        get() = "Set whether to tell each player who they're swapping with before swap."


    override val menuItemMaterial: Material
        get() = Material.SPRUCE_SIGN

    override val menuItemSlot: Int
        get() = 12

    companion object {
        val instance: MentionWho = MentionWho()
    }
}
