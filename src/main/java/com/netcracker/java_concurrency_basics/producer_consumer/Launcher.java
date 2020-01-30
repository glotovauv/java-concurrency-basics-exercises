package com.netcracker.java_concurrency_basics.producer_consumer;

import java.util.ArrayList;
import java.util.List;

public class Launcher {
    private static final int CONSUMERS_NUM = 1;
    private static final int PRODUCER_NUM = 5;
    private static final int MSG_NUM = 100;

    public static void main(String[] args) throws InterruptedException {
        Queue<String> queue = new Queue<>(300);
        String poisonPill = "interrupt it!";

        List<Consumer> consumers = new ArrayList<>(CONSUMERS_NUM);

        for (int i = 0; i < CONSUMERS_NUM; i++) {
            consumers.add(new Consumer(queue, i, poisonPill, PRODUCER_NUM));
        }

        List<Producer> producers = new ArrayList<>(PRODUCER_NUM);

        for (int i = 0; i < PRODUCER_NUM; i++) {
            // if count of consumers > 1 we need give this count to producer
            // and every producer will send as count of poinsonPill as we have consumers
            producers.add(new Producer(queue, i, MSG_NUM, poisonPill));
        }

        for (Producer producer : producers) {
            producer.start();
        }

        for (Consumer consumer : consumers) {
            consumer.start();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            //consumers.forEach(Consumer::shutdown);
            producers.forEach(Producer::shutdown);

            //finish without waiting shutdown consumers and producers
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("interrupt hook");;
            }
            System.out.println("shutdown hook");
        }));
    }
}
