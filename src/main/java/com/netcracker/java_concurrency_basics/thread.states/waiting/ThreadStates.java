package com.netcracker.java_concurrency_basics.thread.states.waiting;

public class ThreadStates {
    public static void main(String[] args) throws Exception {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                /*Object lock = new Object();
                synchronized(lock) {
                    try {
                        lock.wait();
                    } catch(InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }*/
                try {
                    Thread.currentThread().join(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t = new Thread(r);
        t.setDaemon(true);

        t.start();
        Thread.sleep(10);
        System.out.println(t.getState());
    }
}
