package ru.job4j.gc.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class PhantomTest {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        WeakReference<Object> weak = new WeakReference<>(object);
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantom = new PhantomReference<>(object, queue);

        System.out.println(object);
        System.out.println(weak.get());

        object = null;

        System.gc();
        TimeUnit.SECONDS.sleep(3);

        System.out.println(object);
        System.out.println(weak.get());
        System.out.println(phantom.get());

        System.out.println(queue.poll());

    }
}
