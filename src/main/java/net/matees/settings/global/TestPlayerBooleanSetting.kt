package net.matees.settings.global

import me.kodysimpson.simpapi.menu.MenuManager
import net.matees.settings.IGlobal
import net.matees.settings.AbstractPlayerBooleanSetting
import net.matees.settings.menus.CoolnessPlayerBooleanMenu
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.logging.Logger

class TestPlayerBooleanSetting private constructor() : AbstractPlayerBooleanSetting(), IGlobal {
    override var setting: HashMap<Player, Boolean>? = Bukkit.getOnlinePlayers()
        .associateWith { false } // Default value for each player (false).
        .toMap(HashMap())

    override fun openPlayerListMenu(player: Player) {
        MenuManager.openMenu(CoolnessPlayerBooleanMenu::class.java, player)
    }

    override val name: String
        get() = "Player Boolean Selection"

    override val description: String
        get() = "Choose Players?"

    override val menuItemMaterial: Material
        get() = Material.PLAYER_HEAD

    override val menuItemSlot: Int
        get() = 15

    override fun onChange() {
    }

    companion object {
        val instance: TestPlayerBooleanSetting = TestPlayerBooleanSetting()
    }
}