package de.entjic.invasion.game.wave;

import de.entjic.invasion.game.SpawnAreaCalculator;
import de.entjic.invasion.game.mobs.CustomMob;
import de.entjic.invasion.game.mobs.MobSpawner;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Wave {
    private final List<EntityTypeAmountHash> entityAmount;
    private final Set<CustomMob> mobs;
    private final Location target;

    public Wave(List<EntityTypeAmountHash> entityAmount, Location target) {
        this.entityAmount = entityAmount;
        this.mobs = new HashSet<>();
        this.target = target;
    }

    public void spawn() {
        final SpawnAreaCalculator calculator = new SpawnAreaCalculator();
        final MobSpawner spawner = new MobSpawner(calculator.calcSpawnLocation(30, target, 10),
                target);
        for (EntityTypeAmountHash hash : entityAmount) {
            int amount = hash.amount;
            EntityType type = hash.type;
            spawn(spawner, amount, type);
        }
    }

    private void spawn(MobSpawner spawner, int amount, EntityType type) {
        for (int i = 0; i < amount; i++) {
            CustomMob mob = spawner.spawn(type);
            mobs.add(mob);
        }
    }
}
