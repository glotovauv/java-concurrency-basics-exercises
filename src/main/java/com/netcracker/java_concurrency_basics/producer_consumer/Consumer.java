package com.netcracker.java_concurrency_basics.producer_consumer;

class Consumer {
    private final Queue<String> queue;
    private final int id;
    private final String poisonPill;
    private final int countProducers;

    private final Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            doJob();
        }
    });

    Consumer(Queue<String> queue, int id, String poisonPill, int countProducers) {
        this.queue = queue;
        this.id = id;
        this.poisonPill = poisonPill;
        this.countProducers = countProducers;
    }

    private void doJob() {
        int countALiveProducers = 0;
        while (true) {
           /* if(Thread.currentThread().isInterrupted()) {
                System.out.println("CONS" + id + " stopped");
                break;
            }*/
            try {
                String msg = queue.take();
                System.out.println(getName() + " received message: " + msg);

                if(msg == poisonPill) {
                    countALiveProducers++;
                    System.out.println("countALiveProducers = " + countALiveProducers);
                    if(countALiveProducers == countProducers) {
                        System.out.println(getName() + " stopped!");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupt consumer " + getName());
                //t.interrupt();
            }
        }
    }

    String getName() {
        return "CONS" + id;
    }

    void start() {
        t.start();
    }

   /* void shutdown() {
        t.interrupt();
    }*/
}
