package net.frostedop.frostedopmod.config;

import net.frostedop.frostedopmod.FrostedOPMod;

public class ConfigFiles
{
    private static Config players;
    private static Config bans;
    private static Config permbans;
    private static Config mainconfig;
    private static Config admins;
    private static Config donators;
    private static Config logs;

    private ConfigFiles()
    {
    }

    public static void setup()
    {
        bans = new Config(FrostedOPMod.plugin, "bans.yml");
        bans.saveDefaultConfig();
        permbans = new Config(FrostedOPMod.plugin, "permbans.yml");
        permbans.saveDefaultConfig();
        players = new Config(FrostedOPMod.plugin, "players.yml");
        players.saveDefaultConfig();
        admins = new Config(FrostedOPMod.plugin, "admins.yml");
        admins.saveDefaultConfig();
        donators = new Config(FrostedOPMod.plugin, "donators.yml");
        donators.saveDefaultConfig();
        mainconfig = new Config(FrostedOPMod.plugin, "config.yml");
        mainconfig.saveDefaultConfig();
        logs = new Config(FrostedOPMod.plugin, "logs.yml");
        logs.saveDefaultConfig();
    }

    public static Config getLogs()
    {
        return logs;
    }

    public static Config getPlayer()
    {
        return players;
    }

    public static Config getBans()
    {
        return bans;
    }

    public static Config getPermbans()
    {
        return permbans;
    }

    public static Config getAdmins()
    {
        return admins;
    }

    public static Config getDonators()
    {
        return donators;
    }

    public static Config getMConfig()
    {
        return mainconfig;
    }
}
