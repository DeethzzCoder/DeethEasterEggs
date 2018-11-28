package com.deethzzcoder.deetheastereggs.utility;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.logging.Logger;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class ExceptionHandler {

    private static final Logger logger = Logger.getLogger("ExceptionHandler");

    private ExceptionHandler() {

    }

    public static void handleException(Exception exception) {
        logger.warning(ColorUtils.color("&cException was detected!"));
        logger.warning(ColorUtils.color("&cPlease, send this exception to developer! [www.spigotmc.org/members/deethzzcoder.611082/]"));
        exception.printStackTrace();
        logger.warning(ColorUtils.color("&cThe plugin DeethEasterEggs will disabled!"));
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.disablePlugin(pluginManager.getPlugin("DeethEasterEggs"));
    }

}
