package com.salimatech.zazie.service;

import com.salimatech.zazie.model.json.DeadlockMonitor;

public interface ThreadService {

    /**
     * Creates 2 separate threads that share the same resources: lock1 and lock2
     * The order of accessing the resources is such that it will likely create a deadlock scenario
     * between the 2 thread.
     */
    void createDeadlock();

    /**
     * Detect if a deadlock scenario and advise
     * If a deadlock scenario is found then the list of thread stated will be
     * included in the returned monitor
     *
     * @return deadlock findings
     */
    DeadlockMonitor findDeadlock();
}
