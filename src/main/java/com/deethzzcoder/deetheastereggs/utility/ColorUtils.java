package com.deethzzcoder.deetheastereggs.utility;

import org.bukkit.ChatColor;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class ColorUtils {

    private static final char ALT_COLOR_CHAR = '&';

    private ColorUtils() {

    }

    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes(ALT_COLOR_CHAR, input);
    }

}
