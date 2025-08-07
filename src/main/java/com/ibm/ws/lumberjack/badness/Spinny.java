package com.ibm.ws.lumberjack.badness;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * Spinny servlet runs a particular number of threads (specified in the URL
 * field) where after each thread that is created, a delay will occur (also
 * specified in the URL field)
 */
@WebServlet("/Spinny")
public class Spinny extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static volatile boolean finished = false;

    public Spinny() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Reset the volatile variable
        finished = false;

        // Set variables to default values
        int threadsToRun = 8;
        int delay = 10;
        int finalDelay = 30;

        // Extract the delay, final delay, and number of threads to create -- specified
        // within the URL
        String delayAsString = request.getParameter("delay");
        String threadsAsString = request.getParameter("threads");
        String finalDelayAsString = request.getParameter("finalDelay");

        Logger logger = Logger.getLogger("com.ibm.ws.lumberjack.badness.Spinny");

        // Convert values into Integer format
        try {
            if (delayAsString != null) {
                delay = Integer.valueOf(delayAsString);
            }
            if (threadsAsString != null) {
                threadsToRun = Integer.valueOf(threadsAsString);
            }
            if (finalDelayAsString != null) {
                finalDelay = Integer.valueOf(finalDelayAsString);
            }
        } catch (NumberFormatException e) {
        }

        Thread[] listOfThreads = new Thread[threadsToRun];

        logger.log(Level.INFO, ">>>>> Create threads");

        // Create threads
        for (int i = 0; i < threadsToRun; i++) {
            RunnableTask task = new RunnableTask();
            listOfThreads[i] = new Thread(task);
        }

        logger.log(Level.INFO, ">>>>> Start executing threads..");

        // Run threads
        for (int i = 0; i < threadsToRun; i++) {

            // Simulate delay
            try {
                logger.log(Level.INFO, "Waiting " + delay + " seconds to run Thread-" + (i + 1) + "..");
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            listOfThreads[i].start();
            logger.log(Level.INFO, "Thread-" + (i + 1) + " started.");

        }

        // Allow all the threads to run together for a specified time
        try {
            logger.log(Level.INFO, "Allowing all threads to run for a final " + finalDelay + " seconds.");
            Thread.sleep(finalDelay * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Now break the loop that the threads are trapped in
        finished = true;
        logger.log(Level.INFO, ">>>>> Threads finished");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

class RunnableTask implements Runnable {

    // Force all threads to run within this method
    // Threads will run in this method until the last thread is executed to run
    @Override
    public void run() {
        while (!Spinny.finished) {
            // Stay in this loop until all threads have been executed

            // Run a CPU intensive task -- Compute a large factorial OR the number of prime
            // numbers in [0..n]

            // -- Add method here

        }

    }

    // Computes the factorial of a positive integer n, where n >= 1
    void factorial(int n) {
        BigInteger fact = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++)
            fact = fact.multiply(BigInteger.valueOf(i));
    }

    // Prints the number of primes from [1..n]
    void sieveOfEratosthenes(int n) {

        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n + 1];

        for (int i = 0; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime
            if (prime[p] == true) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true)
                System.out.print(i + " ");
        }
    }

}
