package com.deethzzcoder.deetheastereggs.easteregg;

import org.bukkit.Location;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public interface EasterEggResolver {

    EasterEgg findEasterEggByName(String name);

    EasterEgg findEasterEggByLocation(Location location);

}
