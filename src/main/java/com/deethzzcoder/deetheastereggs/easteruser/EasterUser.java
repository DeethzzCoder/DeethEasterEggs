package com.deethzzcoder.deetheastereggs.easteruser;

import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;

import java.util.Set;
import java.util.UUID;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public interface EasterUser {

    UUID getUuid();

    Set<EasterEgg> getEasterEggs();

}
