package com.salimatech.zazie.service;

import com.salimatech.zazie.model.json.DeadlockMonitor;
import com.salimatech.zazie.model.json.ThreadEvent;
import com.salimatech.zazie.model.json.ThreadStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.management.ManagementFactory.getThreadMXBean;

/**
 * Thread service which provide the folling two functions:
 * 1. create deadlock scenario between two threads
 * 2. Detect the deadlock by providing thread info
 */

@Service
public class ThreadServiceImpl implements ThreadService {

    private static Logger logger = LogManager.getLogger(ThreadServiceImpl.class);


    /**
     *  Creates 2 separate threads that share the same resources: lock1 and lock2
     *  The order of accessing the resources is such that it will likely create a deadlock scenario
     *  between the 2 thread.
     *
     */
    @Override
    public void createDeadlock() {


        Object lock1 = "lock1";
        Object lock2 = "lock2";


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (lock1) {

                    ThreadEvent te1 = new ThreadEvent(Instant.now(),
                            Thread.currentThread().getName(), "acquired lock1");
                    logger.info(te1.toString());


                    //simulate processing
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException ignore) {
                    }
                    //completed processing
                    ThreadEvent te2 = new ThreadEvent(Instant.now(),
                            Thread.currentThread().getName(), "waiting on lock2");

                    logger.info(te2.toString());

                    synchronized (lock2) {
                        ThreadEvent te3 = new ThreadEvent(Instant.now(),
                                Thread.currentThread().getName(), "acquired lock2");

                        logger.info(te3.toString());

                    }
                }
            }

        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {

                    ThreadEvent te1 = new ThreadEvent(Instant.now(),
                            Thread.currentThread().getName(), "acquired lock2");
                    logger.info(te1.toString());


                    //simulate processing
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException ignore) {
                    }
                    //completed processing
                    ThreadEvent te2 = new ThreadEvent(Instant.now(),
                            Thread.currentThread().getName(), "waiting on lock1");

                    logger.info(te2.toString());


                    synchronized (lock1) {
                        ThreadEvent te3 = new ThreadEvent(Instant.now(),
                                Thread.currentThread().getName(), "acquired lock1");

                        logger.info(te3.toString());
                    }
                }
            }

        });


        thread2.start();
        thread1.start();

    }

    /**
     * Detect if a deadlock scenario and advise
     * If a deadlock scenario is found then the list of thread stated will be
     * included in the returned monitor
     * @return deadlock findings
     */
    @Override
    public DeadlockMonitor findDeadlock() {

        List<ThreadStatus> threadStates = new ArrayList<>();
        DeadlockMonitor deadLockMonitor = new DeadlockMonitor(threadStates);

        ThreadMonitor monitor = new ThreadMonitor();
        monitor.findDeadlock(deadLockMonitor);

        return deadLockMonitor;

    }


    /**
     * ThreadMonitor object to get thread information in the local VM
     */
    class ThreadMonitor {

        private ThreadMXBean tmbean;

        public ThreadMonitor() {
            this.tmbean = getThreadMXBean();
        }

        /**
         * adds a blocked thread's state info
         */
        private ThreadStatus getThreadStatus(ThreadInfo ti) {
            ThreadStatus ts = new ThreadStatus();

            ts.setThreadName(ti.getThreadName());
            ts.setThreadState(ti.getThreadState().name());

            if (ti.getLockName() != null) {

                ts.setLockName(String.valueOf(ti.getLockInfo().getIdentityHashCode()));
            }
            if (ti.isSuspended()) {
                ts.setSuspended(true);
            }
            if (ti.isInNative()) {
                ts.setNative(true);

            }

            if (ti.getLockOwnerName() != null) {

                ts.setLockOwnerName(ti.getLockOwnerName());
                ts.setLockName(ti.getLockOwnerName());
            }
            return ts;

        }


        /**
         * Checks if any threads are deadlocked. If any, collect the thread dump
         * information.
         */
        private void findDeadlock( DeadlockMonitor deadLockMonitor) {
            long[] tids;
            if (tmbean.isSynchronizerUsageSupported()) {
                tids = tmbean.findDeadlockedThreads();

                if (tids == null) {
                    return;
                }
                deadLockMonitor.setDeadlockFound(Boolean.TRUE);
                logger.info(">>>> Deadlock found !");
                ThreadInfo[] infos = tmbean.getThreadInfo(tids, true, true);
                collectThreadStatus(deadLockMonitor, infos);


            } else {
                tids = tmbean.findMonitorDeadlockedThreads();
                if (tids == null) {
                    return;
                }
                deadLockMonitor.setDeadlockFound(Boolean.TRUE);
                ThreadInfo[] infos = tmbean.getThreadInfo(tids, Integer.MAX_VALUE);
                collectThreadStatus(deadLockMonitor, infos);


            }

        }

        /**
         *  collect thread info
         */
        private void collectThreadStatus(DeadlockMonitor deadLockMonitor, ThreadInfo[] infos){

            for (ThreadInfo ti : infos) {
                //TODO make below add thread safe
                deadLockMonitor.getDeadlockInfo().add(getThreadStatus(ti));
            }
        }

    }


}





