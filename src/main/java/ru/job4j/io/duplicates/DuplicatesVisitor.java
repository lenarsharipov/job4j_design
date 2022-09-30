package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, StringBuilder> files = new HashMap<>();

    public void getDuplicates() {
        for (Map.Entry<FileProperty, StringBuilder> entry : files.entrySet()) {
            if (entry.getValue().toString().split(System.lineSeparator()).length > 1) {
                System.out.printf("%s - %d bytes%n%s%n", entry.getKey().getName(), entry.getKey().getSize(), entry.getValue());
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
            if (files.containsKey(fileProperty)) {
                files.get(fileProperty).append(file.toAbsolutePath()).append(System.lineSeparator());
            } else {
                files.put(fileProperty, new StringBuilder(file.toAbsolutePath().toString()).append(System.lineSeparator()));
            }
        }
        return super.visitFile(file, attrs);
    }
}