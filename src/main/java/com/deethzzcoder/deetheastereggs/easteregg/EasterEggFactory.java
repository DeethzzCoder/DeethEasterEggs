package com.deethzzcoder.deetheastereggs.easteregg;

import org.bukkit.Location;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public interface EasterEggFactory {

    EasterEgg makeEasterEgg(String name, String prize, Location location);

    EasterEgg makeEasterEgg(String name, Location location);

}
