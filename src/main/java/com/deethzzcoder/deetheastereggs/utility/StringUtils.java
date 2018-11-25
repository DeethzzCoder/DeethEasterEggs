package com.deethzzcoder.deetheastereggs.utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class StringUtils {

    private StringUtils() {

    }

    public static String merge(List<Object> input, String symbol) {
        StringBuilder builder = new StringBuilder();
        input.stream().forEach(inputString -> builder.append(input.indexOf(inputString) == 0 ? inputString : symbol + inputString));
        return builder.toString();
    }

    public static String merge(Object[] input, String symbol) {
        return merge(Arrays.asList(input), symbol);
    }

    public static String mergeStrings(List<String> input, String symbol) {
        return merge(input.stream().map(inputString -> (Object) inputString).collect(Collectors.toList()), symbol);
    }

}
