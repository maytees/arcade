package net.matees.arcade.deathswap.listeners

import net.matees.Arcade
import net.matees.arcade.deathswap.DeathSwap
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType

class CancelSwapListener: Listener {
    @EventHandler
    fun playerInteractEvent(event: PlayerInteractEvent) {
        if(!DeathSwap.instance.isCurrentMinigame) return

        if(event.item?.persistentDataContainer?.get(
            NamespacedKey(
                Arcade.Companion.plugin!!,
                "cancel_swap"
            ),
            PersistentDataType.STRING
        ) != null) {
            event.player.sendMessage("Right clicked cancel_swap")
        }
    }
}