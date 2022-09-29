package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final FileProperty fileProperty;

    public DuplicatesVisitor(FileProperty fileProperty) {
        this.fileProperty = fileProperty;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (fileProperty.equals(new FileProperty(Files.size(file), file.getFileName().toString()))) {
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}