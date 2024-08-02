package net.matees.arcade.mobrush.listeners

import net.matees.arcade.mobrush.MobRush
import org.bukkit.Bukkit
import org.bukkit.entity.Creature
import org.bukkit.entity.Enemy
import org.bukkit.entity.EntityType
import org.bukkit.entity.Monster
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import java.util.*

class BlockBreak : Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (!MobRush.Companion.instance.isCurrentMinigame) return

        val mobRush: MobRush = MobRush.Companion.instance
        val maxMobCount = mobRush.getSetting("Max Mob Count")?.setting as Int
        val randomMobCount = mobRush.getSetting("Random Mob Count")!!.setting as Boolean
        val enableHostileMobs = mobRush.getSetting("Enable Hostile Mobs")!!.setting as Boolean
        val enablePeacefulMobs = mobRush.getSetting("Enable Peaceful Mobs")!!.setting as Boolean

        val mobs = Arrays.stream<EntityType>(EntityType.entries.toTypedArray()).filter { entityType: EntityType ->
            if (entityType.entityClass == null) {
                return@filter false
            }
            if (!(!entityType.entityClass?.let { Monster::class.java.isAssignableFrom(it) }!! && !entityType.entityClass?.let {
                    Enemy::class.java.isAssignableFrom(
                        it
                    )
                }!!)
            ) {
                return@filter enableHostileMobs
            }

            if (entityType.entityClass?.let { Creature::class.java.isAssignableFrom(it) }!!) {
                return@filter enablePeacefulMobs
            }
            false
        }.toArray()

        if (!enableHostileMobs && !enablePeacefulMobs) {
            return
        }

        val mobType = mobs[Random().nextInt(mobs.size)]

        if (randomMobCount) {
            val count = kotlin.random.Random.nextInt(1, maxMobCount + 1)
            for (i in 0 until count) {
                event.block.world.spawnEntity(event.block.location.clone().add(0.0,2.0,0.0), mobType as EntityType)
            }

            return
        }

        for (i in 0 until maxMobCount) {
            event.block.world.spawnEntity(event.block.location, mobType as EntityType)
        }
    }
}