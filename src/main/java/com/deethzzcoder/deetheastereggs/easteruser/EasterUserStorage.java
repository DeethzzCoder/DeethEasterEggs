package com.deethzzcoder.deetheastereggs.easteruser;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterUserStorage {

    private final Set<EasterUser> easterUsers;

    EasterUserStorage(Set<EasterUser> easterUsers) {
        this.easterUsers = easterUsers;
    }

    EasterUserStorage() {
        this(new HashSet<>());
    }

    public Set<EasterUser> getEasterUsers() {
        return easterUsers;
    }

}
