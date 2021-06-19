package com.company.Bionix;

public class Point {
    private final Object lock = new Object();

    private int x;
    private int y;

    public synchronized void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void move2(int dx, int dy) {
        synchronized (lock) {
            x += dx;
            y += dy;
        }
    }

    public static synchronized void move3(Point point, int dx, int dy) {
        point.x += dx;
        point.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}