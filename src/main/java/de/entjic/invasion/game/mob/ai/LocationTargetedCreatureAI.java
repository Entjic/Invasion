package de.entjic.invasion.game.mob.ai;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import de.entjic.invasion.Invasion;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public class LocationTargetedCreatureAI implements Goal<Mob> {
    private final Mob mob;
    private final Location target;
    private int tick;

    public LocationTargetedCreatureAI(Mob mob, Location target) {
        this.mob = mob;
        this.target = target;
        tick = 0;
    }

    @Override
    public boolean shouldActivate() {
        return true;
    }

    @Override
    public boolean shouldStayActive() {
        return shouldActivate();
    }

    @Override
    public void start() {
        mob.getPathfinder().moveTo(target);
    }

    @Override
    public void stop() {
        mob.getPathfinder().stopPathfinding();
    }

    @Override
    public void tick() {
        tick++;
        if (tick > 60 || (mob.getPathfinder().getCurrentPath() == null && ! mob.getLocation().getBlock().equals(target.getBlock()))) {
            start();
            tick = 0;
        }
    }

    @Override
    public @NotNull
    GoalKey<Mob> getKey() {
        return GoalKey.of(Mob.class, new NamespacedKey(Invasion.getProvidingPlugin(Invasion.class), "DestructiveCreatureAI"));
    }

    @Override
    public @NotNull
    EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.MOVE);
    }
}
