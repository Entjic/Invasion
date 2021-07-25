package de.entjic.invasion.game.mobs;

import net.minecraft.server.v1_15_R1.EntityCreature;
import net.minecraft.server.v1_15_R1.PathfinderGoal;
import org.bukkit.Location;

import java.util.EnumSet;

public class DestructivePathFinderGoal extends PathfinderGoal {

    private final EntityCreature creature;
    private final Location target;

    public DestructivePathFinderGoal(EntityCreature creature, Location target) {
        this.creature = creature;
        this.target = target;
        this.a(EnumSet.of(Type.MOVE));
    }

    // FIXME: 25.07.2021 mobs seem to sometimes stop walking after dropping one block down

    // TODO: 25.07.2021 rework ai -> destructing blocks, placing blocks to get over gaps, ladders, making zombies attack

    @Override
    public boolean a() {
        if (!creature.onGround){
        //    System.out.println("false");
            return false;
        }
        // System.out.println("true");
        return true;
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public void c() {
       // System.out.println("Location " + creature.locX() + ", " + creature.locY() + ", " + creature.locZ() + " with target: " + target);
        this.creature.getNavigation().a(target.getX(), target.getY(), target.getZ(), 1f);
    }

    @Override
    public void d() {
        super.d();
    }
}
