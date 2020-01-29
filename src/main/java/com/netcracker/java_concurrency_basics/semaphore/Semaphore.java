package com.netcracker.java_concurrency_basics.semaphore;

import java.util.ArrayList;
import java.util.List;

class Semaphore {
    private final int permits;
    private int count = 0;

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (count == permits) {
            wait();
        }
        count++;
        System.out.println("acquire - " + count);
    }

    public synchronized void release() {
        count--;
        System.out.println("release - " + count);
        notifyAll();
    }

    public static void main(String[] args) {
        // Run 10 threads that use the semaphore.
        // The group of 3 threads should be able to execute the critical section.
        Semaphore semaphore = new Semaphore(3);

        Runnable runnable = () -> {
            try {
                semaphore.acquire();

                System.out.println(Thread.currentThread().getName() + " - Permitted.");

                Thread.sleep(2000);

                semaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        List<Thread> threads = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(runnable, "thread-" + i));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}