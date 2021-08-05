package de.entjic.invasion.game.mobs;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import de.entjic.invasion.Invasion;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.EnumSet;

public class AttackNearPlayerCreatureAI implements Goal<Mob> {
    private final Mob mob;
    private int tick;

    public AttackNearPlayerCreatureAI(Mob mob) {
        this.mob = mob;
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
        attack();
    }

    @Override
    public void tick() {
        tick++;
        if (tick > 25) {
            attack();
            tick = 0;
        }
    }

    @Override
    public @NotNull
    GoalKey<Mob> getKey() {
        return GoalKey.of(Mob.class, new NamespacedKey(Invasion.getProvidingPlugin(Invasion.class), "AttackPlayersAI"));
    }

    @Override
    public @NotNull
    EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.TARGET);
    }

    private void attack() {
        Collection<Player> nearbyPlayers = mob.getLocation().getNearbyPlayers(2.5);
        if (nearbyPlayers.isEmpty()) {
            mob.setTarget(null);
            return;
        }
        Player target = random(nearbyPlayers);
        if (! mob.hasLineOfSight(target)) {
            return;
        }
        mob.attack(target);
        mob.swingMainHand();
        mob.getPathfinder().moveTo(target);
    }

    private <T> T random(Collection<T> collection) {
        int num = (int) (Math.random() * collection.size());
        for (T t : collection) if (-- num < 0) return t;
        throw new AssertionError();
    }
}
