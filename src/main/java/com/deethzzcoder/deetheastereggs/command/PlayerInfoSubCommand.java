package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.easteruser.EasterUser;
import com.deethzzcoder.deetheastereggs.easteruser.EasterUserResolver;
import com.deethzzcoder.deetheastereggs.utility.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class PlayerInfoSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final EasterUserResolver easterUserResolver;

    PlayerInfoSubCommand(LanguageConfiguration languageConfiguration, EasterUserResolver easterUserResolver) {
        super("player-info", Arrays.asList("p-info"), true, 1, 1);
        this.languageConfiguration = languageConfiguration;
        this.easterUserResolver = easterUserResolver;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        EasterUser easterUser = easterUserResolver.findEasterUserByUuid(player.getUniqueId());
        if(easterUser == null) {
            languageConfiguration.getBuilder("player-info-subcommand.doesnt-exist").replaceUserData(args[0]).build().send(sender);
            return;
        }
        List<String> easterEggs =  easterUser.getEasterEggs().stream().map(easterEgg -> easterEgg.getName()).collect(Collectors.toList());
        languageConfiguration.getBuilder("player-info-subcommand.information").replaceUserData(args[0], player.getUniqueId(), easterEggs.size() != 0 ? StringUtils.mergeStrings(easterEggs, ", ") : languageConfiguration.getMessage("none").getMessage()).build().send(sender);
    }

}
