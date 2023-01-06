package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex5 {
    public static void main(String[] args) {
        String string = "Your girlscout cookies are ready to ship. Your total comes to tree fiddy";
        boolean rsl = string.matches(".*(tree fiddy|3.50|three fifty).*");
        System.out.println(rsl);

        Pattern pattern = Pattern.compile("(tree fiddy|3.50|three fifty)");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }
}
