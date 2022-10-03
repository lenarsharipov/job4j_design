package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean isOut = true;
        boolean isStop = false;
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        while (isOut) {
            String input = scanner.nextLine();
            log.add(String.format("User: %s", input));

            if (STOP.equals(input)) {
                isStop = true;
                log.add("*** System: BOT STOPPED ***" + System.lineSeparator());
            }
            if (CONTINUE.equals(input)) {
                isStop = false;
                log.add("*** System: BOT CONTINUED ***" + System.lineSeparator());
            }
            if (OUT.equals(input)) {
                isOut = false;
                log.add("*** System: CONSOLE CHAT CLOSED ***");
            }
            if (!isStop && isOut) {
                String answer = phrases.get(new Random().nextInt(phrases.size() - 1));
                log.add(String.format("Bot: %s", answer));
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/bot_answers.txt");
        cc.run();
    }
}