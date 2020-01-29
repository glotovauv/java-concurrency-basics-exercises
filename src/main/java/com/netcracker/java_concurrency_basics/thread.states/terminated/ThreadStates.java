package com.netcracker.java_concurrency_basics.thread.states.terminated;

public class ThreadStates {
    public static void main(String[] args) throws Exception {
        Runnable r = new Runnable() {
            @Override
            public void run() {
            }
        };

        Thread t = new Thread(r);

        t.start();
        t.join();

        System.out.println(t.getState());
    }
}
