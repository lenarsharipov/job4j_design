package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {
    public static void main(String[] args) {
        var bytes = new byte[] {'J', 'a', 'v', 'a'};
        var stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();

        var str = "123456789";
        var bytes1 = str.getBytes();
        var byteStream2 = new ByteArrayInputStream(bytes1, 3, 4);
        int data1;
        while ((data1 = byteStream2.read()) != -1) {
            System.out.print((char) data1);
        }
        System.out.println();

        var outStream = new ByteArrayOutputStream();
        var bytes2 = "Message".getBytes();
        outStream.writeBytes(bytes2);
        System.out.println(outStream);
        var byteArray = outStream.toByteArray();
        try (var fileOutput = new FileOutputStream("data/message.txt")) {
            outStream.writeTo(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
