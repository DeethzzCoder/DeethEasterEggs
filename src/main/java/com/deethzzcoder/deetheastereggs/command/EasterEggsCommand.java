package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.utility.PlayerUtils;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class EasterEggsCommand implements CommandExecutor {

    private final LanguageConfiguration languageConfiguration;
    private final GeneralEasterEggsCommand generalEasterEggsCommand;

    EasterEggsCommand(LanguageConfiguration languageConfiguration, GeneralEasterEggsCommand generalEasterEggsCommand) {
        this.languageConfiguration = languageConfiguration;
        this.generalEasterEggsCommand = generalEasterEggsCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!PlayerUtils.hasPermission(sender, "use")) {
            languageConfiguration.getMessage("no-permission").send(sender);
            return true;
        }
        if(args.length == 0) {
            languageConfiguration.getMessage("help").send(sender);
            return true;
        }
        AbstractSubCommand abstractSubCommand = generalEasterEggsCommand.findSubCommand(args[0].toLowerCase());
        if(abstractSubCommand == null) {
            languageConfiguration.getMessage("subcommand-doesnt-exist").send(sender);
            return true;
        }
        if(!(sender instanceof Player) && !abstractSubCommand.isConsole()) {
            languageConfiguration.getMessage("only-for-players").send(sender);
            return true;
        }
        if(!PlayerUtils.hasPermission(sender, abstractSubCommand.getPermission())) {
            languageConfiguration.getMessage("no-permission").send(sender);
            return true;
        }
        if((args.length - 1) < abstractSubCommand.getMinArgs() || (args.length - 1) > abstractSubCommand.getMaxArgs()) {
            languageConfiguration.getMessage(abstractSubCommand.getName() + "-subcommand.usage").send(sender);
            return true;
        }
        abstractSubCommand.execute(sender, (String[]) ArrayUtils.remove(args, 0));
        return true;
    }

}
