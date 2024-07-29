package net.matees.arcade.manhunt

import me.kodysimpson.simpapi.colors.ColorTranslator
import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.menu.Menu
import net.matees.Arcade
import net.matees.arcade.Minigame
import net.matees.arcade.MinigameType
import net.matees.settings.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

class Manhunt private constructor() : Minigame() {
    override val name: String
        get() = "Manhunt"

    override val minigameType: MinigameType
        get() = MinigameType.Manhunt

    override val listeners: List<Listener>
        get() = listOf()

    override val subCommands: List<Class<out SubCommand?>?>?
        get() = null

    override val commandName: String?
        get() = null

    override val commandDescription: String?
        get() = null

    override val commandUsage: String?
        get() = null

    override val settings: List<Setting<*>>
        get() = listOf()

    override val settingsMenu: Class<out Menu?>
        get() = ManhuntSettingsMenu::class.java

    override val minigameMenuItem: ItemStack
        get() {
            val item = ItemStack(Material.NETHERITE_SWORD, 1)
            val meta = item.itemMeta
            meta.setDisplayName(ColorTranslator.translateColorCodes("&aManhunt"))
            meta.lore = java.util.List.of(
                ColorTranslator.translateColorCodes("&7Hunt down the runner(s)!"),
                ColorTranslator.translateColorCodes(
                    "&7The runner(s) must kill the ender dragon while hunters attempt to kill the runner(s)"
                ),
                ColorTranslator.translateColorCodes("&3 Right Click to open settings")
            )

            item.setItemMeta(meta)
            return item
        }

    override fun doStartMinigame() {
        Bukkit.broadcastMessage("&aManhunt has started!")
        for (listener in this.listeners) {
            Arcade.Companion.plugin?.let {
                Arcade.Companion.plugin?.server?.pluginManager
                    ?.registerEvents(listener, it)
            }
        }
    }

    override fun onStopMinigame() {
    }

    companion object {
        val instance: Manhunt = Manhunt()
    }
}
