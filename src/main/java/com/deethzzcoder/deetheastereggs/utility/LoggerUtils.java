package com.deethzzcoder.deetheastereggs.utility;

import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class LoggerUtils {

    private static final Logger logger = Bukkit.getPluginManager().getPlugin("DeethEasterEggs").getLogger();

    private static final String WELCOME_MESSAGE = "\n" +
            "&a<<<===----------------------------------------------------------------------===>>>\n" +
            "   &aThe plugin DeethEasterEggs was successfully enabled!\n" +
            "   &aDeveloper of the plugin: Nikita (DeethzzCoder) Knyazev [www.spigotmc.org/members/deethzzcoder.611082/]\n" +
            "   &aAll found bugs, you can send to developer!\n" +
            "   &aThanks for using this plugin!\n" +
            "&a<<<===----------------------------------------------------------------------===>>>";

    private static final String FAREWELL_MESSAGE = "\n" +
            "&a<<<===----------------------------------------------------------------------===>>>\n" +
            "   &aThe plugin DeethEasterEggs was successfully disabled!\n" +
            "   &aDeveloper of the plugin: Nikita (DeethzzCoder) Knyazev [www.spigotmc.org/members/deethzzcoder.611082/]\n" +
            "   &aAll found bugs, you can send to developer!\n" +
            "   &aThanks for using this plugin!\n" +
            "&a<<<===----------------------------------------------------------------------===>>>";

    private LoggerUtils() {

    }

    public static void warn(String warnMessage) {
        logger.log(Level.WARNING, ColorUtils.color(warnMessage));
    }

    public static void warn(String... warnMessages) {
        Arrays.stream(warnMessages).forEach(warnMessage -> logger.log(Level.WARNING, ColorUtils.color(warnMessage)));
    }

    public static void welcome() {
        logger.log(Level.INFO, ColorUtils.color(WELCOME_MESSAGE));
    }

    public static void farewell() {
        logger.log(Level.INFO, ColorUtils.color(FAREWELL_MESSAGE));
    }

}
