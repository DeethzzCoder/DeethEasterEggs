package com.deethzzcoder.deetheastereggs.easteruser;

import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterUserImpl implements EasterUser {

    private final UUID uuid;
    private final Set<EasterEgg> easterEggs;

    EasterUserImpl(UUID uuid, Set<EasterEgg> easterEggs) {
        this.uuid = uuid;
        this.easterEggs = easterEggs;
    }

    EasterUserImpl(UUID uuid) {
        this(uuid, new HashSet<>());
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public Set<EasterEgg> getEasterEggs() {
        return easterEggs;
    }

}
