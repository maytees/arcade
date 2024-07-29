package net.matees.arcade.lavarise

import me.kodysimpson.simpapi.colors.ColorTranslator
import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.menu.Menu
import net.matees.Arcade
import net.matees.arcade.Minigame
import net.matees.arcade.MinigameType
import net.matees.arcade.lavarise.settings.OnlyAirBlock
import net.matees.settings.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.scheduler.BukkitRunnable

class LavaRise private constructor() : Minigame() {
    val playersAlive: MutableList<Player> = ArrayList()

    fun removePlayer(player: Player) {
        playersAlive.remove(player)
    }

    override val name: String
        get() = "Lava Rise"

    override val minigameType: MinigameType?
        get() = MinigameType.LavaRise

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
        get() = java.util.List.of<Setting<*>>(OnlyAirBlock.Companion.instance)

    override val settingsMenu: Class<out Menu?>
        get() = LavaRiseSettingsMenu::class.java

    override val minigameMenuItem: ItemStack
        get() {
            val item = ItemStack(Material.LAVA_BUCKET, 1)
            val meta = item.itemMeta
            meta.setDisplayName("§aLava Rise")
            meta.lore = java.util.List.of(
                ColorTranslator.translateColorCodes("§7Lava rises every minute"),
                ColorTranslator.translateColorCodes("§7Build up and don't die!"),
                ColorTranslator.translateColorCodes("&3Last to die wins.")
            )
            item.setItemMeta(meta)
            return item
        }

    override fun doStartMinigame() {
        Bukkit.broadcastMessage("§aLava Rise has started!")
        for (listener in this.listeners) {
            Arcade.Companion.plugin?.let {
                Arcade.Companion.plugin?.server?.pluginManager
                    ?.registerEvents(listener, it)
            }
        }

        this.startLavaRise()
    }

    private fun startLavaRise() {
        Arcade.Companion.plugin?.let { it ->
            object : BukkitRunnable() {
                override fun run() {
                    val onlyAir = settings[0].setting as Boolean
                    if (!instance.isCurrentMinigame || yCoord == 322) this.cancel()

                    val world: World? = Arcade.Companion.plugin?.server?.getWorld("world")
                    val size = world?.worldBorder?.size?.toInt()
                    val centerX = world?.worldBorder?.center?.x
                    val centerZ = world?.worldBorder?.center?.z

                    val halfSize = (size?.div(2))?.toDouble()

                    var x = halfSize?.let { centerX?.minus(it) }
                    if (centerX != null) {
                        if (x != null) {
                            while (x <= centerX + halfSize!!) {
                                var z = centerZ?.minus(halfSize)
                                if (centerZ != null) {
                                    if (z != null) {
                                        while (z <= centerZ + halfSize) {
                                            val block = world.getBlockAt(x.toInt(), yCoord, z.toInt())
                                            Arcade.Companion.plugin?.let { FixedMetadataValue(it, true) }?.let {
                                                block.setMetadata("from_arcade",
                                                    it
                                                )
                                            }

                                            if (onlyAir) {
                                                if (block.type == Material.AIR) {
                                                    block.type = Material.LAVA
                                                }
                                            } else {
                                                block.type = Material.LAVA
                                            }
                                            z++
                                        }
                                    }
                                }
                                x++
                            }
                        }
                    }

                    yCoord++
                    Bukkit.broadcastMessage("§cRaised lava level to y=" + yCoord)
                }
            }.runTaskTimer(it, 0L, 20)
        }
    }

    override fun onStopMinigame() {
        object : BukkitRunnable() {
            override fun run() {
                if (yCoord == -64) this.cancel()

                val world: World? = Arcade.Companion.plugin?.server?.getWorld("world")
                val size = world?.worldBorder?.size?.toInt()
                val centerX = world?.worldBorder?.center?.x
                val centerZ = world?.worldBorder?.center?.z

                val halfSize = (size!! / 2).toDouble()

                var x = centerX!! - halfSize
                while (x <= centerX + halfSize) {
                    var z = centerZ!! - halfSize
                    while (z <= centerZ + halfSize) {
                        val block = world.getBlockAt(x.toInt(), yCoord, z.toInt())
                        val values = block.getMetadata("from_arcade")
                        var fromArcade = false
                        for (value in values) {
                            if (value.asBoolean()) fromArcade = true
                        }

                        if (block.type == Material.LAVA && fromArcade) {
                            block.type = Material.AIR
                        }
                        z++
                    }
                    x++
                }

                yCoord--
                Bukkit.broadcastMessage("§cCurrent y = " + yCoord)
            }
        }.runTaskTimer(Arcade.Companion.plugin!!, 0L, 10)
    }

    companion object {
        val instance: LavaRise = LavaRise()
        private var yCoord = -64
    }
}
