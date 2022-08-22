package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        System.out.println(rsl);

        List<String> rsl2 = new ArrayList<>();
        rsl2.add("four");
        rsl2.add("five");
        rsl2.add(1, "six");
        System.out.println(rsl2);

        rsl.addAll(1, rsl2);
        System.out.println(rsl);

        List<String> rsl3 = List.of("of1", "of2", "of3", "of4", "of5");

        System.out.println("get " + rsl3.get(1));

        Iterator<String> iterator = rsl3.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        ListIterator<String> iterator2 = rsl3.listIterator(5);
        while (iterator2.hasNext()) {
            System.out.println("listIterator " + iterator2.next());
        }

        rsl2.set(1, "four and half");
        System.out.println(rsl2);
        rsl2.replaceAll(String::toUpperCase);
        System.out.println(rsl2);
        rsl2.remove("FOUR");
        System.out.println(rsl2);

        rsl2.removeIf(e -> e.length() < 5);
        System.out.println(rsl2);

    }
}
