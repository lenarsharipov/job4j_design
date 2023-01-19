package ru.job4j.pool;

public class StringPoolExample {
    /**
     * string1 и string2 будут созданы в heap отдельно.
     * string3 будет создан в пуле строк, так как string1 и string2
     * там не создавались, а string4 будет хранить ссылку на уже
     * существующий объект, на который также ссылается string3.
     *
     * Данное сравнение показывает, что в пул строк попали только
     * string3 и string4, а остальные объекты были созданы в куче.
     * Оператор new здесь избыточен, так как создание этих объектов
     * могло бы быть оптимизировано путем создания одного объекта
     * в пуле строк на все 4 переменные, а в данном виде создаются
     * целых 3 одинаковых объекта (2 в куче, 1 в пуле строк).
     */
    public static void main(String[] args) {
        String string1 = new String("Hello");
        String string2 = new String("Hello");
        String string3 = "Hello";
        String string4 = "Hello";
        System.out.println(string1 == string2);
        System.out.println(string3 == string4);
        System.out.println(string1 == string3);
        System.out.println(string2 == string4);

    }
}
