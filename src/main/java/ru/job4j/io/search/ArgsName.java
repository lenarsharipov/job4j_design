package ru.job4j.io.search;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("%s key does not exist.", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String argument : args) {
            int index = argument.indexOf("=");
            if (index < 2
                    || !argument.startsWith("-")
                    || argument.indexOf("=") == argument.length() - 1) {
                throw new IllegalArgumentException(
                        String.format("Passed argument illegal - %s.", argument)
                );
            }

            values.put(argument.substring(1, index), argument.substring(index + 1));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Passed parameters quantity illegal");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}