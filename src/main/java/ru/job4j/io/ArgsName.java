package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("%s key does not exist", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Parameters are null.");
        }
        for (String argument : args) {
            if (argument == null
                    || !argument.startsWith("-")
                    || argument.length() == 1
                    || !argument.contains("=")
                    || argument.indexOf("=") == argument.length() - 1
                    || argument.indexOf("=") == 1) {
                throw new IllegalArgumentException(String.format("Passed argument illegal - %s.", argument));
            }
            int index = argument.indexOf("=");
            if (argument.charAt(index + 1) == '*') {
                values.put(argument.substring(1, index), argument.substring(index + 2));
            } else {
                values.put(argument.substring(1, index), argument.substring(index + 1));
            }
        }

    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}