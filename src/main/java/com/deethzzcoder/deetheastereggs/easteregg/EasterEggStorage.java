package com.deethzzcoder.deetheastereggs.easteregg;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterEggStorage {

    private final Set<EasterEgg> easterEggs;

    EasterEggStorage(Set<EasterEgg> easterEggs) {
        this.easterEggs = easterEggs;
    }

    EasterEggStorage() {
        this(new HashSet<>());
    }

    public Set<EasterEgg> getEasterEggs() {
        return easterEggs;
    }

}
