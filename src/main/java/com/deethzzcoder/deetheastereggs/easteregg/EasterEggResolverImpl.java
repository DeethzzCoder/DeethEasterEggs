package com.deethzzcoder.deetheastereggs.easteregg;

import org.bukkit.Location;

import java.util.Optional;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterEggResolverImpl implements EasterEggResolver {

    private final EasterEggStorage easterEggStorage;

    EasterEggResolverImpl(EasterEggStorage easterEggStorage) {
        this.easterEggStorage = easterEggStorage;
    }

    @Override
    public EasterEgg findEasterEggByName(String name) {
        Optional<EasterEgg> easterEggOptional = easterEggStorage.getEasterEggs().stream().filter(easterEgg -> easterEgg.getName().equals(name)).findFirst();
        return easterEggOptional.isPresent() ? easterEggOptional.get() : null;
    }

    @Override
    public EasterEgg findEasterEggByLocation(Location location) {
        Optional<EasterEgg> easterEggOptional = easterEggStorage.getEasterEggs().stream().filter(easterEgg -> easterEgg.getLocation().equals(location)).findFirst();
        return easterEggOptional.isPresent() ? easterEggOptional.get() : null;
    }

}
