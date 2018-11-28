package com.deethzzcoder.deetheastereggs.listener;

import com.deethzzcoder.deetheastereggs.configuration.GeneralConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.MainConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import com.deethzzcoder.deetheastereggs.easteruser.EasterUser;
import com.deethzzcoder.deetheastereggs.easteruser.EasterUserFactory;
import com.deethzzcoder.deetheastereggs.easteruser.EasterUserResolver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class PlayerListener implements Listener {

    private final LanguageConfiguration languageConfiguration;
    private final MainConfiguration mainConfiguration;
    private final EasterUserResolver easterUserResolver;
    private final EasterUserFactory easterUserFactory;
    private final EasterEggResolver easterEggResolver;

    PlayerListener(GeneralConfiguration generalConfiguration, EasterUserResolver easterUserResolver, EasterUserFactory easterUserFactory, EasterEggResolver easterEggResolver) {
        this.languageConfiguration = generalConfiguration.getLanguageConfiguration();
        this.mainConfiguration = generalConfiguration.getMainConfiguration();
        this.easterEggResolver = easterEggResolver;
        this.easterUserFactory = easterUserFactory;
        this.easterUserResolver = easterUserResolver;
    }

    @EventHandler
    void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(easterUserResolver.findEasterUserByUuid(player.getUniqueId()) != null) return;
        easterUserFactory.makeEasterUser(player.getUniqueId());
    }

    @EventHandler
    void onInteract(PlayerInteractEvent event) {
        if(!event.hasBlock() || event.getHand() == EquipmentSlot.OFF_HAND) return;
        EasterEgg easterEgg = easterEggResolver.findEasterEggByLocation(event.getClickedBlock().getLocation());
        Player player = event.getPlayer();
        EasterUser easterUser = easterUserResolver.findEasterUserByUuid(player.getUniqueId());
        if(easterEgg == null || easterUser.getEasterEggs().contains(easterEgg)) return;
        easterUser.getEasterEggs().add(easterEgg);
        languageConfiguration.getBuilder("found-easteregg.message").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).build().send(player);
        player.sendTitle(languageConfiguration.getBuilder("found-easteregg.title").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).replaceMessage("%name%", player.getName()).build().getMessage(), languageConfiguration.getBuilder("found-easteregg.subtitle").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).replaceMessage("%name%", player.getName()).build().getMessage());
        if(easterEgg.hasPrize()) Bukkit.dispatchCommand(mainConfiguration.getBoolean("handling-server-side") ? Bukkit.getConsoleSender() : player, easterEgg.getPrize().replaceAll("%name%", player.getName()));
    }

}
