package de.entjic.invasion.game;

import org.bukkit.Location;

public class SpawnAreaCalculator {

    // FIXME: 26.07.2021 seems to spawn in just 2 or 3 places

    public Location calcSpawnLocation(int range, Location target) {
        int x = calcNearRandom(target.getBlockX(), range);
        int z = calcNearRandom(target.getBlockZ(), range);
        int y = target.getWorld().getHighestBlockYAt(x, z);
        Location location = new Location(target.getWorld(), x, y + 1, z);
        // System.out.println(location.toString());
        return location;
    }

    public Location calcSpawnLocation(int range, Location target, int min) {
        int x = calcNearRandom(target.getBlockX(), range, min);
        int z = calcNearRandom(target.getBlockZ(), range, min);
        int y = target.getWorld().getHighestBlockYAt(x, z);
        Location location = new Location(target.getWorld(), x, y + 1, z);
        // System.out.println(location.toString());
        return location;
    }

    private int calcNearRandom(int i, int range, int min) {
        return i + (randomPosNeg() * (min + (int) (Math.random() * range)));
    }

    private int calcNearRandom(int i, int range) {
        return i + (randomPosNeg() * (int) (Math.random() * range));
    }

    private int randomPosNeg() {
        return Math.random() > 0.5 ? - 1 : 1;
    }

}
