package net.frostedop.frostedopmod;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Savnith
 */
// Added this to make things more stable?
public class FLog {

    public static String name = "[FrostedOPMod]: ";

    public static void info(String msg) {
        Logger.getLogger("Minecraft").log(Level.INFO, "{0}", new Object[]{name + msg});
    }

    public static void severe(String args) {
        Logger.getLogger("Minecraft").log(Level.SEVERE, "{0}", new Object[]{name + args});
    }

    public static void severe(Exception ex) {
        Logger.getLogger("Minecraft").log(Level.SEVERE, "{0}", new Object[]{name + ex.toString()});
    }
}
