package com.deethzzcoder.deetheastereggs.utility;

import org.bukkit.command.CommandSender;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class PlayerUtils {

    private static final String GLOBAL_PERMISSION = "eastereggs.";

    private PlayerUtils() {

    }

    public static boolean hasPermission(CommandSender sender, String permission) {
        return sender.hasPermission(GLOBAL_PERMISSION + "*") || sender.hasPermission(GLOBAL_PERMISSION + "all") || sender.hasPermission(GLOBAL_PERMISSION + permission);
    }

}
