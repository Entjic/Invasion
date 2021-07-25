package de.entjic.invasion.game.mobs;

import net.minecraft.server.v1_15_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CustomZombie extends EntityZombie {
    private final Location location, targetLocation;

    public CustomZombie(Location location, Location targetLocation) {
        super(EntityTypes.ZOMBIE, ((CraftWorld) Objects.requireNonNull(location.getWorld())).getHandle());
        this.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
        this.setCustomName(new ChatComponentText(ChatColor.AQUA + "Zombie"));
        this.setCustomNameVisible(true);
        this.location = location;
        this.targetLocation = targetLocation;
        lateInit();
    }

    private void lateInit(){
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new DestructivePathFinderGoal(this, targetLocation));
        this.goalSelector.a(2, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        //this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.5f, true));
        //this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
    }

    @Override
    protected void initPathfinder() {
        // super.initPathfinder(); // This will apply all default pathfinders to the pig

        //this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, false));
        // this.setNoGravity(true);
    }



    @Override
    public void setOnFire(int i) {
    }
}
