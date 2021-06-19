package com.company.Bionix;

public class FibonacciThread extends Thread {
    int maxCountNumber;
    long previousNumber;
    long nextNumber;

    public FibonacciThread(int maxNumber, long previousNumber, long nextNumber) {
        this.maxCountNumber = maxNumber;
        this.previousNumber = previousNumber;
        this.nextNumber = nextNumber;
    }

    @Override
    public void run() {

        System.out.print("Fibonacci Series of " + maxCountNumber + " numbers:");

        for (int i = 1; i <= maxCountNumber; ++i) {
            System.out.print(previousNumber + " ");
            /* On each iteration, we are assigning second number
             * to the first number and assigning the sum of last two
             * numbers to the second number
             */
            long sum = previousNumber + nextNumber;
            previousNumber = nextNumber;
            nextNumber = sum;
        }
    }
}
