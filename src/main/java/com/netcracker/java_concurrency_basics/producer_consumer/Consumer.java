package com.netcracker.java_concurrency_basics.producer_consumer;

class Consumer {
    private final Queue<String> queue;
    private final int id;
    private final Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            doJob();
        }
    });

    Consumer(Queue<String> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    private void doJob() {
        while (true) {
            try {
                String msg = queue.take();
                System.out.println("CONS" + id + " received message: " + msg);
                //Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void start() {
        t.start();
    }
}
