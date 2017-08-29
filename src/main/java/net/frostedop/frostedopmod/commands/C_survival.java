package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Savnith
 */
public class C_survival extends FCommand {

    public C_survival() {
        super("survival", "/survival", "Change your gamemode to survival", Arrays.asList("gms"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            Bukkit.getOnlinePlayers().stream().map((player) -> {
                player.sendMessage(ChatColor.YELLOW + "You are now in Survival!");
                return player;
            }).forEach((player) -> {
                player.setGameMode(GameMode.SURVIVAL);
            });
        }
        return true;
    }
}
