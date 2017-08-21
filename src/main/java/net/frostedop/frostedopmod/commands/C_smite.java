package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.ranks.Rank;
import net.frostedop.frostedopmod.FUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_smite extends FCommand 
{
    public C_smite() 
    {
        super("smite", "/smite <player> [reason]", "Smite a player!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        if (!Rank.isAdmin(sender)) 
        {
            sender.sendMessage(NO_PERM);
            return false;
        }
        
        if (args.length < 1) 
        {
            return false;
        }

        final Player player = Bukkit.getServer().getPlayer(args[0]);

        String reason = null;
        
        if (args.length > 1) 
        {
            reason = StringUtils.join(args, " ", 1, args.length);
        }

        if (player == null) 
        {
            sender.sendMessage(PLAYER_NOT_FOUND);
            return true;
        }

        smite(player, sender, reason);
        return true;
    }

    public static void smite(Player player, CommandSender sender, String reason) 
    {
        FUtil.bcastMsg(player.getName() + " has been a naughty, naughty boy.", ChatColor.RED);

        if (reason != null) 
        {
            FUtil.bcastMsg("Reason: " + reason + " (" + sender.getName() + ")", ChatColor.RED);
            player.sendMessage(ChatColor.RED + "You have been smited for, " + reason + " by, " + sender.getName());
        }
        
        else {
            FUtil.bcastMsg("Smited by: " + sender.getName(), ChatColor.RED);
            player.sendMessage(ChatColor.RED + "You have been smited by, " + sender.getName());
        }

        player.setOp(false);
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();

        final Location targetPos = player.getLocation();
        final World world = player.getWorld();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                final Location strike_pos = new Location(world, targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                world.strikeLightning(strike_pos);
            }
        }
        
        player.setHealth(0.0);
    }
}