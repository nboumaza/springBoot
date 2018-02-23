package com.salimatech.zazie.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.management.ManagementFactory.getThreadMXBean;

/**
 * Thread service which provide the folling two functions:
 * 1. create deadlock scenario between two threads
 * 2. Detect the deadlock by providing thread info
 */

@Service
public class ThreadServiceImpl {

    private static Logger logger = LogManager.getLogger(ThreadServiceImpl.class);

    /**
     * @throws TimeoutException
     */
    public void createDeadlock() {


        Object lock1 = "lock1";
        Object lock2 = "lock2";

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    logger.info(Thread.currentThread().getName() + " acquired lock1");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException ignore) {
                    }
                    logger.info(Thread.currentThread().getName() + " waiting on lock2");
                    synchronized (lock2) {
                        logger.info(Thread.currentThread().getName() + " acquired lock2");
                    }
                }
            }

        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    logger.info(Thread.currentThread().getName() + " acquired lock2");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException ignore) {
                    }
                    logger.info(Thread.currentThread().getName() + " waiting on lock1");
                    synchronized (lock1) {
                        logger.info(Thread.currentThread().getName() + " acquired lock1");
                    }
                }
            }

        });


        thread2.start();
        thread1.start();

    }

    /**
     * detect deadlock and log info
     */
    public void findDeadlock() {

        ThreadMonitor monitor = new ThreadMonitor();
        monitor.findDeadlock();

    }


    /**
     * ThreadMonitor object to get thread information in the local VM
     */
    static class ThreadMonitor {
        private static String INDENT = "    ";
        private MBeanServerConnection server;
        private ThreadMXBean tmbean;
        private ObjectName objname;
        private String findDeadlocksMethodName = "findDeadlockedThreads";
        private boolean canDumpLocks = true;

        public ThreadMonitor() {
            this.tmbean = getThreadMXBean();
        }


        /**
         * dump thread info
         */
        private void dumpThreadInfo() {
            long[] tids = tmbean.getAllThreadIds();
            ThreadInfo[] tinfos = tmbean.getThreadInfo(tids, Integer.MAX_VALUE);
            for (ThreadInfo ti : tinfos) {
                logThreadInfo(ti);
            }
        }

        /**
         * log thread state info
         */
        private void logThreadInfo(ThreadInfo ti) {
            StringBuilder sb = new StringBuilder("\"" + ti.getThreadName() + "\"" + " Id="
                    + ti.getThreadId() + " in " + ti.getThreadState());
            if (ti.getLockName() != null) {
                sb.append(" on lock=" + ti.getLockName());
            }
            if (ti.isSuspended()) {
                sb.append(" (suspended)");
            }
            if (ti.isInNative()) {
                sb.append(" (running in native)");
            }
            logger.info(sb.toString());
            if (ti.getLockOwnerName() != null) {
                logger.info(INDENT + " owned by " + ti.getLockOwnerName() + " Id="
                        + ti.getLockOwnerId());
            }
        }


        /**
         * Checks if any threads are deadlocked. If any, print the thread dump
         * information.
         */
        private boolean findDeadlock() {
            long[] tids;
            if (findDeadlocksMethodName.equals("findDeadlockedThreads")
                    && tmbean.isSynchronizerUsageSupported()) {
                tids = tmbean.findDeadlockedThreads();
                if (tids == null) {
                    return false;
                }

                logger.info("Deadlock found :-");
                ThreadInfo[] infos = tmbean.getThreadInfo(tids, true, true);
                for (ThreadInfo ti : infos) {
                    logThreadInfo(ti);


                }
            } else {
                tids = tmbean.findMonitorDeadlockedThreads();
                if (tids == null) {
                    return false;
                }
                ThreadInfo[] infos = tmbean.getThreadInfo(tids, Integer.MAX_VALUE);
                for (ThreadInfo ti : infos) {
                    // print thread information
                    logThreadInfo(ti);
                }
            }

            return true;
        }


    }


}





