package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private static String filter(String[] firstLine,
                                 String[] nextLine,
                                 int size,
                                 String filter,
                                 String delimiter) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < firstLine.length; i++) {
            map.put(firstLine[i], i);
        }
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < size; i++) {
            pattern.append(map.get(filter.split(delimiter)[i]));
        }
        String[] tmp = pattern.toString().split("");
        String[] rsl = new String[size];
        for (int i = 0; i < tmp.length; i++) {
            rsl[i] = nextLine[Integer.parseInt(tmp[i])];
        }
        return String.join(delimiter, rsl);
    }
    public static void handle(ArgsName argsName) {
        validate(argsName);
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter").replaceAll(",", delimiter);
        String out = argsName.get("out");

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
            String[] firstLine = scanner.nextLine().split(delimiter);
            int size = filter.split(delimiter).length;
            List<String> list = new ArrayList<>();
            list.add(filter);
            while (scanner.hasNextLine()) {
                String[] nextLine = scanner.nextLine().split(delimiter);
                list.add(filter(firstLine, nextLine, size, filter, delimiter));
            }
            String rsl = String.join(System.lineSeparator(), list).concat(System.lineSeparator());
            if ("stdout".equals(out)) {
                System.out.println(rsl);
            } else {
                try (PrintWriter pw = new PrintWriter(
                        new FileWriter(out, StandardCharsets.UTF_8, false))) {
                    pw.print(rsl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void validate(ArgsName argsName) {
        if (argsName.get("path") == null
                || argsName.get("delimiter") == null
                || argsName.get("out") == null
                || argsName.get("filter") == null) {
            throw new IllegalArgumentException("Passed arguments are illegal");
        }

        if (!Paths.get(argsName.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("Passed directory - does not exist");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Passed file extension incorrect");
        }

        if (!argsName.get("delimiter").endsWith(";")
            && !argsName.get("delimiter").endsWith(",")) {
            throw new IllegalArgumentException("Passed delimiter incorrect");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}