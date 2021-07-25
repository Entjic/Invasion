package de.entjic.invasion.game;

import de.entjic.invasion.game.mobs.CustomZombie;
import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Trigger implements GameObject {
    private final Location location;
    private double captured;
    private final Game game;

    public Trigger(Location location, Game game) {
        this.location = location;
        this.captured = 0;
        this.game = game;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void update(int gameTick) {
        System.out.println(captured);
        if (captured == 1.0) {
            game.pause();
            return;
        }
        if (gettingCaptured()) {
            captured = captured + 0.01;
        }
    }

    private boolean gettingCaptured() {
        for (Entity entity : location.getWorld().getEntities()) {
            net.minecraft.server.v1_15_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
            if (nmsEntity instanceof CustomZombie) {
                if (isCapturing(entity)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCapturing(Entity entity) {
        return entity.getLocation().distanceSquared(location) < 30.0; // FIXME: 25.07.2021 weird behavior doesnt work as intended
    }

    @Override
    public void render(int gameTick) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendActionBar(player, "§5Captured: " + (int) Math.floor(captured * 100) + "%");
            // TODO: 25.07.2021 add sound
        });
    }


    private void sendActionBar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"),
                net.minecraft.server.v1_15_R1.ChatMessageType.a((byte) 2));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}

