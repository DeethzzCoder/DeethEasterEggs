package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class RenameSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final EasterEggResolver easterEggResolver;

    RenameSubCommand(LanguageConfiguration languageConfiguration, EasterEggResolver easterEggResolver) {
        super("rename", Arrays.asList("setname"), true, 2, 2);
        this.languageConfiguration = languageConfiguration;
        this.easterEggResolver = easterEggResolver;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if(easterEggResolver.findEasterEggByName(args[0]) == null) {
            languageConfiguration.getBuilder("rename-subcommand.doesnt-exist").replaceMessage("%easter-egg-name%", args[0]).build().send(sender);
            return;
        }
        if(easterEggResolver.findEasterEggByName(args[1]) != null) {
            EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[1]);
            languageConfiguration.getBuilder("rename-subcommand.already-exists-name").replaceMessage("%easter-egg-name%", easterEgg.getName()).replaceMessage("%easter-egg-prize%", easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage()).replaceMessage("%easter-egg-world%", easterEgg.getLocation().getWorld().getName()).replaceMessage("%easter-egg-x%", String.valueOf(easterEgg.getLocation().getX())).replaceMessage("%easter-egg-y%", String.valueOf(easterEgg.getLocation().getY())).replaceMessage("%easter-egg-z%", String.valueOf(easterEgg.getLocation().getZ())).build().send(sender);
            return;
        }
        EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
        String oldName = easterEgg.getName();
        easterEgg.setName(args[1]);
        languageConfiguration.getBuilder("rename-subcommand.successfully").replaceMessage("%easter-egg-old-name%", oldName).replaceMessage("%easter-egg-name%", easterEgg.getName()).replaceMessage("%easter-egg-prize%", easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage()).replaceMessage("%easter-egg-world%", easterEgg.getLocation().getWorld().getName()).replaceMessage("%easter-egg-x%", String.valueOf(easterEgg.getLocation().getX())).replaceMessage("%easter-egg-y%", String.valueOf(easterEgg.getLocation().getY())).replaceMessage("%easter-egg-z%", String.valueOf(easterEgg.getLocation().getZ())).build().send(sender);
    }

}
