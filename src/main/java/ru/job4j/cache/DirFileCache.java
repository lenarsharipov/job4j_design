package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    /**
     * cachingDir - путь кэшируемой директории
     */
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Если в кеше файла нет. Кеш должен загрузить себе данные
     */
    @Override
    protected String load(String key) {
        String out = null;
        try {
            out = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}