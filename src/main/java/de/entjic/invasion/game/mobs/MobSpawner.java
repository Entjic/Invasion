package de.entjic.invasion.game.mobs;

import de.entjic.invasion.game.SpawnAreaCalculator;
import net.minecraft.server.v1_15_R1.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawner {
    private final Location spawnArea, target;
    private final SpawnAreaCalculator spawnAreaCalculator;

    public MobSpawner(Location spawnArea, Location target) {
        this.spawnArea = spawnArea;
        this.target = target;
        this.spawnAreaCalculator = new SpawnAreaCalculator();
    }

    public void spawn(int amount) {
        for (int i = 0; i < amount; i++) {
            spawn();
        }
    }

    private void spawn() {
        CustomZombie customZombie = new CustomZombie(spawnAreaCalculator.calcSpawnLocation(3, spawnArea), target);
        WorldServer world = ((CraftWorld) spawnArea.getWorld()).getHandle();
        world.addEntity(customZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }
}
