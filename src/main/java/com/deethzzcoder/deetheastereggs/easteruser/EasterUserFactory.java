package com.deethzzcoder.deetheastereggs.easteruser;

import java.util.UUID;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public interface EasterUserFactory {

    EasterUser makeEasterUser(UUID uuid);

}
