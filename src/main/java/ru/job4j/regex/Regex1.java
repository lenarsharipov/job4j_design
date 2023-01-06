package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex1  {
    public static void main(String[] args) {
        String s = "Ivanov Vasiliy, Russia, Moscow, Lenin street, 51, Flat 48,"
                + " email: vivanov@mail.ru, Postcode: AA99, PhoneNumber: +123456789;"
                + "Petrova Mariya, Ukraine, Kiev, Lomonosov street, 33, Flat 18,"
                + " email: masha@yandex.ru. Postcode: UKR54, Phone Number: +987654321;"
                + "Chuck Norris, USA, Hollywood, All stars street, 87, Flat 21,"
                + " email: chuck@gmail.com, Postcode: USA23, Phone Number: +136478952";

        Pattern pattern1 = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+,");
        Matcher matcher1 = pattern1.matcher(s);
        while (matcher1.find()) {
            System.out.println("position " + matcher1.start() + " : " + matcher1.group());
        }

        System.out.println("-".repeat(100));
        System.out.println("Номера домов и квартир");
        Pattern pattern2 = Pattern.compile("\\b\\d{2}\\b");
        Matcher matcher2 = pattern2.matcher(s);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }

        System.out.println("-".repeat(100));
        System.out.println("Телефоны:");
        Pattern pattern3 = Pattern.compile("\\+\\d{9}\\b");
        Matcher matcher3 = pattern3.matcher(s);
        while (matcher3.find()) {
            System.out.println(matcher3.group());
        }

        System.out.println("-".repeat(100));
        System.out.println("emails:");
        Pattern pattern4 = Pattern.compile("\\b\\w+@\\w+\\.\\w+\\b");
        Matcher matcher4 = pattern4.matcher(s);
        while (matcher4.find()) {
            System.out.println(matcher4.group());
        }
    }

}
