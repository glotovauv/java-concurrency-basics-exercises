package com.netcracker.java_concurrency_basics.producer_consumer;

class Producer {
    private final Queue<String> queue;
    private final int id;
    private final int msgNum;
    private volatile boolean stopped = false;
    private final String poisonPill;

    private final Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            doJob();
        }
    });

    Producer(Queue<String> queue, int id, int msgNum, String poisonPill) {
        this.queue = queue;
        this.id = id;
        this.msgNum = msgNum;
        this.poisonPill = poisonPill;
    }

    private void doJob() {
        for (int i = 0; i < msgNum; i++) {
            try {
                String msg = getName() + "-" + i;
                if (stopped) {
                    queue.offer(poisonPill);
                    System.out.println(getName() + " stopped!");
                    break;
                }

                queue.offer(msg);
                System.out.println("Sent message: " + msg);
                Thread.sleep(300);

            } catch (InterruptedException e) {
                System.out.println("Interrupt producer " + getName());
            }
        }
    }

    String getName() {
        return "PROD" + id;
    }

    void start() {
       t.start();
    }

    void shutdown() {
        stopped = true;
    }
}
