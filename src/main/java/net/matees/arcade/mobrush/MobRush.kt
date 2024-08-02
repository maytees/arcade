package net.matees.arcade.mobrush

import me.kodysimpson.simpapi.colors.ColorTranslator
import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.menu.Menu
import net.matees.Arcade
import net.matees.arcade.Minigame
import net.matees.arcade.MinigameType
import net.matees.arcade.mobrush.listeners.BlockBreak
import net.matees.arcade.mobrush.settings.EnableHostileMobs
import net.matees.arcade.mobrush.settings.EnablePeacefulMobs
import net.matees.arcade.mobrush.settings.MaxMobCount
import net.matees.arcade.mobrush.settings.RandomMobCount
import net.matees.settings.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

class MobRush private constructor() : Minigame() {
    override val name: String
        get() = "Mob Rush"

    override val minigameType: MinigameType?
        get() = MinigameType.MobRush

    private val blockBreakListener = BlockBreak()

    override val listeners: List<Listener>
        get() = listOf<Listener>(blockBreakListener)

    override val subCommands: List<Class<out SubCommand?>?>?
        get() = null

    override val commandName: String?
        get() = null

    override val commandDescription: String?
        get() = null

    override val commandUsage: String?
        get() = null

    override val settings: List<Setting<*>>
        get() = listOf<Setting<*>>(
            MaxMobCount.instance,
            RandomMobCount.instance,
            EnableHostileMobs.instance,
            EnablePeacefulMobs.instance
        )

    override val settingsMenu: Class<out Menu?>?
        get() = MobRushSettingsMenu::class.java

    override val minigameMenuItem: ItemStack
        get() {
            val item = ItemStack(Material.CREEPER_HEAD, 1)
            val meta = item.itemMeta
            meta.setDisplayName(ColorTranslator.translateColorCodes("&aMob Rush"))
            meta.lore = listOf(
                ColorTranslator.translateColorCodes("§7Break blocks to spawn random mobs!"),
                ColorTranslator.translateColorCodes("§7Get lucky!"),
                ColorTranslator.translateColorCodes("&3Right Click to open settings")
            )

            item.setItemMeta(meta)
            return item
        }

    override fun doStartMinigame() {
        Bukkit.broadcastMessage("§aMob Rush has started!")
        for (listener in this.listeners) {
            Arcade.Companion.plugin?.let {
                Arcade.Companion.plugin?.server?.pluginManager
                    ?.registerEvents(listener, it)
            }
        }
    }

    override fun onStopMinigame() {
        unregisterListeners()
    }

    private fun unregisterListeners() {
        listeners.forEach { listener ->
            HandlerList.unregisterAll(listener)
        }
    }

    companion object {
        val instance: MobRush = MobRush()
    }
}
