package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

        @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = ((result << 5) - result) + Integer.hashCode(children);
        result = ((result << 5) - result) + (birthday == null ? 0 : birthday.hashCode());
        return result;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Ivan", 3, birthday);
        User user2 = new User("Ivan", 3, birthday);

        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;

        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;

        System.out.println("hashcode : " + hashCode1 + " - " + hashCode2);
        System.out.println("hash     : " + hash1 + " - " + hash2);
        System.out.println("bucket   : " + bucket1 + " - " + bucket2);

        System.out.println(user1.equals(user2));

        System.out.println(user1);
        System.out.println(user2);

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}