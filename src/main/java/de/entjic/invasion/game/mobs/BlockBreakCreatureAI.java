package de.entjic.invasion.game.mobs;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import de.entjic.invasion.Invasion;
import de.entjic.invasion.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public class BlockBreakCreatureAI implements Goal<Mob> {
    private final Mob mob;
    private final Block triggerBlock;
    private int tick;

    public BlockBreakCreatureAI(Mob mob, Block triggerBlock) {
        this.mob = mob;
        this.triggerBlock = triggerBlock;
        tick = 0;
    }

    @Override
    public boolean shouldActivate() {
        return true;
    }

    @Override
    public boolean shouldStayActive() {
        return shouldActivate();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
    }

    @Override
    public void tick() {
        tick++;
        if (tick > 20) {
            tick = 0;
            dig();
        }
    }

    @Override
    public @NotNull
    GoalKey<Mob> getKey() {
        return GoalKey.of(Mob.class, new NamespacedKey(Invasion.getProvidingPlugin(Invasion.class), "BlockBreakCreaturesAI"));
    }

    @Override
    public @NotNull
    EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.UNKNOWN_BEHAVIOR);
    }

    private boolean dig(int height) {
        Block frontBlock = getBlockInFront(height);
        if (frontBlock == null) {
            return false;
        }
        if (diggable(frontBlock)) {
            frontBlock.getWorld().playSound(frontBlock.getLocation(), Sound.BLOCK_STONE_HIT, 2.0F, 1.0F);
            System.out.println(frontBlock.breakNaturally(new ItemBuilder(Material.STONE_PICKAXE).toItemStack(), true));
            return true;
        }
        return false;
    }

    private void dig() {
        if (! dig(1)) {
            dig(0);
        }
    }

    private Block getBlockInFront(int height) {
        float yaw = (float) Math.round(mob.getLocation().getYaw() / 90);
        BlockFace bf = null;
        if (yaw == - 4 || yaw == 0 || yaw == 4) bf = BlockFace.SOUTH;
        if (yaw == - 1 || yaw == 3) bf = BlockFace.EAST;
        if (yaw == - 2 || yaw == 2) bf = BlockFace.NORTH;
        if (yaw == - 3 || yaw == 1) bf = BlockFace.WEST;
        if (bf == null) {
            return null;
        }
        return mob.getLocation().getBlock().getRelative(bf, 1).getLocation().add(0, height, 0).getBlock();
    }

    private boolean diggable(Block block) {
        Material material = block.getType();
        return ! material.equals(Material.AIR) && ! material.equals(Material.WATER) && ! material.equals(Material.LAVA) && ! block.equals(triggerBlock);
    }

}


