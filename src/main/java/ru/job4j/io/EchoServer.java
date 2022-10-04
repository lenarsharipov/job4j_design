package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String[] arr = new String[3];
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.startsWith("GET") && str.endsWith("HTTP/1.1")) {
                            arr[2] = str.split(" ")[1];
                            arr[0] = "http://";
                        }
                        if (str.startsWith("Host:")) {
                            arr[1] = str.split(" ")[1];
                        }
                    }
                    out.flush();
                    if ("http://localhost:9000/?msg=Bye".equals(String.join("", arr))) {
                        server.close();
                    }
                }
            }
        }
    }
}