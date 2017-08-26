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
public class C_creative extends FCommand {

    public C_creative() {
        super("creative", "/creative", "", Arrays.asList("gmc"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            Bukkit.getOnlinePlayers().stream().map((player) -> {
                player.sendMessage(ChatColor.YELLOW + "You are now in GMC!");
                return player;
            }).forEach((player) -> {
                player.setGameMode(GameMode.CREATIVE);
            });
        }
        return true;
    }
}