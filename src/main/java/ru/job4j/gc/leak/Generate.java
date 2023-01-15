package ru.job4j.gc.leak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Интерфейс генератора (чтение не будем реализовывать отдельно для экономии кода в задании,
 * поэтому реализуем как дефолтный метод в Generate).
 */
public interface Generate  {

    void generate();

    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        try (Stream<String> s = Files.lines(Paths.get(path))) {
            s.forEach(text::add);
            return text;
        }
    }
}