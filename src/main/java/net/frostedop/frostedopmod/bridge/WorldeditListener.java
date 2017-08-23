package net.frostedop.frostedopmod.bridge;

import net.frostedop.frostedopmod.FrostedOPMod;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class WorldeditListener implements Listener {

    FrostedOPMod plugin;

    @SuppressWarnings("LeakingThisInConstructor")
    public WorldeditListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
}
