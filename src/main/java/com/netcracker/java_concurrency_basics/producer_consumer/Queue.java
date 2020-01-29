package com.netcracker.java_concurrency_basics.producer_consumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Queue<E> {
    private List<E> list;
    int maxSize;

    Queue(int msgNum) {
        this.maxSize = msgNum;
        this.list = new LinkedList<>();
    }

    synchronized E take() throws Exception {
        while (list.isEmpty()) {
            wait();
        }

        E e = list.remove(0);

        // if don't have maxSize, notifyAll need delete
        notifyAll();
        return e;
    }

    synchronized void offer(E e) throws Exception {
        while (list.size() == maxSize) {
            wait();
        }

        if(list.isEmpty()) {
            notifyAll();
        }

        list.add(e);
    }
}
