package com.company.Bionix;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run() throws InterruptedException {
        final int workersCount = 2_000;

        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(workersCount);
        Point sharedPoint = new Point();

        for (int i = 0; i < workersCount; i++) {
            executor.execute(new WorkerRunnable(
                    startSignal, doneSignal, sharedPoint));
        }

        Scanner scanner = new Scanner(System.in);
        int userCode = scanner.nextInt();
        if (userCode == 7) {
            // дать стартовую команду
            startSignal.countDown();
            executor.shutdown();

            System.out.println("Ожидаем завершения рабочих...");
            doneSignal.await();
        } else {
            System.out.println("неверная команда, выходим");
            executor.shutdownNow();
        }
        System.out.println("Все, выходим");
        System.out.println("x: " + sharedPoint.getX());
        System.out.println("y: " + sharedPoint.getY());


        FibonacciRunnable fibonacciRunnable = new FibonacciRunnable(10, 2, 3);
        Thread thread = new Thread(fibonacciRunnable);
        thread.start();
        thread.interrupt();
        thread.join(10000);
        System.out.println();

        ExecutorService executorServiceFib = Executors.newSingleThreadExecutor();
        FibonacciRunnable fibWithExecutor = new FibonacciRunnable(10, 2, 3);
        Future<?> promiseFib = executorServiceFib.submit(fibWithExecutor);
//        try {
//            promiseFib.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println();


        FibonacciThread fibonacciThread = new FibonacciThread(10, 2, 3);
        Thread thread1 = new Thread(fibonacciThread);
        thread1.start();
    }
}