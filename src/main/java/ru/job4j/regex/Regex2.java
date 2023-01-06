package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex2 {
    public static void main(String[] args) {
        String cards = "12345678910112130101333";
        Pattern pattern = Pattern.compile(
                "(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d{2})(\\d{2})(\\d{3})"
        );
        Matcher matcher = pattern.matcher(cards);

        String rsl = matcher.replaceAll("$5/$6 $1 $2 $3 $4 ($7)");
        System.out.println(rsl);

        Pattern pattern2 = Pattern.compile(
                "(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d{2})(\\d{2})(\\d{3})"
        );
        Matcher matcher2 = pattern2.matcher(cards);
        while (matcher2.find()) {
            System.out.println(matcher2.group(7));
        }

    }
}
