package de.entjic.invasion.listener;

import de.entjic.invasion.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class CancelDeathItemDropListener implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntityType().equals(EntityType.PLAYER)) {
            return;
        }
        event.getDrops().clear();
        event.getDrops().add(new ItemBuilder(Material.GOLD_NUGGET).toItemStack());
    }
}
