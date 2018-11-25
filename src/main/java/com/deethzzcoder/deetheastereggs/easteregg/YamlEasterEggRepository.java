package com.deethzzcoder.deetheastereggs.easteregg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class YamlEasterEggRepository implements EasterEggRepository {

    private FileConfiguration configuration;
    private final boolean save;
    private File currentFile;

    YamlEasterEggRepository(File folder, boolean save) {
        this.save = save;
        if(!save) return;
        this.currentFile = new File(folder, "storage.yml");
    }

    private void initConfiguration() throws IOException {
        if(!currentFile.exists()) currentFile.createNewFile();
        configuration = YamlConfiguration.loadConfiguration(currentFile);
    }

    @Override
    public EasterEggStorage load() throws IOException {
        if(!save) return new EasterEggStorage();
        this.initConfiguration();
        if(!configuration.isConfigurationSection("easter-eggs")) return new EasterEggStorage();
        Set<EasterEgg> easterEggs = new HashSet<>();
        configuration.getConfigurationSection("easter-eggs").getKeys(false).stream().forEach(name -> {
            String path = "easter-eggs." + name + ".";
            String pathLocation = path + "location.";
            String prize = configuration.getString(path + "prize") != null ? configuration.getString(path + "prize") : "";
            Location location = new Location(Bukkit.getWorld(configuration.getString(pathLocation + "world")), configuration.getDouble(pathLocation + "x"), configuration.getDouble(pathLocation + "y"), configuration.getDouble(pathLocation + "z"));
            easterEggs.add(new EasterEggImpl(name, prize, location));
        });
        return new EasterEggStorage(easterEggs);
    }

    @Override
    public void save(EasterEggStorage easterEggStorage) throws IOException {
        if(!save) return;
        this.initConfiguration();
        configuration.set("easter-eggs", null);
        easterEggStorage.getEasterEggs().stream().forEach(easterEgg -> {
            String path = "easter-eggs." + easterEgg.getName() + ".";
            String pathLocation = path + "location.";
            if(easterEgg.hasPrize()) configuration.set(path + "prize", easterEgg.getPrize());
            configuration.set(pathLocation + "world", easterEgg.getLocation().getWorld().getName());
            configuration.set(pathLocation + "x", easterEgg.getLocation().getX());
            configuration.set(pathLocation + "y", easterEgg.getLocation().getY());
            configuration.set(pathLocation + "z", easterEgg.getLocation().getZ());
        });
        configuration.save(currentFile);
    }

}
