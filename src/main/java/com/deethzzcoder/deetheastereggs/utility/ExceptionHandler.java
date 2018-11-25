package com.deethzzcoder.deetheastereggs.utility;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class ExceptionHandler {

    private ExceptionHandler() {

    }

    public static void handleException(Exception exception) {
        LoggerUtils.warn("&cException was detected!", "&cPlease, send this exception to developer! [www.spigotmc.org/members/deethzzcoder.611082/]");
        exception.printStackTrace();
        LoggerUtils.warn("&cThe plugin DeethEasterEggs will disabled!");
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.disablePlugin(pluginManager.getPlugin("DeethEasterEggs"));
    }

}
