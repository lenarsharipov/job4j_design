package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        FileProperty fileProperty = new FileProperty(249, "README.md");
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor(fileProperty));
    }
}