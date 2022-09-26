package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int tmp = Integer.parseInt(line);
                if (tmp % 2 == 0) {
                    System.out.println(tmp + " is even number");
                } else {
                    System.out.println(tmp + " is not even number");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
