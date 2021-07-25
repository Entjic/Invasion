package de.entjic.invasion;

import de.entjic.invasion.commands.*;
import de.entjic.invasion.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Invasion extends JavaPlugin {
    private Game game;

    @Override
    public void onEnable() {
        game = new Game();
        registerCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        Objects.requireNonNull(Bukkit.getPluginCommand("pause")).setExecutor(new PauseCommand(game));
        Objects.requireNonNull(Bukkit.getPluginCommand("start")).setExecutor(new StartCommand(game));
        Objects.requireNonNull(Bukkit.getPluginCommand("spawn")).setExecutor(new SpawnZombieCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("reset")).setExecutor(new ResetCommand(game));
        Objects.requireNonNull(Bukkit.getPluginCommand("game")).setExecutor(new GameCommand(game));
    }


}
