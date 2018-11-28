package com.deethzzcoder.deetheastereggs.easteruser;

import com.deethzzcoder.deetheastereggs.DeethEasterEggsPlugin;
import com.deethzzcoder.deetheastereggs.configuration.MainConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.TypeStorage;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import com.deethzzcoder.deetheastereggs.easteruser.exception.EasterUserException;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class GeneralEasterUser {

    private EasterUserRepository easterUserRepository;
    private EasterUserStorage easterUserStorage;
    private EasterUserFactory easterUserFactory;
    private EasterUserResolver easterUserResolver;

    public GeneralEasterUser(Plugin plugin, MainConfiguration mainConfiguration, EasterEggResolver easterEggResolver) throws IOException {
        if(!(plugin instanceof DeethEasterEggsPlugin)) throw new EasterUserException("This JavaPlugin type don't support!");
        TypeStorage typeStorage = mainConfiguration.getTypeStorage();
        if(typeStorage == TypeStorage.YAML) this.easterUserRepository = new YamlEasterUserRepository(plugin.getDataFolder(), easterEggResolver, mainConfiguration);
        this.easterUserStorage = easterUserRepository.load();
        this.easterUserFactory = new EasterUserFactoryImpl(easterUserStorage);
        this.easterUserResolver = new EasterUserResolverImpl(easterUserStorage);
    }

    public EasterUserRepository getEasterUserRepository() {
        return easterUserRepository;
    }

    public EasterUserStorage getEasterUserStorage() {
        return easterUserStorage;
    }

    public EasterUserFactory getEasterUserFactory() {
        return easterUserFactory;
    }

    public EasterUserResolver getEasterUserResolver() {
        return easterUserResolver;
    }

}
