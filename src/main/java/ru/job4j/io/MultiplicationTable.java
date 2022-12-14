package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("./data/Multiplication Table.txt")) {
            String txt;
            for (int i = 1; i < 10; i++) {
                for (int y = 1; y < 10; y++) {
                    txt = y + " * " + i + " = " + i * y;
                    if (y * i > 9) {
                        txt += "    ";
                    } else {
                        txt += "     ";
                    }
                    out.write(txt.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
