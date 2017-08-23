package net.frostedop.frostedopmod.commands;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import static net.frostedop.frostedopmod.FrostedOPMod.plugin;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public abstract class FCommand implements CommandExecutor, TabExecutor {

    protected CommandSender sender;
    //
    public static final String NO_PERM = ChatColor.RED + "You have no permission to use this command!";
    public static final String OP = ChatColor.YELLOW + "You are now op!";
    public static final String DEOP = ChatColor.YELLOW + "You are no longer op!";
    public static final String PLAYER_NOT_FOUND = ChatColor.RED + "Player was not found!";

    public int bcastMsg(String message) {
        return Bukkit.broadcastMessage(message);
    }

    public int bcastMsg(String message, ChatColor color) {
        return Bukkit.broadcastMessage((color == null ? "" : color) + message);
    }

    public static FileConfiguration adminConfig() {
        return ConfigEntry.AdminConfig();
    }

    public FileConfiguration playerConfig() {
        return ConfigEntry.PlayerConfig();
    }

    public FileConfiguration mainConfig() {
        return ConfigEntry.MainConfig();
    }

    public FileConfiguration banConfig() {
        return ConfigEntry.BansConfig();
    }

    protected void message(final CommandSender sender, final String message, final ChatColor color) {
        if (sender == null) {
            return;
        }

        sender.sendMessage(color + message);
    }

    protected void message(final String message) {
        message(sender, message);
    }

    protected void message(final String message, final ChatColor color) {
        message(sender, message, color);
    }

    protected void message(final CommandSender sender, final String message) {
        message(sender, message, ChatColor.GRAY);
    }

    protected boolean isConsole() {
        return !(sender instanceof Player);
    }

    protected final String command;
    protected final String description;
    protected final List<String> alias;
    protected final String usage;

    protected static CommandMap cmap;

    public FCommand(String command) {
        this(command, null, null, null, null);
    }

    public FCommand(String command, String usage) {
        this(command, usage, null, null, null);
    }

    public FCommand(String command, String usage, String description) {
        this(command, usage, description, null);
    }

    public FCommand(String command, String usage, String description, List<String> aliases) {
        this(command, usage, description, null, aliases);
    }

    public FCommand(String command, String usage, String description, String permissionMessage, List<String> aliases) {
        this.command = command.toLowerCase();
        this.usage = usage;
        this.description = description;
        this.alias = aliases;
    }

    public void register() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        ReflectCommand cmd = new ReflectCommand(this.command);
        if (this.alias != null) {
            cmd.setAliases(this.alias);
        }
        if (this.description != null) {
            cmd.setDescription(this.description);
        }
        if (this.usage != null) {
            cmd.setUsage(this.usage);
        }
        if (!getCommandMap().register("", cmd)) {
            this.unRegisterBukkitCommand(Bukkit.getPluginCommand(cmd.getName()));
            getCommandMap().register("", cmd);
        }
        cmd.setExecutor(this);
    }

    final CommandMap getCommandMap() {
        if (cmap == null) {
            try {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (cmap != null) {
            return cmap;
        }
        return getCommandMap();
    }

    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }

    private Object getPrivateField(Object object, String field) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        Object result = objectField.get(object);
        objectField.setAccessible(false);
        return result;
    }

    private void unRegisterBukkitCommand(PluginCommand cmd) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        try {
            Object result = getPrivateField(plugin.getServer().getPluginManager(), "commandMap");
            SimpleCommandMap commandMap = (SimpleCommandMap) result;
            Object map = getPrivateField(commandMap, "knownCommands");
            @SuppressWarnings("unchecked")
            HashMap<String, Command> knownCommands = (HashMap<String, Command>) map;
            knownCommands.remove(cmd.getName());
            for (String registeredalias : cmd.getAliases()) {
                knownCommands.remove(registeredalias);
            }
        } catch (IllegalAccessException e) {
            // do nothing 
        }
    }

    public final class ReflectCommand extends Command {

        private FCommand exe = null;

        protected ReflectCommand(String command) {
            super(command);
        }

        public void setExecutor(FCommand exe) {
            this.exe = exe;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                if (!exe.onCommand(sender, this, commandLabel, args)) {
                    sender.sendMessage(this.usageMessage.replaceAll("<command>", command));
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
            if (exe != null) {
                return exe.onTabComplete(sender, this, alias, args);
            }
            return null;
        }
    }
}
