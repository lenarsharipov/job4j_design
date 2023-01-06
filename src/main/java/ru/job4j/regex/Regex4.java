package ru.job4j.regex;

import java.util.regex.*;

public class Regex4 {
    public static void main(String[] args) {
        String string1 = "a1a a2a a3a a4a a5a aba aca";
        Pattern pattern1 = Pattern.compile("\\w\\d\\w");
        Matcher matcher1 = pattern1.matcher(string1);
        while (matcher1.find()) {
            System.out.println(matcher1.group());
        }
        String rsl = string1.replaceAll("\\w\\d\\w", "!");
        System.out.println(rsl);

        String string2 = "a1a a22a a333a a4444a a55555a aba aca";
        Pattern pattern2 = Pattern.compile("a\\d+a");
        Matcher matcher2 = pattern2.matcher(string2);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }

        String string3 = "aa a1a a22a a333a a4444a a55555a aba aca";
        Pattern pattern3 = Pattern.compile("a\\d*a");
        Matcher matcher3 = pattern3.matcher(string3);
        while (matcher3.find()) {
            System.out.println(matcher3.group());
        }

    }
}
