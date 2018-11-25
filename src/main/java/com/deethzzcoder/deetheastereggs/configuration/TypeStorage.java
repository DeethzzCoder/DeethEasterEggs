package com.deethzzcoder.deetheastereggs.configuration;

import com.deethzzcoder.deetheastereggs.configuration.exception.ConfigurationException;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public enum TypeStorage {

    YAML;

    static TypeStorage fromString(String typeString) throws ConfigurationException {
        for(TypeStorage typeStorage : TypeStorage.values()) {
            if(typeStorage.toString().equalsIgnoreCase(typeString)) return typeStorage;
        }
        throw new ConfigurationException("This storage type doesn't exist!");
    }

}
