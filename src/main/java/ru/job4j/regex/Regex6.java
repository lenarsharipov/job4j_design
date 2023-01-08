package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex6 {
    public static void main(String[] args) {
        String s = "2008-08-08, 2008-08-8, 2012-12-12";
        Pattern pattern = Pattern.compile("\\b\\d\\d(?<magic>\\d\\d)-\\k<magic>-\\k<magic>\\b");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
