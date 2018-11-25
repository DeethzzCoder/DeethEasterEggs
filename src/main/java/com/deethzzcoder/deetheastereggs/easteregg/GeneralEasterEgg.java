package com.deethzzcoder.deetheastereggs.easteregg;

import com.deethzzcoder.deetheastereggs.DeethEasterEggsPlugin;
import com.deethzzcoder.deetheastereggs.configuration.MainConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.TypeStorage;
import com.deethzzcoder.deetheastereggs.easteregg.exception.EasterEggException;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class GeneralEasterEgg {

    private EasterEggRepository easterEggRepository;
    private EasterEggStorage easterEggStorage;
    private EasterEggFactory easterEggFactory;
    private EasterEggPurifier easterEggPurifier;
    private EasterEggResolver easterEggResolver;

    public GeneralEasterEgg(Plugin plugin, MainConfiguration mainConfiguration) throws IOException {
        if(!(plugin instanceof DeethEasterEggsPlugin)) throw new EasterEggException("This JavaPlugin type don't support!");
        TypeStorage typeStorage = mainConfiguration.getTypeStorage();
        boolean save = mainConfiguration.getBoolean("save");
        if(typeStorage == TypeStorage.YAML) this.easterEggRepository = new YamlEasterEggRepository(plugin.getDataFolder(), save);
        this.easterEggStorage = easterEggRepository.load();
        this.easterEggFactory = new EasterEggFactoryImpl(easterEggStorage);
        this.easterEggPurifier = new EasterEggPurifierImpl(easterEggStorage);
        this.easterEggResolver = new EasterEggResolverImpl(easterEggStorage);
    }

    public EasterEggRepository getEasterEggRepository() {
        return easterEggRepository;
    }

    public EasterEggStorage getEasterEggStorage() {
        return easterEggStorage;
    }

    public EasterEggFactory getEasterEggFactory() {
        return easterEggFactory;
    }

    public EasterEggPurifier getEasterEggPurifier() {
        return easterEggPurifier;
    }

    public EasterEggResolver getEasterEggResolver() {
        return easterEggResolver;
    }

}
