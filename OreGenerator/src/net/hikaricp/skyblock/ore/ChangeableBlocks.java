package net.hikaricp.skyblock.ore;

import org.bukkit.Material;

public class ChangeableBlocks {

    public Material type;
    public short data;
    public int minchance;
    public int maxchance;

    public ChangeableBlocks(Material type, short data, int minchance, int maxchance) {
        this.type = type;
        this.data = data;
        this.minchance = minchance;
        this.maxchance = maxchance;
    }

}
