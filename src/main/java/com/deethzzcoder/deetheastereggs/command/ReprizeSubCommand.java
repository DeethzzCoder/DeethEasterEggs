package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import com.deethzzcoder.deetheastereggs.utility.StringUtils;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class ReprizeSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final EasterEggResolver easterEggResolver;

    ReprizeSubCommand(LanguageConfiguration languageConfiguration, EasterEggResolver easterEggResolver) {
        super("reprize", Arrays.asList("reprise"), true, 2, -1);
        this.languageConfiguration = languageConfiguration;
        this.easterEggResolver = easterEggResolver;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if(easterEggResolver.findEasterEggByName(args[0]) == null) {
            languageConfiguration.getBuilder("reprize-subcommand.doesnt-exist").replaceMessage("%easter-egg-name%", args[0]).build().send(sender);
            return;
        }
        EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
        easterEgg.setPrize(StringUtils.merge(ArrayUtils.remove(args, 0), " "));
        languageConfiguration.getBuilder("reprize-subcommand.successfully").replaceMessage("%easter-egg-name%", easterEgg.getName()).replaceMessage("%easter-egg-prize%", easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage()).replaceMessage("%easter-egg-world%", easterEgg.getLocation().getWorld().getName()).replaceMessage("%easter-egg-x%", String.valueOf(easterEgg.getLocation().getX())).replaceMessage("%easter-egg-y%", String.valueOf(easterEgg.getLocation().getY())).replaceMessage("%easter-egg-z%", String.valueOf(easterEgg.getLocation().getZ())).build().send(sender);
    }

}
