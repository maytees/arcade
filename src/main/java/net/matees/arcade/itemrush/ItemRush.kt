package net.matees.arcade.itemrush

import me.kodysimpson.simpapi.colors.ColorTranslator
import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.menu.Menu
import net.matees.Arcade
import net.matees.arcade.Minigame
import net.matees.arcade.MinigameType
import net.matees.arcade.itemrush.listeners.BlockBreak
import net.matees.arcade.itemrush.settings.MaxItemCount
import net.matees.arcade.itemrush.settings.RandomItemCount
import net.matees.settings.AbstractSetting
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

class ItemRush private constructor() : Minigame() {
    override val name: String
        get() = "Item Rush"

    override val minigameType: MinigameType?
        get() = MinigameType.ItemRush

    private val blockBreakListener = BlockBreak()

    override val listeners: List<Listener>
        get() = listOf(blockBreakListener)

    override val subCommands: List<Class<out SubCommand?>?>?
        get() = null

    override val commandName: String?
        get() = null

    override val commandDescription: String?
        get() = null

    override val commandUsage: String?
        get() = null

    override val settings: List<AbstractSetting<*>>
        get() = listOf(
            MaxItemCount.instance,
            RandomItemCount.instance
        )

    override fun doStartMinigame() {
        Bukkit.broadcastMessage("§aItem Rush has started!")
        registerListeners()
    }

    override fun onStopMinigame() {
        this.unregisterListeners()
    }

    private fun registerListeners() {
        listeners.forEach { listener ->
            Arcade.plugin?.server?.pluginManager
                ?.registerEvents(listener, Arcade.plugin!!)
        }
    }

    private fun unregisterListeners() {
        listeners.forEach { listener ->
            HandlerList.unregisterAll(listener)
        }
    }

    override val minigameMenuItem: ItemStack
        get() {
            val item = ItemStack(Material.DIAMOND, 1)
            val meta = item.itemMeta
            meta?.setDisplayName("§aItem Rush")
            meta?.lore = listOf(
                ColorTranslator.translateColorCodes("§7Break blocks to get items!"),
                ColorTranslator.translateColorCodes("§7Get lucky!"),
                ColorTranslator.translateColorCodes("&3Right Click to open settings")
            )
            item.itemMeta = meta
            return item
        }

    override val settingsMenu: Class<out Menu?>
        get() = ItemRushSettingsMenu::class.java

    companion object {
        val instance: ItemRush = ItemRush()
    }
}