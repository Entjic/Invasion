package de.entjic.invasion.game.wave;

import de.entjic.invasion.game.GameObject;
import de.entjic.invasion.game.SpawnAreaCalculator;
import de.entjic.invasion.game.mobs.MobSpawner;
import org.bukkit.Location;

public class WaveSpawner implements GameObject {
    private final Location target;

    public WaveSpawner(Location target) {
        this.target = target;
    }


    @Override
    public void update(int gameTick) {
        if (gameTick % 20 == 0 || gameTick == 1) {
            spawn();
        }
    }

    @Override
    public void render(int gameTick) {

    }

    private void spawnWave(int member, WaveStrength strength) {

    }

    private void spawn() {
        MobSpawner spawner = new MobSpawner(new SpawnAreaCalculator().calcSpawnLocation(10, target, 10), target);
        spawner.spawn(1);
    }

}
