package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex7 {
    public static void main(String[] args) {
        String date1 = "1997/10/10";
        String date2 = "2015/10/10";
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher1 = pattern.matcher(date1);
        Matcher matcher2 = pattern.matcher(date2);
        String rsl1 = "";
        String rsl2 = "";
        while (matcher1.find() && matcher2.find()) {
            rsl1 = matcher1.group();
            rsl2 = matcher2.group();
        }
        int rsl = Math.abs(Integer.parseInt(rsl1) - Integer.parseInt(rsl2));

        System.out.println(rsl);
    }
}
