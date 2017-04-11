package net.hikaricp.skyblock.ore;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class listener implements Listener {

    public boolean isLavaOrWater(Block b) {
        boolean result = false;
        if (b.getType() == Material.LAVA) {
            result = true;
        }
        if (b.getType() == Material.STATIONARY_LAVA) {
            result = true;
        }
        return result;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent event) {
        final Block b = event.getBlock();
        if (b.getType() != Material.COBBLESTONE) {
            return;
        }
        final Chunk c = b.getChunk();
        boolean shouldrun = false;
        int radius = 2;
        for (int x = b.getX() - radius; x < b.getX() + 2; x++) {
            for (int z = b.getZ() - radius; z < b.getZ() + 2; z++) {
                Block block = c.getBlock(x, b.getY(), z);
                if (this.isLavaOrWater(block)) {
                    shouldrun = true;
                    break;
                }
            }
        }
        if (shouldrun) {
            Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.getPlugin(), new Runnable() {
                public void run() {
                    Random rand = new Random();
                    int chance = rand.nextInt(100) + 1;
                    for (final ChangeableBlocks changeableblocks : Main.ChangeableBlocks) {
                        if (chance >= changeableblocks.minchance && chance <= changeableblocks.maxchance) {
                            Bukkit.getServer().getScheduler().runTask(Main.getPlugin(), new Runnable() {
                                public void run() {
                                    b.setType(changeableblocks.type);
                                    b.setData((byte) changeableblocks.data);
                                }
                            });
                            event.setCancelled(true);
                            break;
                        }
                    }
                }
            });
        }


    }

}
