package net.matees.arcade.mobrush.listeners;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.matees.arcade.mobrush.MobRush;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        MobRush mobRush = MobRush.getInstance();
        int maxMobCount = (int) mobRush.getSetting("Max Mob Count").getSetting();
        boolean randomMobCount = (boolean) mobRush.getSetting("Random Mob Count").getSetting();
        boolean enableHostileMobs = (boolean) mobRush.getSetting("Enable Hostile Mobs").getSetting();
        boolean enablePeacefulMobs = (boolean) mobRush.getSetting("Enable Peaceful Mobs").getSetting();

        EntityType[] mobs = Arrays.stream(EntityType.values()).filter(entityType -> {
            if (entityType.getEntityClass() == null) {
                return false;
            }

            if (Monster.class.isAssignableFrom(entityType.getEntityClass())
                    || Enemy.class.isAssignableFrom(entityType.getEntityClass())) {
                return enableHostileMobs;
            }

            if (Creature.class.isAssignableFrom(entityType.getEntityClass())) {
                return enablePeacefulMobs;
            }

            return false;
        }).toArray(EntityType[]::new);

        if (!enableHostileMobs && !enablePeacefulMobs) {
            return;
        }

        EntityType mobType = mobs[new Random().nextInt(mobs.length)];

        if (randomMobCount) {
            int count = new Random().nextInt(maxMobCount + 1);
            for (int i = 0; i < count; i++) {
                event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), mobType);
            }

            return;
        }

        for (int i = 0; i < maxMobCount; i++) {
            event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), mobType);
        }
    }
}