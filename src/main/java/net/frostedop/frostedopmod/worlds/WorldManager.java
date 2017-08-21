package net.frostedop.frostedopmod.worlds;

import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldManager implements Listener
{
    
    public static World getAdminWorld()
    {
        World world = Bukkit.getWorld("adminworld");
        if(world == null)
        {
            WorldCreator creator = new WorldCreator("adminworld");
            creator.generator(new Flatworld());
            world = creator.createWorld();
        }
        return world;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        if (event.getPlayer().getWorld().equals(WorldManager.getAdminWorld()))
        {
            if (!Rank.isAdmin(event.getPlayer()))
            {
                event.getPlayer().sendMessage(ChatColor.GRAY + "You can't place blocks in the adminworld!");
                event.isCancelled();
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event)
    {
        if (event.getPlayer().getWorld().equals(WorldManager.getAdminWorld()))
        {
            if (!Rank.isAdmin(event.getPlayer()))
            {
                event.getPlayer().sendMessage(ChatColor.GRAY + "You can't break blocks in the adminworld!");
                event.isCancelled();
            }
        }
    }
    
    public static World getFlatlands()
    {
        World world = Bukkit.getWorld("flatlands");
        if(world == null)
        {
            WorldCreator creator = new WorldCreator("flatlands");
            creator.generator(new Flatworld());
            world = creator.createWorld();
        }
        return world;
    }
    
    public static World getBuildersWorld()
    {
        World world = Bukkit.getWorld("builderworld");
        if(world == null)
        {
            WorldCreator creator = new WorldCreator("builderworld");
            creator.generator(new Flatworld());
            world = creator.createWorld();
        }
        return world;
    }
    
    public static void wipeFlatlands()
    {
        final World flatlands = getFlatlands();
        for(Player player : flatlands.getPlayers())
        {
            player.setOp(false);
            player.setWhitelisted(false);
        }
        Bukkit.getServer().setWhitelist(true);
        unloadWorld(flatlands);
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                FUtil.deleteFiles(flatlands.getWorldFolder());
                Bukkit.getServer().setWhitelist(false);
            }
        }.runTaskLater(FrostedOPMod.plugin, 20L * 5L);
        getFlatlands();
    }
    
    public static void unloadWorld(World world)
    {
        if(world != null)
        {
            for(Player player : world.getPlayers())
            {
                for(World newworld : Bukkit.getWorlds())
                {
                    if(world != newworld)
                    {
                        player.teleport(newworld.getSpawnLocation());
                    }
                }
                player.kickPlayer("The world you are in is being unloaded.");
            }
            Bukkit.getServer().unloadWorld(world, false);
        }
    }
    
    public static void wipeMainWorld()
    {
        ConfigEntry.MainConfig().set("general.wipe", true);
        Bukkit.getServer().shutdown();
    }
}

