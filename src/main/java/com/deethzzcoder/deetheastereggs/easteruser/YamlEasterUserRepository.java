package com.deethzzcoder.deetheastereggs.easteruser;

import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class YamlEasterUserRepository implements EasterUserRepository {

    private final boolean save;
    private EasterEggResolver easterEggResolver;
    private FileConfiguration configuration;
    private File currentFile;

    YamlEasterUserRepository(File folder, EasterEggResolver easterEggResolver, boolean save) {
        this.save = save;
        if(!save) return;
        this.easterEggResolver = easterEggResolver;
        this.currentFile = new File(folder, "storage.yml");
    }

    private void initConfiguration() throws IOException {
        if(!currentFile.exists()) currentFile.createNewFile();
        configuration = YamlConfiguration.loadConfiguration(currentFile);
    }

    @Override
    public EasterUserStorage load() throws IOException {
        if(!save) return new EasterUserStorage();
        this.initConfiguration();
        if(!configuration.isConfigurationSection("easter-users")) return new EasterUserStorage();
        Set<EasterUser> easterUsers = new HashSet<>();
        configuration.getConfigurationSection("easter-users").getKeys(false).stream().forEach(uuidString -> {
            String path = "easter-users." + uuidString + ".";
            UUID uuid = UUID.fromString(uuidString);
            Set<EasterEgg> easterEggs = new HashSet<>();
            configuration.getStringList(path + "easter-eggs").stream().forEach(eggName -> easterEggs.add(easterEggResolver.findEasterEggByName(eggName)));
            easterUsers.add(new EasterUserImpl(uuid, easterEggs));
        });
        return new EasterUserStorage(easterUsers);
    }

    @Override
    public void save(EasterUserStorage easterUserStorage) throws IOException {
        if(!save) return;
        this.initConfiguration();
        configuration.set("easter-users", null);
        easterUserStorage.getEasterUsers().stream().forEach(easterUser -> {
            String path = "easter-users." + easterUser.getUuid().toString() + ".";
            List<String> easterEggs = new ArrayList<>();
            easterUser.getEasterEggs().stream().forEach(easterEgg -> easterEggs.add(easterEgg.getName()));
            configuration.set(path + "easter-eggs", easterEggs);
        });
        configuration.save(currentFile);
    }

}
