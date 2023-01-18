package ru.job4j.gc.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class RefDemo {

    public static void testSoft() throws InterruptedException {
        System.out.println("*".repeat(100));
        System.out.println("Test Soft Reference");
        Object strong = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        SoftReference<Object> soft = new SoftReference<>(strong);
        System.out.println("strong : " + strong);
        System.out.println("soft : " + soft.get());
        System.out.println("queue : " + queue.poll());
        strong = null;
        System.out.println("strong is null");
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("strong : " + strong);
        System.out.println("soft : " + soft.get());
        System.out.println("queue " + queue.poll());
    }

    public static void testWeak() throws InterruptedException {
        System.out.println("*".repeat(100));
        System.out.println("Test Weak Reference");
        Object strong = new Object();

        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        SoftReference<Object> soft = new SoftReference<>(strong);
        WeakReference<Object> weak = new WeakReference<>(soft.get(), queue);
        System.out.println("strong : " + strong);
        System.out.println("soft : " + soft.get());
        System.out.println("weak : " + weak.get());
        System.out.println("queue : " + queue.poll());
        strong = null;
        System.out.println("strong is null");
        System.out.println("weak : " + weak.get());
        System.out.println("queue : " + queue.poll());
        soft = null;
        System.out.println("soft is null, System.gc(), sleep 3sec");
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("weak : " + weak.get());
        System.out.println("queue : " + queue.poll());
    }

    public static void testPhantom() throws InterruptedException {
        Object strong = new Object();

        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantom = new PhantomReference<>(strong, queue);

        System.out.println("strong : " + strong);
        strong = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);

        System.out.println("queue : " + queue.poll());

    }
    public static void main(String[] args) throws InterruptedException {
        testPhantom();
    }
}