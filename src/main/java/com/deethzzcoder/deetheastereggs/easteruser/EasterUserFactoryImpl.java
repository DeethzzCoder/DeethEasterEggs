package com.deethzzcoder.deetheastereggs.easteruser;

import java.util.UUID;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterUserFactoryImpl implements EasterUserFactory {

    private final EasterUserStorage easterUserStorage;

    EasterUserFactoryImpl(EasterUserStorage easterUserStorage) {
        this.easterUserStorage = easterUserStorage;
    }

    @Override
    public EasterUser makeEasterUser(UUID uuid) {
        EasterUser easterUser = new EasterUserImpl(uuid);
        easterUserStorage.getEasterUsers().add(easterUser);
        return easterUser;
    }

}
