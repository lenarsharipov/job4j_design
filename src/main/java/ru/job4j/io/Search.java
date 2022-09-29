package ru.job4j.io;

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
        if (!validate(args)) {
            throw new IllegalArgumentException("Parameters are not valid");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean validate(String[] args) {
        return !args[0].isBlank() && !args[1].isBlank() && args[0].startsWith(".") && args[1].startsWith(".") && args[1].length() != 1;
    }
}
