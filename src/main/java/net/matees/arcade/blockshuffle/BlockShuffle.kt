package net.matees.arcade.blockshuffle

import me.kodysimpson.simpapi.colors.ColorTranslator
import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.menu.Menu
import net.matees.Arcade
import net.matees.arcade.Minigame
import net.matees.arcade.MinigameType
import net.matees.arcade.blockshuffle.listeners.PlayerInteract
import net.matees.settings.AbstractSetting
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class BlockShuffle private constructor() : Minigame() {
    private var task: BukkitTask? = null
    private val gameDuration = 5 * 60 * 20 // 5 minutes in ticks
    private var elapsedTime = 0
    private var checkEvery = 10L // Ticks

    override val name: String
        get() = "Block Shuffle"

    override val minigameType: MinigameType?
        get() = MinigameType.BlockShuffle

    private val playerInteractListener = PlayerInteract()

    override val listeners: List<Listener>
        get() = listOf(playerInteractListener)

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
        )

    override fun doStartMinigame() {
        Bukkit.broadcastMessage("§aBlock Shuffle has started!")
        registerListeners()

        startTimer()
    }

    fun startTimer() {
        // Cancel existing task if it's running
        task?.cancel()

        if (!isCurrentMinigame) {
            stopMinigame()
            return
        }

        elapsedTime = 0
        assignBlocks()

        task = object : BukkitRunnable() {
            override fun run() {
                elapsedTime += checkEvery.toInt() // 15 seconds in ticks

                val playerMap = questMap.mapKeys { it.key.first }

                if (playerMap.all { it.value }) {
                    assignBlocks()
                    elapsedTime = 0 // Reset timer
                } else if (elapsedTime >= gameDuration) {
                    // Game over
                    Bukkit.getServer().broadcastMessage(
                        ColorTranslator.translateColorCodes("§cGame Over! Failed players:")
                    )
                    playerMap.filterNot { it.value }.forEach { (player, _) ->
                        Bukkit.getServer().broadcastMessage(
                            ColorTranslator.translateColorCodes("§c${player.name}")
                        )
                    }
                    stopMinigame()
                    return
                } else {
                    // Continue the game
                }
            }
        }.runTaskTimer(Arcade.plugin!!, 0L, checkEvery) // Run every 15 seconds
    }

    fun assignBlocks() {
        questMap.clear()

        for(player in Bukkit.getOnlinePlayers()) {
            val randomBlock = Material.entries.filter { it.isBlock }.random()
            questMap[Pair(player, randomBlock)] = false
            player.sendMessage(
                ColorTranslator.translateColorCodes(
                    "§bYou must acquire a §a${randomBlock.name}"
                )
            )
        }
    }

    override fun onStopMinigame() {
        this.unregisterListeners()
        task?.cancel()
        task = null
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
            val item = ItemStack(Material.MAGMA_BLOCK, 1)
            val meta = item.itemMeta
            meta?.setDisplayName("§aBlock Shuffle")
            meta?.lore = listOf(
                ColorTranslator.translateColorCodes("§Each player is tasked with standing on a certain block"),
                ColorTranslator.translateColorCodes("§7Stand on the block before the 5 minute timer!"),
                ColorTranslator.translateColorCodes("&3Right Click to open settings")
            )
            item.itemMeta = meta
            return item
        }

    override val settingsMenu: Class<out Menu?>
        get() = BlockShuffleSettingsMenu::class.java

    companion object {
        val instance: BlockShuffle = BlockShuffle()
        // Map of players, their item, and if the block has been stood on.
        var questMap: MutableMap<Pair<Player, Material>, Boolean> = mutableMapOf()
        fun foundBlock(player: Player, material: Material) {
            val key = Pair(player, material)
            questMap[key] = true
        }
    }
}