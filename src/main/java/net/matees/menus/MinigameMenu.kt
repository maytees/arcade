package net.matees.menus

import co.aikar.timings.TimingsManager
import me.kodysimpson.simpapi.colors.ColorTranslator
import me.kodysimpson.simpapi.exceptions.MenuManagerException
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException
import me.kodysimpson.simpapi.menu.Menu
import me.kodysimpson.simpapi.menu.MenuManager
import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.Arcade
import net.matees.arcade.Minigame
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import io.papermc.paper.command.brigadier.argument.ArgumentTypes.player
import co.aikar.timings.TimingsManager.url



class MinigameMenu(playerMenuUtility: PlayerMenuUtility?) : Menu(playerMenuUtility) {
    override fun getMenuName(): String {
        return "Start Minigame"
    }

    override fun getSlots(): Int {
        return 54
    }

    override fun cancelAllClicks(): Boolean {
        return true
    }

    @Throws(MenuManagerNotSetupException::class, MenuManagerException::class)
    override fun handleMenu(inventoryClickEvent: InventoryClickEvent) {
        val plugin: Arcade? = Arcade.Companion.plugin
        val minigames = plugin?.minigames
        var clicked: Minigame? = null

        if (inventoryClickEvent.currentItem!!.type == Material.GHAST_TEAR) {
            MenuManager.openMenu(GlobalSettingsMenu::class.java, inventoryClickEvent.whoClicked as Player)
            inventory.close()
            return
        }

        if (inventoryClickEvent.currentItem!!.type == Material.PAPER) {
            inventory.close()
            val player = inventoryClickEvent.whoClicked as Player
            val url = "https://modrinth.com/plugin/arcade/changelog"

            val jsonMessage =
                "tellraw " + player.name + " {\"text\":\"" +ColorTranslator.translateColorCodes("§aClick me to view changelogs!") +  "\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"$url\"}}"
            Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                jsonMessage
            )
            return
        }

        // Stop current minigame
        if (inventoryClickEvent.currentItem!!.type == Material.REDSTONE_BLOCK) {
            if (plugin != null) {
                if (plugin.currentGame == null) {
                    inventoryClickEvent.whoClicked.sendMessage("§cThere is no current minigame!")
                    inventory.close()
                    return
                }
            }
            if (plugin != null) {
                plugin.currentGame!!.stopMinigame()
            }
            if (plugin != null) {
                plugin.currentGame = null
            }
            inventoryClickEvent.whoClicked.sendMessage("§cStopped current minigame!")
            inventory.close()
            return
        }

        // If right click, open settings menu
        if (inventoryClickEvent.isRightClick) {
            for (game in minigames!!) {
                if (game.minigameMenuItem.isSimilar(inventoryClickEvent.currentItem)) {
                    MenuManager.openMenu(game.settingsMenu, inventoryClickEvent.whoClicked as Player)
                    return
                }
            }
        }

        for (game in minigames!!) {
            if (plugin.currentGame != null) if (plugin.currentGame!!.minigameMenuItem.isSimilar(inventoryClickEvent.currentItem)) {
                inventoryClickEvent.whoClicked
                    .sendMessage(ColorTranslator.translateColorCodes("&cThis minigame is already running!"))
                inventory.close()
                return
            }

            if (game.minigameMenuItem.isSimilar(inventoryClickEvent.currentItem)) {
                clicked = game
            }
        }

        if (plugin.currentGame != null) {
            plugin.currentGame!!.stopMinigame()
            plugin.currentGame = null
        }

        plugin.currentGame = clicked
        clicked!!.startMinigame()
        inventory.close()

        inventory.close()
    }

    override fun setMenuItems() {
        val minigames: List<Minigame>? = Arcade.Companion.plugin?.minigames

        if (minigames != null) {
            for (i in minigames.indices) {
                if (i == 17 || i == 26 || i == 35 || i == 44 || i == 53 || i == 18 || i == 27 || i == 36 || i == 45) continue
                val minigame = minigames[i]
                inventory.setItem(i + 10, minigame.minigameMenuItem)
            }
        }

        val stopCurrent = ItemStack(Material.REDSTONE_BLOCK, 1)
        val meta = stopCurrent.itemMeta
        meta.setDisplayName("§cStop Current Minigame")
        stopCurrent.setItemMeta(meta)
        inventory.setItem(49, stopCurrent)

        val globalSettings = ItemStack(Material.GHAST_TEAR, 1)
        val settingsMeta = globalSettings.itemMeta
        settingsMeta.setDisplayName("Global Settings")
        globalSettings.setItemMeta(settingsMeta)
        inventory.setItem(48, globalSettings)

        val arcadeInfo = ItemStack(Material.PAPER, 1)
        val arcadeInfoMeta = arcadeInfo.itemMeta
        arcadeInfoMeta.setDisplayName("Arcade Info")
        arcadeInfoMeta.lore = listOf(
            ColorTranslator.translateColorCodes("§cAlpha-0.0.3"),
            ColorTranslator.translateColorCodes("§aClick to view changelog.")
        )
        arcadeInfo.setItemMeta(arcadeInfoMeta)
        inventory.setItem(45, arcadeInfo)
    }
}