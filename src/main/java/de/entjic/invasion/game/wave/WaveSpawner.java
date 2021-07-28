package de.entjic.invasion.game.wave;

import de.entjic.invasion.files.FileContainer;
import de.entjic.invasion.game.GameObject;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class WaveSpawner implements GameObject {

    private final Location target;
    private Wave current;

    public WaveSpawner(Location target) {
        this.target = target;
    }


    @Override
    public void update(int gameTick) {
        if (gameTick % 20 == 0) {
            spawnWave(gameTick / 20);
        }
    }

    @Override
    public void render(int gameTick) {
    }

    private void spawnWave(int wave) {
        current = new Wave(readWaveConfiguration(wave), target);
        current.spawn();
    }


    private List<EntityTypeAmountHash> readWaveConfiguration(int wave) {
        List<EntityTypeAmountHash> list = new ArrayList<>();
        ConfigurationSection configuration = FileContainer.getInstance().getFile("waves")
                .getFileConfiguration().getConfigurationSection(String.valueOf(wave));
        if (configuration != null) {
            createAndAddHashes(list, configuration);
        }
        return list;
    }

    private void createAndAddHashes(List<EntityTypeAmountHash> list, ConfigurationSection configuration) {
        for (String key : configuration.getKeys(false)) {
            EntityTypeAmountHash hash = new EntityTypeAmountHash(EntityType.valueOf(key), configuration.getInt(key));
            list.add(hash);
        }
    }


}
