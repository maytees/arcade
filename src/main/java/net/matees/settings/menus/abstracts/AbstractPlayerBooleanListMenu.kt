package net.matees.settings.menus.abstracts

import me.kodysimpson.simpapi.colors.ColorTranslator
import me.kodysimpson.simpapi.exceptions.MenuManagerException
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException
import me.kodysimpson.simpapi.heads.SkullCreator
import me.kodysimpson.simpapi.menu.PaginatedMenu
import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.Arcade
import net.matees.arcade.Minigame
import net.matees.settings.global.GlobalSettings
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*
import kotlin.collections.HashMap

abstract class AbstractPlayerBooleanListMenu(playerMenuUtility: PlayerMenuUtility?) : PaginatedMenu(playerMenuUtility) {

    abstract val settingName: String
    open val isGlobal: Boolean = true
    open val minigame: Minigame? = null

    // These can be used to declare alias' for true/false
    // For example, in Hunter selection menu, true's value
    // Could be displays as "Hunter", instead of "True"
    // TODO: I'd prefer to replace this with a future SelectSetting
    open val trueAlias: String = "True"
    open val falseAlias: String = "False"

    fun getCurrentSetting(): HashMap<Player, Boolean> {
        return if (isGlobal) GlobalSettings.Companion.instance
            .getSetting(settingName)?.setting as HashMap<Player, Boolean> else {
            minigame?.getSetting(settingName)?.setting as HashMap<Player, Boolean>
        }
    }

    override fun getSlots(): Int {
        return 54
    }

    override fun cancelAllClicks(): Boolean {
        return true
    }

    @Throws(MenuManagerNotSetupException::class, MenuManagerException::class)
    override fun handleMenu(event: InventoryClickEvent) {
        if(event.currentItem?.type != Material.PLAYER_HEAD) {
            event.isCancelled = true
        }

        val itemMeta = event.currentItem?.itemMeta
        val uuidStr = itemMeta?.persistentDataContainer?.get(
            NamespacedKey(
                Arcade.Companion.plugin!!,
                "player_uuid"),
            PersistentDataType.STRING)
        val selectedPlayer = uuidStr?.let { Bukkit.getPlayer(UUID.fromString(it)) }

        if (selectedPlayer != null) {
            Bukkit.getLogger().info("Is not null")
            val current = getCurrentSetting()[selectedPlayer]
            if (isGlobal) {
                (GlobalSettings.Companion.instance
                    .getSetting(settingName)?.setting
                        as HashMap<Player, Boolean>).set(selectedPlayer, !current!!)
                Bukkit.getLogger().info("${selectedPlayer.toString()} is selected, current: ${current}")
            } else {
                (minigame?.getSetting(settingName)?.setting
                        as HashMap<Player, Boolean>).set(selectedPlayer, !current!!)
            }
        }
        Bukkit.getLogger().info("After check, selected name: ${selectedPlayer?.name}, clicked item: ${event.currentItem!!.itemMeta.displayName}")
        inventory.clear()
        this.setMenuItems()
    }

    override fun dataToItems(): MutableList<ItemStack> {
        if (!isGlobal && minigame == null) {
            throw Error("Must be either a global setting, or a minigame setting!")
        }

        return (Bukkit.getOnlinePlayers().map {
            val itemStack = SkullCreator.itemFromUuid(it.uniqueId)
            val setting: HashMap<Player, Boolean> = getCurrentSetting()

            Bukkit.getLogger().info("${setting.toString()} is setting")
            val currentSetting = setting[it]
            val settingAsStr = if (currentSetting == true) trueAlias else falseAlias
            val itemMeta = itemStack.itemMeta
            itemMeta.setItemName(
                ColorTranslator.translateColorCodes(
                    // Change based on value?
                    "§a${it.name}"
                )
            )
            itemMeta.lore = listOf(
                ColorTranslator.translateColorCodes(
                    "${if (currentSetting == true) "§a" else "§c"}Current: $settingAsStr"
                ),
                ColorTranslator.translateColorCodes(
                    "&3Click to toggle"
                ),
            )

            itemMeta.persistentDataContainer.set(
                NamespacedKey(Arcade.Companion.plugin!!, "player_uuid"),
                PersistentDataType.STRING,
                it.uniqueId.toString()
            )

            itemStack.setItemMeta(itemMeta)
            itemStack
        }).toMutableList()
    }

    override fun getCustomMenuBorderItems(): HashMap<Int, ItemStack>? {
        return null
    }
}