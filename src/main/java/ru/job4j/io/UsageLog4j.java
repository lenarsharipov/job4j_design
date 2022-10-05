package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char c = 'a';
        byte b = 1;
        short s = 30_000;
        int i = 2_000_000_000;
        long l = 30_000_000_000L;
        boolean isTrue = false;
        double d = 23.4d;
        float f = 12.1212f;

        LOG.debug("char : {}, byte : {}, short : {}, int : {}, long : {}, boolean : {}, double : {}, float : {}", c, b, s, i, l, isTrue, d, f);
    }
}