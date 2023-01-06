package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex3 {
    public static void main(String[] args) {
        String string = "aa aba abba abbba abbbba abbbbba";
        Pattern pattern1 = Pattern.compile("ab{2,4}a");
        Matcher matcher1 = pattern1.matcher(string);
        while (matcher1.find()) {
            System.out.println(matcher1.group());
        }

        Pattern pattern2 = Pattern.compile("ab{0,3}a");
        Matcher matcher2 = pattern2.matcher(string);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }

        Pattern pattern3 = Pattern.compile("ab{4,}a");
        Matcher matcher3 = pattern3.matcher(string);
        while (matcher3.find()) {
            System.out.println(matcher3.group());
        }
    }
}
