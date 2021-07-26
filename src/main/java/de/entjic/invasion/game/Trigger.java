package de.entjic.invasion.game;

import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Trigger implements GameObject {
    private final Location location;
    private final Game game;
    private double captured;

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
        captured = captured >= 0 ? captured + gettingCaptured() * 0.01 : captured;

        if (captured >= 1) {
            game.pause();
            captured = 1;
        }

    }

    private int gettingCaptured() {
        int i = 0;
        for (Entity entity : location.getWorld().getEntities()) {
            if (isCapturing(entity)) {
                i++;
            }
        }
        return i == 0 && captured != 0 ? - 1 : i;
    }

    private boolean isCapturing(Entity entity) {
        return entity.getLocation().distanceSquared(location) < 9; // FIXME: 25.07.2021 weird behavior doesnt work as intended
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

