package org.example.date_230208;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class ThreadLocalUtil {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void set(String value) {
        threadLocal.set(value);
    }

    public static String get() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }

}

class ValidatingValue {
    private static String valueOfFirstThread;
    private static String valueOfSecondThread;
    private static String valueOfThirdThread;

    public static void setValueOfFirstThread(String value) {
        valueOfFirstThread = value;
    }

    public static void setValueOfSecondThread(String value) {
        valueOfSecondThread = value;
    }

    public static void setValueOfThirdThread(String value) {
        valueOfThirdThread = value;
    }

    public static String getValueOfFirstThread() {
        return valueOfFirstThread;
    }

    public static String getValueOfSecondThread() {
        return valueOfSecondThread;
    }

    public static String getValueOfThirdThread() {
        return valueOfThirdThread;
    }
}

public class ThreadTest {

    @Test
    public void threadLocalTest() throws InterruptedException {
        final String[] values = {"apple", "banana", "melon"};

        IntStream.rangeClosed(1, 3)
                .forEach(iter -> {
                    Thread thread = new Thread(new ThreadInfoCollector(iter, values[iter - 1]));
                    thread.start();
                });

        Thread.sleep(1000);

        Assertions.assertEquals(values[0], ValidatingValue.getValueOfFirstThread());
        Assertions.assertEquals(values[1], ValidatingValue.getValueOfSecondThread());
        Assertions.assertEquals(values[2], ValidatingValue.getValueOfThirdThread());
    }

}

class ThreadInfoCollector implements Runnable {
    private final int threadNumber;
    private final String threadLocalValue;

    public ThreadInfoCollector(int threadNumber, String threadLocalValue) {
        this.threadNumber = threadNumber;
        this.threadLocalValue = threadLocalValue;
    }

    @Override
    public void run() {
        ThreadLocalUtil.set(String.format("Hello, I'm [%d] thread. and i take [%s]. ", threadNumber, threadLocalValue));
        System.out.println(ThreadLocalUtil.get());

        updateValueByThreadNumber(threadNumber, threadLocalValue);
        ThreadLocalUtil.clear();
    }

    public void updateValueByThreadNumber(int threadNumber, String value) {
        if(threadNumber == 1)  ValidatingValue.setValueOfFirstThread(value);
        if(threadNumber == 2)  ValidatingValue.setValueOfSecondThread(value);
        if(threadNumber == 3)  ValidatingValue.setValueOfThirdThread(value);
    }

}