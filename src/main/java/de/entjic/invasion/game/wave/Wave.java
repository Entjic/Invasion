package de.entjic.invasion.game.wave;

import org.bukkit.Location;
import org.bukkit.World;

public class Wave {
    World world;
    Location location;

    public Wave(Location location) {
        this.world = location.getWorld();
        this.location = location;
    }

    public void spawn() {
/*        CustomZombie customZombie = new CustomZombie(location);
        WorldServer world = ((CraftWorld) this.world).getHandle();
        customZombie.setGoalTarget()
        world.addEntity(customZombie);*/
    }
}
