package net.matees.settings.menus

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.settings.menus.abstracts.AbstractPlayerBooleanListMenu
import org.bukkit.entity.Player

class CoolnessPlayerBooleanMenu(playerMenuUtility: PlayerMenuUtility?) : AbstractPlayerBooleanListMenu(playerMenuUtility) {
    override val isGlobal: Boolean
        get() = true

    override val settingName: String
        get() = "Player Boolean Selection"

    override fun getMenuName(): String {
        return "Coolness"
    }
}