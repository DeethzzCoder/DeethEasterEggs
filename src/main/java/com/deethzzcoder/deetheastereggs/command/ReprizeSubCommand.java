package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.GeneralConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.MainConfiguration;
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
    private final MainConfiguration mainConfiguration;
    private final EasterEggResolver easterEggResolver;

    ReprizeSubCommand(GeneralConfiguration generalConfiguration, EasterEggResolver easterEggResolver) {
        super("reprize", Arrays.asList("reprise"), true, 2, -1);
        this.languageConfiguration = generalConfiguration.getLanguageConfiguration();
        this.mainConfiguration = generalConfiguration.getMainConfiguration();
        this.easterEggResolver = easterEggResolver;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
        if(easterEgg == null) {
            languageConfiguration.getBuilder("reprize-subcommand.doesnt-exist").replaceEggData(args[0]).build().send(sender);
            return;
        }
        String prize = StringUtils.merge(ArrayUtils.remove(args, 0), " ");
        if(mainConfiguration.getStringList("blocked-egg-prizes").contains(prize)) {
            languageConfiguration.getBuilder("reprize-subcommand.blocked-prize").replaceEggData(prize).build().send(sender);
            return;
        }
        String oldPrize = easterEgg.getPrize();
        easterEgg.setPrize(prize);
        languageConfiguration.getBuilder("reprize-subcommand.successfully").replaceEggData(easterEgg.getName(), easterEgg.getPrize(), easterEgg.getLocation()).replaceMessage("%easter-egg-old-prize%", oldPrize).build().send(sender);
    }

}
