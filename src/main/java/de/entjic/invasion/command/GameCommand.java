package de.entjic.invasion.command;

import de.entjic.invasion.game.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GameCommand implements CommandExecutor {
    private final Game game;

    public GameCommand(Game game) {
        this.game = game;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage("Current game state: " + game.getGameState().toString());
        return true;
    }
}
