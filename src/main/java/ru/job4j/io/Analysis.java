package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            StringBuilder text = new StringBuilder();
            boolean flag = true;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if ((line.startsWith("400") || line.startsWith("500")) && flag) {
                    flag = false;
                    text.append(line.split(" ")[1]);
                    continue;
                }
                if ((!line.startsWith("400") && !line.startsWith("500")) && !flag) {
                    flag = true;
                    text.append(";").append(line.split(" ")[1]).append(System.lineSeparator());
                }
            }
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                out.print(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        String source = "./data/server.log";
        String target = "./data/unavailable.csv";
        analysis.unavailable(source, target);

        source = "./data/server2.log";
        target = "./data/unavailable2.csv";
        analysis.unavailable(source, target);

    }
}
