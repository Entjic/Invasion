package de.entjic.invasion.commands;

import de.entjic.invasion.game.Game;
import de.entjic.invasion.game.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PauseCommand implements CommandExecutor {
    private final Game game;

    public PauseCommand(Game game) {
        this.game = game;
    }

    @Override
    public boolean onCommand(@org.jetbrains.annotations.NotNull CommandSender sender, @org.jetbrains.annotations.NotNull Command command, @org.jetbrains.annotations.NotNull String label, @org.jetbrains.annotations.NotNull String[] args) {
        if (! sender.isOp()) {
            sender.sendMessage("U dont have permission");
            return true;
        }
        if (game.getGameState().equals(GameState.PAUSED)) {
            game.resume();
            sender.sendMessage("Game resumed");
            return true;
        }
        game.pause();
        sender.sendMessage("Game paused");
        return true;
    }
}
