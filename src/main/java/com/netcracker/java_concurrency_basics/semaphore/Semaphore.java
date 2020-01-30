package com.netcracker.java_concurrency_basics.semaphore;

class Semaphore {
    private final int permits;
    private int count = 0;
    private final Object lock = new Object();

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public void acquire() throws InterruptedException {
        synchronized (lock) {
            while (count == permits) {
                lock.wait();
            }
            count++;
            System.out.println("acquire - " + count);
        }
    }

    public void release() {
        synchronized(lock) {
            if (count > 0) {
                count--;
                System.out.println("release - " + count);
                lock.notify();
            }
        }
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

        for (int i = 0; i < 10; i++) {
            new Thread(runnable, "thread-" + i).start();
        }
    }
}