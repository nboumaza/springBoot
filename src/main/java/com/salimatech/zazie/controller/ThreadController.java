package com.salimatech.zazie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.TimeUnit;

public class ThreadController {

    @GetMapping("/deadlockInfo")
    public  String retrieveDeadLockStatus() {
        return "";
    }


    @PostMapping("/createDeadlock")
    public  String createdDeadLockStatus() {
        return "";
    }



    private static void test1() {
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override public void run() {
                synchronized (lock1) {
                    System.out.println("Thread1 acquired lock1");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException ignore) {}
                    synchronized (lock2) {
                        System.out.println("Thread1 acquired lock2");
                    }
                }
            }

        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override public void run() {
                synchronized (lock2) {
                    System.out.println("Thread2 acquired lock2");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException ignore) {}
                    synchronized (lock1) {
                        System.out.println("Thread2 acquired lock1");
                    }
                }
            }
        });
        thread2.start();

        // Wait a little for threads to deadlock.
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ignore) {}
    }

}
