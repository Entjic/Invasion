package de.entjic.invasion.game.wave;

import org.bukkit.entity.EntityType;

public class EntityTypeAmountHash {
    public final EntityType type;
    public final int amount;

    public EntityTypeAmountHash(EntityType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

}
