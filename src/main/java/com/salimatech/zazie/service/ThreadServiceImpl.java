package com.salimatech.zazie.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Thread service which provide the folling two functions:
 * 1. create deadlock scenario between two threads
 * 2. Detect the deadlock by providing thread info
 */

@Service
public class ThreadServiceImpl {

    Object lock1 = "lock1";
    Object lock2 = "lock2";

    private static Logger logger = LogManager.getLogger(ThreadServiceImpl.class);

    private final ExecutorService executor = Executors.newFixedThreadPool(1);


    public void createDeadlock() throws TimeoutException {

        Future<?> future = executor.submit(() -> {
                    getRunnable(lock1, lock2).run();
                    getRunnable(lock2, lock1).run();
                }
        );

        try {
            future.get(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error(e.getStackTrace());
        } catch (ExecutionException e) {
            logger.error(e.getStackTrace());
        }

    }


    /**
     *
     */
    private Runnable getRunnable(Object resource1, Object resource2) {

       return (Runnable) () -> {
           logger.info(Thread.currentThread().getName()+" waiting for "+resource1);
           synchronized (resource1) {
               logger.info(Thread.currentThread().getName()+" acquired "+resource1);
               try {
                   // simulate some processing
                   TimeUnit.MILLISECONDS.sleep(50);
               } catch (InterruptedException e) {
                   logger.error(e.getStackTrace());
               }
               logger.info(Thread.currentThread().getName()+" waiting on "+resource2);
               synchronized (resource2) {
                   logger.info(Thread.currentThread().getName()+" acquired "+resource2);
               }

           }
       };
       }


}


