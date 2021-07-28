package de.entjic.invasion.game.mobs;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.MobGoals;
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
    private final EntityType type;

    public CustomMob(EntityType type, Location spawnLocation, Location target) {
        this.entity = spawnLocation.getWorld().spawnEntity(spawnLocation, type, CreatureSpawnEvent.SpawnReason.CUSTOM);
        this.type = type;
        mob = (Mob) entity;
        this.target = target;
        addAI();
        init();
    }

    private void addAI() {
        MobGoals mobGoals = Bukkit.getMobGoals();
        mobGoals.getAllGoals(mob).forEach(e -> System.out.println(e.getKey().getNamespacedKey()));

        Goal<Mob> destructiveAI = new LocationTargetedCreatureAI(mob, target);
        mobGoals.addGoal(mob, 0, destructiveAI);

    }

    private void init() {
        entity.setCustomNameVisible(true);
        entity.setCustomName(ChatColor.AQUA + entity.getType().toString());
        setEquipment(mob);
    }

    private void setEquipment(Mob mob) {
        EntityEquipment equipment = mob.getEquipment();
        equipment.setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setLeatherArmorColor(Color.fromRGB(255, 0, 0)).toItemStack());
    }

    public EntityType getType() {
        return type;
    }

}
