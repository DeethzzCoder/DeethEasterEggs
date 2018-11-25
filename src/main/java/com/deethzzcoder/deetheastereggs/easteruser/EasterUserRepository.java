package com.deethzzcoder.deetheastereggs.easteruser;

import java.io.IOException;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public interface EasterUserRepository {

    EasterUserStorage load() throws IOException;

    void save(EasterUserStorage easterUserStorage) throws IOException;

}
