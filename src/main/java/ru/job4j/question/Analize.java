package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {

        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }
        int added = 0;
        int changed = 0;
        int deleted = previous.size();
        for (User user : current) {
            if (map.get(user.getId()) != null && map.get(user.getId()).equals(user.getName())) {
                deleted--;
            } else if (map.get(user.getId()) != null
                    && !map.get(user.getId()).equals(user.getName())) {
                changed++;
                deleted--;
            } else {
                added++;
            }
        }
        return new Info(added, changed, deleted);
    }
}
