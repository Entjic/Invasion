package de.entjic.invasion.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CancelNaturalSpawningListener implements Listener {
    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (! event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM)) {
            event.setCancelled(true);
        }
    }
}
