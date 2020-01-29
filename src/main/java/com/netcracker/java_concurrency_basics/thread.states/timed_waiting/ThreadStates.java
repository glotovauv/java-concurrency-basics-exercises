package com.netcracker.java_concurrency_basics.thread.states.timed_waiting;

public class ThreadStates {
    public static void main(String[] args) throws Exception {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    //Thread.sleep(1000);
                    Thread.currentThread().join(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t = new Thread(r);

        t.start();
        Thread.sleep(100);

        System.out.println(t.getState());
    }
}
