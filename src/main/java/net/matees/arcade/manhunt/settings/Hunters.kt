package net.matees.arcade.manhunt.settings

import net.matees.settings.PlayersSelectionSetting
import org.bukkit.Material
import org.bukkit.entity.Player

class Hunters private constructor() : PlayersSelectionSetting() {
    private var selectedPlayers: List<Player>? = null
    private val unselectedPlayers: List<Player>? = null
    private val players: List<Player>? = null

    override val name: String
        get() = "Hunters"

    override val description: String
        get() = "Select the hunters"

    override var setting: List<Player?>?
        get() = this.selectedPlayers
        set(setting) {
            this.selectedPlayers = setting as List<Player>?
        }

    override val menuItemMaterial: Material
        get() = Material.IRON_SWORD

    override val menuItemSlot: Int
        get() = 12

    companion object {
        val instance: Hunters = Hunters()
    }
}
