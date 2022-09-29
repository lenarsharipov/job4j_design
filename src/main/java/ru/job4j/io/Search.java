package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder and file extension are null. Usage  ROOT_FOLDER, FILE_EXTENSION");
        }
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        File dir = new File(args[0]);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s illegal directory", args[0]));
        }

        if (!args[1].startsWith(".") || args[1].length() == 1) {
            throw new IllegalArgumentException(String.format("%s - illegal file extension", args[1]));
        }
    }
}
