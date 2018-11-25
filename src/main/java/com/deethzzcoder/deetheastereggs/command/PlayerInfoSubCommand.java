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
        if(easterUserResolver.findEasterUserByUuid(player.getUniqueId()) == null) {
            languageConfiguration.getBuilder("player-info-subcommand.doesnt-exist").replaceMessage("%easter-user-name%", args[0]).build().send(sender);
            return;
        }
        EasterUser easterUser = easterUserResolver.findEasterUserByUuid(player.getUniqueId());
        List<String> foundEasterEggs =  easterUser.getEasterEggs().stream().map(easterEgg -> easterEgg.getName()).collect(Collectors.toList());
        languageConfiguration.getBuilder("player-info-subcommand.information").replaceMessage("%easter-user-name%", args[0]).replaceMessage("%easter-user-uuid%", player.getUniqueId().toString()).replaceMessage("%easter-user-eggs%", foundEasterEggs.size() != 0 ? StringUtils.mergeStrings(foundEasterEggs, ", ") : languageConfiguration.getMessage("none").getMessage()).build().send(sender);
    }

}
