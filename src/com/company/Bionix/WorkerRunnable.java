package com.company.Bionix;

import java.util.concurrent.CountDownLatch;

public class WorkerRunnable implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final Point sharedPoint;

    public WorkerRunnable(CountDownLatch startSignal,
                          CountDownLatch doneSignal,
                          Point sharedPoint) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.sharedPoint = sharedPoint;
    }

    @Override
    public void run() {
//        System.out.println("Preparing...");

        try {
            startSignal.await();
//            System.out.println("Last chance....");
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
//            System.out.println("Задачу прервали извне");
            return;
        }

//        System.out.println("Executing");
        sharedPoint.move(1, 1);
//        sharedPoint.move2(1, 1);
//        Point.move3(sharedPoint, 1, 1);

//        try {
//            Thread.sleep(1_000 + (long) (Math.random() * 2_000));
//        } catch (InterruptedException e) {
//            System.out.println("Task was interrupted");
//        }

//        System.out.println("Executing has stopped");
        doneSignal.countDown();
    }
}