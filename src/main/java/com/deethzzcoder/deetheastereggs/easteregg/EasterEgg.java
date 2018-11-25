package com.deethzzcoder.deetheastereggs.easteregg;

import org.bukkit.Location;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public interface EasterEgg {

    String getName();

    String getPrize();

    Location getLocation();

    void setName(String name);

    void setPrize(String prize);

    void setLocation(Location location);

    boolean hasPrize();

}
