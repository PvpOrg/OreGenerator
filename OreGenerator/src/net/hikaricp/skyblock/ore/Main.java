package net.hikaricp.skyblock.ore;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static ArrayList<ChangeableBlocks> ChangeableBlocks = new ArrayList<ChangeableBlocks>();
    public final listener listener = new listener();


    public void onEnable() {
        fileutil.instance.setup(this.getDataFolder());
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(this.listener, this);
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("OreGenerator");
    }

}
