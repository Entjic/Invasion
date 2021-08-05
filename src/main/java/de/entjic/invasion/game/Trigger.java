package de.entjic.invasion.game;

import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Trigger implements GameObject {
    private final Location location;
    private final Game game;
    private int captured;
    private boolean capturing;

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
        captured += calcCaptured();
        if (captured >= 100) {
            game.pause();
            captured = 100;

        }

    }

    private int calcCaptured() {
        int capturingEntities = getCapturingEntities();
        if (capturingEntities > 0) {
            capturing = true;
            return capturingEntities;
        }
        capturing = false;
        if (captured > 0) {
            return - 1;
        }
        return 0;
    }

    private int getCapturingEntities() {
        int capturingEntities = 0;
        for (Entity entity : location.getWorld().getEntities()) {
            if (isCapturing(entity)) {
                capturingEntities++;
            }
        }
        return capturingEntities;
    }


    private boolean isCapturing(Entity entity) {
        if (entity.getType().equals(EntityType.PLAYER)) {
            return false;
        }
        return entity.getLocation().distanceSquared(location) < 9;
    }

    @Override
    public void render(int gameTick) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendActionBar(player, "§5Captured: " + captured + "%");
            if (capturing) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 2);
            }
        });
    }


    private void sendActionBar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"),
                net.minecraft.server.v1_15_R1.ChatMessageType.a((byte) 2));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

    }

}

