package com.deethzzcoder.deetheastereggs.easteregg;

import java.io.IOException;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public interface EasterEggRepository {

    EasterEggStorage load() throws IOException;

    void save(EasterEggStorage easterEggStorage) throws IOException;

}
