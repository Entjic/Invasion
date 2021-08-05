package de.entjic.invasion.game.wave;

import de.entjic.invasion.config.ConfigContainer;
import de.entjic.invasion.game.GameObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class WaveSpawner implements GameObject {

    private final Location target;
    private Wave current;
    private final int waveduration;
    private int waveNr;

    public WaveSpawner(Location target) {
        this.target = target;
        waveduration = ConfigContainer.getInstance().getFile("config").getInt("waveduration");
    }


    @Override
    public void update(int gameTick) {
        if (gameTick % waveduration == 0) {
            this.waveNr = gameTick / waveduration;
            spawnWave(gameTick / waveduration);
        }
    }

    @Override
    public void render(int gameTick) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage("Wave nr " + waveNr + " spawned");
        });
    }

    private void spawnWave(int wave) {
        current = new Wave(readWaveConfiguration(wave), target);
        current.spawn();
    }


    private List<EntityTypeAmountHash> readWaveConfiguration(int wave) {
        List<EntityTypeAmountHash> list = new ArrayList<>();
        ConfigurationSection configuration = ConfigContainer.getInstance().getFile("waves")
                .getFileConfiguration().getConfigurationSection(String.valueOf(wave));
        if (configuration != null) {
            createAndAddHashes(list, configuration);
        }
        return list;
    }

    private void createAndAddHashes(List<EntityTypeAmountHash> list, ConfigurationSection configuration) {
        for (String key : configuration.getKeys(false)) {
            EntityType type = getEntityTypeByString(key);
            EntityTypeAmountHash hash = new EntityTypeAmountHash(type, configuration.getInt(key));
            list.add(hash);
        }
    }

    private EntityType getEntityTypeByString(String string) {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.name().equalsIgnoreCase(string)) {
                return entityType;
            }
        }
        return null;
    }


}
