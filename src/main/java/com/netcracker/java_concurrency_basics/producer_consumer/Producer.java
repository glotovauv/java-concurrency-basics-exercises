package com.netcracker.java_concurrency_basics.producer_consumer;

import java.util.Random;

class Producer {
    private final Queue<String> queue;
    private final int id;
    private final int msgNum;
    private final Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            doJob();
        }
    });

    Producer(Queue<String> queue, int id, int msgNum) {
        this.queue = queue;
        this.id = id;
        this.msgNum = msgNum;
    }

    private void doJob() {
        for (int i = 0; i < msgNum; i++) {
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("PROD" + id + " stopped");
                break;
            }
            String msg = "PROD" + id + "-" + i;
            try {
                queue.offer(msg);
                System.out.println("Sent message: " + msg);
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Interrupt producer " + "PROD" + id);
                t.interrupt();
            }
        }
    }

    void start() {
       t.start();
    }

    void shutdown() {
        t.interrupt();
    }
}
