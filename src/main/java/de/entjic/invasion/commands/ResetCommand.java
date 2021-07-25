package de.entjic.invasion.commands;

import de.entjic.invasion.game.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public class ResetCommand implements CommandExecutor {
    private final Game game;

    public ResetCommand(Game game) {
        this.game = game;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (! (sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        for (Entity entity : player.getWorld().getEntities()) {
            if (entity instanceof Zombie) {
                entity.remove(); // TODO: 25.07.2021 only instances of CustomZombie need to be cleared
            }
        }
        game.reset();

        player.sendMessage("Game has been reset");

        return true;
    }
}
