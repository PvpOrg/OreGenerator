package net.hikaricp.skyblock.ore;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class fileutil {

    public static fileutil instance = new fileutil();

    public File conf;

    public void loadValues() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(this.conf);
        for (String string : config.getStringList("blocks")) {
            String split[] = string.split("-");
            Material type = Material.matchMaterial(split[0]);
            short data = Short.parseShort(split[1]);
            int minchance = Integer.parseInt(split[2]);
            int maxchance = Integer.parseInt(split[3]);
            ChangeableBlocks changeableblocks = new ChangeableBlocks(type, data, minchance, maxchance);
            Main.ChangeableBlocks.add(changeableblocks);
        }
    }

    public void setup(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        this.conf = new File(dir + File.separator + "config.yml");
        if (!this.conf.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(this.conf);
            config.set("Block documentation", "<blocktype> <blockdata> <minchance> <maxchance>");
            ArrayList<String> blocks = new ArrayList<String>();
            blocks.add(Material.COAL_ORE.name() + "-0-61-100");
            blocks.add(Material.IRON_ORE.name() + "-0-40-60");
            blocks.add(Material.LAPIS_ORE.name() + "-0-31-39");
            blocks.add(Material.REDSTONE_ORE.name() + "-0-21-30");
            blocks.add(Material.GOLD_ORE.name() + "-0-10-20");
            blocks.add(Material.DIAMOND_ORE.name() + "-0-5-9");
            blocks.add(Material.EMERALD_ORE.name() + "-0-0-4");
            config.set("blocks", blocks);
            try {
                config.save(this.conf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.loadValues();
    }

}
