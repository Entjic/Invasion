package de.entjic.invasion.game.mob;

import de.entjic.invasion.file.ConfigContainer;
import de.entjic.invasion.util.SpawnAreaCalculator;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class MobSpawner {
    private final Location spawnArea, target;
    private final SpawnAreaCalculator spawnAreaCalculator;

    public MobSpawner(Location spawnArea, Location target) {
        this.spawnArea = spawnArea;
        this.target = target;
        this.spawnAreaCalculator = new SpawnAreaCalculator();
    }

    public CustomMob spawn(EntityType type) {
        return new CustomMob(type, spawnAreaCalculator.calcSpawnLocation(
                ConfigContainer.getInstance().getFile("config").getInt("mobrange"), spawnArea), target);
    }

}
