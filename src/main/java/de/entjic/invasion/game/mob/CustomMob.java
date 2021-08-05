package de.entjic.invasion.game.mob;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.MobGoals;
import de.entjic.invasion.game.mob.ai.AttackNearPlayerCreatureAI;
import de.entjic.invasion.game.mob.ai.BlockBreakCreatureAI;
import de.entjic.invasion.game.mob.ai.LocationTargetedCreatureAI;
import de.entjic.invasion.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;

public class CustomMob {
    private final Entity entity;
    private final Mob mob;
    private final Location target;


    public CustomMob(EntityType type, Location spawnLocation, Location target) {
        this.entity = spawnLocation.getWorld().spawnEntity(spawnLocation, type, CreatureSpawnEvent.SpawnReason.CUSTOM);
        mob = (Mob) entity;
        this.target = target;
        addAI();
        init();
    }

    private void addAI() {
        MobGoals mobGoals = Bukkit.getMobGoals();
        Goal<Mob> digAI = new BlockBreakCreatureAI(mob, target.getBlock());
        mobGoals.addGoal(mob, 2, digAI);
        Goal<Mob> targetedCreatureAI = new LocationTargetedCreatureAI(mob, target);
        mobGoals.addGoal(mob, 1, targetedCreatureAI);
        Goal<Mob> attackAI = new AttackNearPlayerCreatureAI(mob);
        mobGoals.addGoal(mob, 0, attackAI);
    }

    private void init() {
        entity.setCustomNameVisible(true);
        entity.setCustomName(ChatColor.AQUA + entity.getType().toString());
        setEquipment(mob);
        mob.setRemoveWhenFarAway(false);
        mob.setCanPickupItems(false);
        mob.setLootTable(null);
        mob.getPathfinder().setCanFloat(true);
        mob.getPathfinder().setCanPassDoors(true);
        mob.getPathfinder().setCanOpenDoors(true);
    }

    private void setEquipment(Mob mob) {
        EntityEquipment equipment = mob.getEquipment();
        equipment.setHelmet(new ItemBuilder(Material.LEATHER_HELMET)
                .setLeatherArmorColor(Color.fromRGB(255, 0, 0)).setInfinityDurability().toItemStack());
    }

}
