package com.deethzzcoder.deetheastereggs.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public abstract class AbstractSubCommand {

    private final String name;
    private final String permission;
    private final List<String> aliases;
    private final boolean console;
    private final int minArgs;
    private final int maxArgs;

    protected AbstractSubCommand(String name, String permission, List<String> aliases, boolean console, int minArgs, int maxArgs) {
        this.name = name;
        this.permission = permission;
        this.aliases = aliases;
        this.console = console;
        this.minArgs = minArgs;
        this.maxArgs = maxArgs == -1 ? Integer.MAX_VALUE : maxArgs;
    }

    protected AbstractSubCommand(String name, List<String> aliases, boolean console, int minArgs, int maxArgs) {
        this(name, name, aliases, console, minArgs, maxArgs);
    }

    protected AbstractSubCommand(String name,  boolean console, int minArgs, int maxArgs) {
        this(name, name, new ArrayList<>(), console, minArgs, maxArgs);
    }

    public final String getName() {
        return name;
    }
    
    public final String getPermission() {
        return permission;
    }
    
    public final List<String> getAliases() {
        return aliases;
    }

    public final boolean isConsole() {
        return console;
    }

    public final int getMinArgs() {
        return minArgs;
    }

    public final int getMaxArgs() {
        return maxArgs;
    }

    protected abstract void execute(CommandSender sender, String[] args);

}
