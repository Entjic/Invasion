package de.entjic.invasion;

import de.entjic.invasion.command.*;
import de.entjic.invasion.game.Game;
import de.entjic.invasion.listener.CancelDeathItemDropListener;
import de.entjic.invasion.listener.CancelNaturalSpawningListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Invasion extends JavaPlugin {
    private Game game;

    @Override
    public void onEnable() {
        game = new Game();
        registerCommands();
        registerListener();
        saveResource("waves.yml", false);
        saveResource("config.yml", false);
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        Objects.requireNonNull(Bukkit.getPluginCommand("pause")).setExecutor(new PauseCommand(game));
        Objects.requireNonNull(Bukkit.getPluginCommand("start")).setExecutor(new StartCommand(game));
        Objects.requireNonNull(Bukkit.getPluginCommand("spawn")).setExecutor(new SpawnZombieCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("reset")).setExecutor(new ResetCommand(game));
        Objects.requireNonNull(Bukkit.getPluginCommand("game")).setExecutor(new GameCommand(game));
    }

    private void registerListener() {
        Bukkit.getPluginManager().registerEvents(new CancelNaturalSpawningListener(), this);
        Bukkit.getPluginManager().registerEvents(new CancelDeathItemDropListener(), this);
    }


}
