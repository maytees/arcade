package net.matees.arcade.blockshuffle.listeners

import me.kodysimpson.simpapi.colors.ColorTranslator
import net.matees.arcade.blockshuffle.BlockShuffle
import net.matees.arcade.blockshuffle.BlockShuffle.Companion.foundBlock
import net.matees.arcade.blockshuffle.BlockShuffle.Companion.questMap
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteract : Listener {
     @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if(!BlockShuffle.Companion.instance.isCurrentMinigame) return

        for(quest in questMap)   {
            if (quest.key.first == event.player && quest.key.second == event.clickedBlock?.type) {
                foundBlock(event.player, event.clickedBlock?.type!!)
                event.player.sendMessage(
                    ColorTranslator.translateColorCodes(
                        "§aYou found your assigned block!"
                    )
                )
            }
        }
    }
}