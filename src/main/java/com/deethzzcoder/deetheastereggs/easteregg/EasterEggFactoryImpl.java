package com.deethzzcoder.deetheastereggs.easteregg;

import org.bukkit.Location;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterEggFactoryImpl implements EasterEggFactory {

    private final EasterEggStorage easterEggStorage;

    EasterEggFactoryImpl(EasterEggStorage easterEggStorage) {
        this.easterEggStorage = easterEggStorage;
    }

    @Override
    public EasterEgg makeEasterEgg(String name, String prize, Location location) {
        EasterEgg easterEgg = new EasterEggImpl(name, prize, location);
        easterEggStorage.getEasterEggs().add(easterEgg);
        return easterEgg;
    }

    @Override
    public EasterEgg makeEasterEgg(String name, Location location) {
        return makeEasterEgg(name, "", location);
    }

}
