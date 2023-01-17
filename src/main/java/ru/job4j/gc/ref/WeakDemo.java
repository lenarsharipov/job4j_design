package ru.job4j.gc.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Объекты, на которые ссылаются слабые ссылки, удаляются сразу,
 * если на них нет сильных или безопасных ссылок.
 * Данный тип ссылок служит для реализации структур, для которых
 * у одного значения типа может быть только один объект,
 * например пул строк, и объекты чаще всего используется всего один
 * раз, т.е. сохранили-получили-забыли.
 */
public class WeakDemo {
    public static void main(String[] args) throws InterruptedException {
        example1();
        example2();
        example3();
    }

    /**
     * Инициализается сильной ссылки(object) значением null приводит к удалению объекта
     * и мы его не можем получить по слабой ссылке.
     * (В подобной ситуации, мы могли получить доступ к объекту
     * по безопасной ссылке).
     * @throws InterruptedException
     */
    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        System.out.println(weak.get());
        object = null;
        System.out.println(weak.get());
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    /**
     * Множество объектов создано без сильной ссылки. При вызове сборщика мусора он все удаляются.
     * @throws InterruptedException
     */
    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed!");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Когда на объекте уже нет сильных и безопасных ссылок, слабая ссылка принимает значение null.
     * Далее слабая ссылка будет помещена в очередь ReferenceQueue и мы можем,
     * пока объект не удален, физически получить его из этой очереди.
     */
    private static void example3() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link " + weak.get());
        System.out.println("from queue " + queue.poll());
    }

}