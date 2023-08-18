package net.matees.arcade.mobrush.listeners;

import java.util.Random;

import org.bukkit.entity.EntityType;
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

        EntityType mobType = EntityType.values()[new Random().nextInt(EntityType.values().length)];

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