package com.netcracker.java_concurrency_basics.rectangle;

import java.awt.*;
import java.util.Objects;

/**
 * Rectangular area in a coordinate space,
 * specified by a top left point, its width, height and name.
 */
public class RectangularArea {
    private volatile String name;
    private final Point topLeft;
    private int width;
    private int height; // union this variable and return in getter only copy
    private final Object lock = new Object();

    public RectangularArea(String s, Point p, int w, int h) {
        Objects.requireNonNull(p);
        name = s;
        topLeft = new Point(p);
        width = w;
        height = h;
    }

    public int getHeight() {
        synchronized (lock) {
            return height;
        }
    }

    public int getWidth() {
        synchronized (lock) {
            return width;
        }
    }

    public Point getLocation() {
        synchronized (topLeft) {
            return new Point(topLeft);
        }
    }

    public String getName() {
        return name;
    }

    public void resize(int factor) {
        synchronized (lock) {
            width *= factor;
            height *= factor;
        }
    }

    public void translate(Point p) {
        synchronized (topLeft) {
            topLeft.x += p.x;
            topLeft.y += p.y;
        }
    }

    public void rename(String s) {
        name = s;
    }
}