package de.entjic.invasion.command;

import de.entjic.invasion.game.Game;
import de.entjic.invasion.game.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {
    private final Game game;

    public StartCommand(Game game) {
        this.game = game;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (! sender.isOp()) {
            sender.sendMessage("U dont have permission");
            return true;
        }

        if (! (sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (! game.getGameState().equals(GameState.INACTIVE)) {
            sender.sendMessage("Game in wrong state: " + game.getGameState().toString());
            return true;
        }
        game.start(player.getLocation());
        sender.sendMessage("Game started");


        return true;
    }
}
