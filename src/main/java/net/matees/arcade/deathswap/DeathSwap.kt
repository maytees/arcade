package net.matees.arcade.deathswap

import me.kodysimpson.simpapi.colors.ColorTranslator
import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.menu.Menu
import net.matees.Arcade
import net.matees.arcade.Minigame
import net.matees.arcade.MinigameType
import net.matees.arcade.deathswap.settings.DisplayCountdown
import net.matees.arcade.deathswap.settings.MentionWho
import net.matees.arcade.deathswap.settings.TimeBetween
import net.matees.settings.*
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getLogger
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class DeathSwap private constructor() : Minigame() {
    private var mainTask: BukkitTask? = null;
    private var countdownTask: BukkitTask? = null;

    override val name: String
        get() = "Mob Rush"

    override val minigameType: MinigameType
        get() = MinigameType.MobRush

    override val listeners: List<Listener>
        get() = listOf<Listener>(
        )

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
            TimeBetween.instance,
            DisplayCountdown.instance,
            MentionWho.instance
        )

    override val settingsMenu: Class<out Menu?>
        get() = DeathSwapSettingsMenu::class.java

    override val minigameMenuItem: ItemStack
        get() {
            val item = ItemStack(Material.SKELETON_SKULL, 1)
            val meta = item.itemMeta
            meta.setDisplayName(ColorTranslator.translateColorCodes("&aDeath Swap"))
            meta.lore = listOf(
                ColorTranslator.translateColorCodes("§7Every minute you swap places with a random player!"),
                ColorTranslator.translateColorCodes("§7Don't die!"),
                ColorTranslator.translateColorCodes("&3Right Click to open settings")
            )

            item.setItemMeta(meta)
            return item
        }

    override fun doStartMinigame() {
        Bukkit.broadcastMessage("§aDeath swap has started!")
        for (listener in this.listeners) {
            Arcade.Companion.plugin?.let {
                Arcade.Companion.plugin?.server?.pluginManager
                    ?.registerEvents(listener, it)
            }
        }

        Arcade.plugin?.let {
            mainTask = object : BukkitRunnable() {
                override fun run() {
                    val players = Bukkit.getOnlinePlayers().toList()
                    val playerCount = players.size

                    if (playerCount < 2) {
                        // Not enough players to swap
                        getLogger().info("Not enough players to swap.")
                        return
                    }

                    if (playerCount % 2 == 1) {
                        // Odd number of players, choose two players and start the countdown
                        val player1 = players.random()
                        var player2 = players.random()

                        // Ensure player1 and player2 are different
                        while (player2 == player1) {
                            player2 = players.random()
                        }

                        startCountdown(player1, player2)
                    } else {
                        // Even number of players, pair players up and start the countdown for each pair
                        players.shuffled().chunked(2).forEach { pair ->
                            if (pair.size == 2) {
                                startCountdown(pair[0], pair[1])
                            }
                        }
                    }
                }
                // Subtract 20 to make time for the countdown (for initial delay)
            }.runTaskTimer(Arcade.Companion.plugin!!, (getSetting("Time Between")?.setting as Int * 20).toLong() - 10 * 20,
                (getSetting("Time Between")?.setting as Int * 20).toLong())
        }
    }

    override fun onStopMinigame() {
        mainTask?.cancel();
        countdownTask?.cancel()
    }

    private fun startCountdown(player1: Player, player2: Player) {
        Arcade.plugin?.let {
            countdownTask = object : BukkitRunnable() {
                var countdown = 10

                override fun run() {
                    if (countdown > 0) {
                        if(countdown == 10) {
                            if(getSetting("Mention Who")?.setting == true) {
                                player1.sendMessage(ColorTranslator.translateColorCodes("&cYou are swapping locations with &l" + player2.name))
                                player2.sendMessage(ColorTranslator.translateColorCodes("&cYou are swapping locations with &l" + player1.name))
                            }
                        }
                        if(getSetting("Display Countdown")?.setting == true) {
                            Bukkit.broadcastMessage(
                                ColorTranslator.translateColorCodes("&3&lRandomly swapping players in $countdown...")
                            )
                        }
                        countdown--

                    } else {
                        // Swap players when countdown reaches zero
                        swapPlayers(player1, player2)
                        cancel()
                    }
                }
            }.runTaskTimer(it, 0, 20)
        } // Run every second (20 ticks)
    }

    private fun swapPlayers(player1: Player, player2: Player) {
        val location1 = player1.location
        val location2 = player2.location

        // Teleport player1 to player2's location and vice versa
        player1.teleport(location2)
        player2.teleport(location1)

        getLogger().info("Swapped locations of ${player1.name} and ${player2.name}")
    }

    companion object {
        val instance: DeathSwap = DeathSwap()
    }
}
