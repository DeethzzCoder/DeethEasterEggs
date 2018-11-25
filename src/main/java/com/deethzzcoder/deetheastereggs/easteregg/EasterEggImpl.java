package com.deethzzcoder.deetheastereggs.easteregg;

import org.bukkit.Location;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class EasterEggImpl implements EasterEgg {

    private String name, prize;
    private Location location;

    EasterEggImpl(String name, String prize, Location location) {
        this.name = name;
        this.prize = prize;
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPrize() {
        return prize;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrize(String prize) {
        this.prize = prize;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean hasPrize() {
        return !prize.equals("");
    }

}
