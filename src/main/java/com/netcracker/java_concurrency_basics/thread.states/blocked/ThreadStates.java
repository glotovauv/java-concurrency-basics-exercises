package com.netcracker.java_concurrency_basics.thread.states.blocked;

public class ThreadStates {
    public static void main(String[] args) throws Exception {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized(ThreadStates.class) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }
            }
        };

        Thread t = new Thread(r);
        Thread t2 = new Thread(r);

        t2.start();
        Thread.sleep(100);

        t.start();
        Thread.sleep(10);

        System.out.println(t.getState());
    }
}
