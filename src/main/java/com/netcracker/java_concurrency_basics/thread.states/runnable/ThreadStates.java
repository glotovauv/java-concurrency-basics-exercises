package com.netcracker.java_concurrency_basics.thread.states.runnable;

import java.util.Scanner;

public class ThreadStates {
    public static void main(String[] args) throws Exception {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Scanner scanner =  new Scanner(System.in);
                scanner.nextLine();
            }
        };

        Thread t = new Thread(r);
        t.setDaemon(true);

        t.start();

        Thread.sleep(10);
        System.out.println(t.getState());
    }
}
