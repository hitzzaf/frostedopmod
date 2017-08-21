package net.frostedop.frostedopmod;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class FUtil 
{
    
    // Masterbuilders
    public static final List<String> MASTERBUILDERS = Arrays.asList("");
    public static final String STABLE = "Stable";
    public static final String NOT_STABLE = "Not Stable";
    
    private FUtil() 
    {
        
    }
    
    public static int bcastMsg(String message) 
    {
        return Bukkit.broadcastMessage(message);
    }

    public static int bcastMsg(String message, ChatColor color) 
    {
        return Bukkit.broadcastMessage((color == null ? "" : color) + message);
    }

    public static String color(String string) 
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }  
    
    public static boolean deleteFiles(File path) {
        
        if (path.exists()) {
            File files[] = path.listFiles();
            
            for (int i = 0; i < files.length; i++) {
                
                if (files[i].isDirectory()) {
                    deleteFiles(files[i]);
                }else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
}
