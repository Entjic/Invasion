package de.entjic.invasion.commands;

import de.entjic.invasion.game.mobs.CustomZombie;
import net.minecraft.server.v1_15_R1.WorldServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnZombieCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (! (sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        CustomZombie customZombie = new CustomZombie(player.getLocation(), player.getLocation().add(0, 0, 0));
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
        world.addEntity(customZombie);
        return true;
    }
}
