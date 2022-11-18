package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path p : sources) {
                zip.putNextEntry(new ZipEntry(p.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(p.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private void validate(ArgsName argsName) {
        if (argsName.get("d") == null || argsName.get("e") == null || argsName.get("o") == null) {
            throw new IllegalArgumentException("Passed arguments are illegal.");
        }

        if (!Paths.get(argsName.get("d")).toFile().exists()) {
            throw new IllegalArgumentException("Passed directory - does not exist.");
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        ArgsName argsName = ArgsName.of(args);
        zip.validate(argsName);
        Path directory = Paths.get(ArgsName.of(args).get("d"));
        Path exclude = Paths.get(ArgsName.of(args).get("e"));
        File output = Paths.get(ArgsName.of(args).get("o")).toFile();
        Predicate<Path> condition = p -> !p.toFile().getName().endsWith(exclude.toString());
        List<Path> sources = Search.search(directory, condition);
        zip.packFiles(sources, output);
    }
}