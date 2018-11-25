package com.deethzzcoder.deetheastereggs.easteregg;

import java.util.Optional;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterEggPurifierImpl implements EasterEggPurifier {

    private final EasterEggStorage easterEggStorage;

    EasterEggPurifierImpl(EasterEggStorage easterEggStorage) {
        this.easterEggStorage = easterEggStorage;
    }

    @Override
    public void purifyEasterEggByName(String name) {
        Optional<EasterEgg> easterEggOptional = easterEggStorage.getEasterEggs().stream().filter(easterEggs -> easterEggs.getName().equals(name)).findFirst();
        easterEggOptional.ifPresent(easterEgg -> easterEggStorage.getEasterEggs().remove(easterEgg));
    }

}
