package net.matees.arcade.manhunt.settings

import net.matees.settings.AbstractPlayerBooleanSetting
import org.bukkit.Material
import org.bukkit.entity.Player

class Hunters private constructor() : AbstractPlayerBooleanSetting() {
    private var selectedPlayers: HashMap<Player, Boolean>? = null
    private val players: List<Player>? = null

    override fun openPlayerListMenu(player: Player) {
        // TODO: Implement Hunter Selection Menu
    }

    override val name: String
        get() = "Hunters"

    override val description: String
        get() = "Select the hunters"

    override var setting: HashMap<Player, Boolean>?
        get() = this.selectedPlayers
        set(setting) {
            this.selectedPlayers = setting as HashMap<Player, Boolean>
        }

    override val menuItemMaterial: Material
        get() = Material.IRON_SWORD

    override val menuItemSlot: Int
        get() = 12

    companion object {
        val instance: Hunters = Hunters()
    }
}
