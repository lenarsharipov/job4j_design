package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private final List<Path> paths = new ArrayList<>();
    private final PathMatcher matcher;

    public SearchFiles(String pattern, String type) {
        if ("mask".equals(type) || "name".equals(type)) {
            matcher = FileSystems.getDefault().getPathMatcher(String.format("glob:%s", pattern));
        } else {
            matcher = FileSystems.getDefault().getPathMatcher(String.format("regex:%s", pattern));
        }
    }

    public List<Path> getPaths() {
        return paths;
    }

    void find(Path file) {
        Path name = file.getFileName();
        if (matcher.matches(name)) {
            paths.add(file);
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file);
        return super.visitFile(file, attrs);
    }
}