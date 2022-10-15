package ru.job4j.io.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;


public class Search {

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    public static void handle(ArgsName argsName) throws IOException {
        validate(argsName);
        Path root = Paths.get(argsName.get("d"));
        String output = root.resolve(Paths.get(argsName.get("o"))).toFile().toString();
        String type = argsName.get("t");
        writePathsToFile(search(root, argsName.get("n"), type), output);
    }

    private static List<Path> search(Path root, String name, String type) throws IOException {
        SearchFiles searcher = new SearchFiles(name, type);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void writePathsToFile(List<Path> paths, String output) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            StringBuilder sb = new StringBuilder();
            for (Path path : paths) {
                sb.append(path.toAbsolutePath()).append(System.lineSeparator());
            }
            System.out.println(output);
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        if (argsName.get("d") == null
                || argsName.get("n") == null
                || argsName.get("t") == null
                || argsName.get("o") == null) {
            throw new IllegalArgumentException("Passed arguments illegal.");
        }

        if (!Paths.get(argsName.get("d")).toFile().exists()
                || !Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Passed directory does not exist.");
        }
    }

}