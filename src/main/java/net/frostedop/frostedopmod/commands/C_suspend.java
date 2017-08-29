package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.events.BanningEvent;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hailey
 */
public class C_suspend extends FCommand {

    public C_suspend(String command) {
        super("suspend", "/suspend <player>", "Suspend a player.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 1)
        {
            return false;
        }
        
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        final Player target = Bukkit.getServer().getPlayer(args[0]);
        
        Player player = (Player) sender;

        if (player == null)
        {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        FUtil.bcastMsg(ChatColor.RED + sender.getName() + " - Suspending " + player.getName());
        
        try 
        {
        Thread.sleep(1000);
        }   
        catch(InterruptedException ex) 
        {
        Thread.currentThread().interrupt();
        }
        
        final String ip = player.getAddress().getAddress().getHostAddress().trim();
        
        String UUID = player.getUniqueId().toString();
        
        adminConfig().set(UUID + ".isactive", false);
        adminConfig().set(UUID + ".isadmin", false);

        
        player.setWhitelisted(false);

        player.setOp(false);

        BanningEvent.addBan(player, sender);

        player.setGameMode(GameMode.SURVIVAL);
        
        player.closeInventory();
        player.getInventory().clear();

        player.setFireTicks(10000);

        player.getWorld().strikeLightning(player.getLocation());

        
         player.setHealth(0.0);
        try 
        {
        Thread.sleep(2000);
        }   
        catch(InterruptedException ex) 
        {
        Thread.currentThread().interrupt();
        }
        
         FUtil.bcastMsg(ChatColor.RED + sender.getName() + " - Banning: " + player.getName() + " and IP: " + ip);

         player.kickPlayer(ChatColor.RED + "You've been suspended by " + sender.getName() + " - GTFO!");
            


        return true;
    }
    
}

