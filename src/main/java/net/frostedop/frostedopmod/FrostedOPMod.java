package net.frostedop.frostedopmod;

import java.util.logging.Level;
import net.frostedop.frostedopmod.listeners.F_BlockListener;
import net.frostedop.frostedopmod.commands.F_CommandLoader;
import net.frostedop.frostedopmod.config.ConfigEntry;
import static net.frostedop.frostedopmod.config.ConfigEntry.T_AUTODAY;
import static net.frostedop.frostedopmod.config.ConfigEntry.T_NO_RAIN;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.listeners.F_ChatListener;
import net.frostedop.frostedopmod.listeners.F_MainListener;
import net.frostedop.frostedopmod.listeners.F_PlayerListener;
import net.frostedop.frostedopmod.listeners.F_RankListener;
import net.frostedop.frostedopmod.worlds.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FrostedOPMod extends JavaPlugin
{    
    public static FrostedOPMod plugin;

    @Override
    public void onLoad()
    {
        plugin = this;
        
        ConfigFiles.setup();
    }

    @Override
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void onEnable()
    {
        ConfigFiles.setup();

        getLogger().log(Level.INFO, "---------------------");
        getLogger().log(Level.INFO, "FrostedOPMod: R version: {0} authors: {1}", new Object[]
        {
            this.getDescription().getVersion(), this.getDescription().getAuthors()
        });
        getLogger().log(Level.INFO, "---------------------");
        // start stuff
        this.weather();
        new WorldManager();
        new F_BlockListener();
        new F_BlockListener();
        new F_ChatListener();
        new F_MainListener();
        new F_PlayerListener();
        new F_RankListener();
        new F_CommandLoader();
    }

    @Override
    public void onDisable()
    {
    }

    void weather()
    {
        for (final Player all : Bukkit.getOnlinePlayers())
        {
            if (ConfigEntry.MainConfig().getBoolean(T_NO_RAIN))
            {
                new BukkitRunnable()
                {
                    public void run()
                    {
                        all.getWorld().setWeatherDuration(0);
                        all.getWorld().setThunderDuration(0);
                    }
                }.runTaskTimer(this, 0L, 1L);
            }
            if (ConfigEntry.MainConfig().getBoolean(T_AUTODAY))
            {
                new BukkitRunnable()
                {
                    public void run()
                    {
                        for (Player all : Bukkit.getOnlinePlayers())
                        {
                            all.getWorld().setTime(600L);
                        }
                    }
                }.runTaskTimer(this, 0L, 1L);
            }
        }
    }
}
