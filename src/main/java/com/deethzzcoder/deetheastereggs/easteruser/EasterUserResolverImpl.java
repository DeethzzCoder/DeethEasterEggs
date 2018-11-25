package com.deethzzcoder.deetheastereggs.easteruser;

import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterUserResolverImpl implements EasterUserResolver {

    private final EasterUserStorage easterUserStorage;

    EasterUserResolverImpl(EasterUserStorage easterUserStorage) {
        this.easterUserStorage = easterUserStorage;
    }

    @Override
    public EasterUser findEasterUserByUuid(UUID uuid) {
        Optional<EasterUser> easterUserOptional = easterUserStorage.getEasterUsers().stream().filter(easterUser -> easterUser.getUuid().equals(uuid)).findFirst();
        return easterUserOptional.isPresent() ? easterUserOptional.get() : null;
    }

    @Override
    public Set<EasterUser> findEasterUsersByEasterEgg(EasterEgg easterEgg) {
        return easterUserStorage.getEasterUsers().stream().filter(easterUser -> easterUser.getEasterEggs().contains(easterEgg)).collect(Collectors.toSet());
    }

}
