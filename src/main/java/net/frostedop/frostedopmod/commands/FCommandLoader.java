package net.frostedop.frostedopmod.commands;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.CodeSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.frostedop.frostedopmod.FLog;
import net.frostedop.frostedopmod.FrostedOPMod;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;

import static org.bukkit.Bukkit.*;

public class FCommandLoader {

    private static CommandMap cmap = getCommandMap();

    public FCommandLoader() {
        registerCommands();
    }

    @SuppressWarnings("UseSpecificCatch")
    public static void registerCommands() {
        try {
            Pattern PATTERN = Pattern.compile("net/frostedop/frostedopmod/commands/(C_[^\\$]+)\\.class");
            CodeSource codeSource = FrostedOPMod.class.getProtectionDomain().getCodeSource();
            if (codeSource != null) {
                ZipInputStream zip = new ZipInputStream(codeSource.getLocation().openStream());
                ZipEntry zipEntry;
                while ((zipEntry = zip.getNextEntry()) != null) {
                    String entryName = zipEntry.getName();
                    Matcher matcher = PATTERN.matcher(entryName);
                    if (matcher.find()) {
                        try {
                            Class<?> commandClass = Class.forName("net.frostedop.frostedopmod.commands." + matcher.group(1));
                            Constructor construct = commandClass.getConstructor();
                            FCommand command = (FCommand) construct.newInstance();
                            command.register();
                        } catch (InvocationTargetException ex) {
                            broadcastMessage("" + ex);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            FrostedOPMod.plugin.getLogger().severe(ex.getLocalizedMessage());
        }
    }

    public static boolean isLCLMCommand(String name) {
        Command cmd = cmap.getCommand(name);
        if (!(cmd instanceof PluginCommand)) {
            return true;
        }
        PluginCommand command = (PluginCommand) cmd;
        return command.getPlugin() == FrostedOPMod.plugin;
    }

    private static CommandMap getCommandMap() {
        if (cmap == null) {
            try {
                final Field f = getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(getServer());
                return getCommandMap();
            } catch (NoSuchFieldException e) {
                FLog.severe(e);
            } catch (IllegalAccessException e) {
                FLog.severe(e);
            }
        } else if (cmap != null) {
            return cmap;
        }
        return getCommandMap();
    }
}
