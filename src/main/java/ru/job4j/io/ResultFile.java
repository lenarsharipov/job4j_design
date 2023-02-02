package ru.job4j.io;

import java.io.*;

public class ResultFile {
    public static void main(String[] args) {
        writeBytes();
        writeSymbols();
        bufferedWriteBytes();
        bufferedWriteSymbols();
    }

    private static void writeBytes() {
        try (var out = new FileOutputStream("data/resultWriteBytes.txt")) {
                out.write(("Hello world!" + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeSymbols() {
        try (var out = new FileWriter("data/resultWriteSymbols.txt")) {
                out.write("Hello World" + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferedWriteBytes() {
        try (var out = new BufferedOutputStream(
                new FileOutputStream("data/resultBufferedBytesWriter.txt"))) {
            out.write(("Hello world" + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferedWriteSymbols() {
        try (var out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/resultPrintWriter.txt")))) {
            out.println("Hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}