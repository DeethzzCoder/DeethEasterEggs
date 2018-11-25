package com.deethzzcoder.deetheastereggs.utility;

import net.md_5.bungee.api.chat.*;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class TextComponentBuilder {

    private final TextComponent component;

    public TextComponentBuilder(String text) {
        this.component = new TextComponent(ColorUtils.color(text));
    }

    public TextComponentBuilder setClickEvent(ClickEvent event) {
        if(event != null) component.setClickEvent(new ClickEvent(event.getAction(), ColorUtils.color(event.getValue())));
        return this;
    }

    public TextComponentBuilder setHoverEvent(HoverEvent event) {
        if(event != null) component.setHoverEvent(new HoverEvent(event.getAction(), new ComponentBuilder(ColorUtils.color(BaseComponent.toLegacyText(event.getValue()))).create()));
        return this;
    }

    public TextComponent build() {
        return component;
    }

}
