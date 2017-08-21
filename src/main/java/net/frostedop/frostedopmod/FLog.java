package net.frostedop.frostedopmod;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Savnith
 */

// Added this to make things more stable?
public class FLog 
{
    public static final Logger log = Logger.getLogger("Minecraft");
    public static String name = "[FrostedOPMod]: ";
    
    public static void info(String msg)
    {
        log.log(Level.INFO, "{0}", new Object[]{name + msg});
    }
    
    public static void severe(String args)
    {
        log.log(Level.SEVERE, "{0}", new Object[]{name + args});
    }
    
    public static void severe(Exception ex)
    {
        log.log(Level.SEVERE, "{0}", new Object[]{name + ex.toString()});
    }
}
