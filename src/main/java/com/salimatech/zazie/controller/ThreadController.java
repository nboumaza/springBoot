package com.salimatech.zazie.controller;

import com.salimatech.zazie.model.json.DeadlockMonitor;
import com.salimatech.zazie.service.ThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Threads REST controller
 * <p>
 * 1. Simulates threads that become deadlocked with each other.
 * 2. Provides a function to m detect the deadlock
 * <p>
 * Please refer to the following swagger api for more detail on the available
 * operations and corresponding response status
 * https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/
 */
@RestController
public class ThreadController {

    @Autowired
    private ThreadServiceImpl service;


    /**
     * Creates 2 separate threads that share the same resources: lock1 and lock2
     * The order of accessing the resources is such that it will likely create a deadlock scenario
     * between the 2 thread.
     *
     * @return list of events that resulted in a deadlock scenario
     */
    @GetMapping("/threads/status")
    public DeadlockMonitor retrieveDeadLockStatus() {

        return service.findDeadlock();
    }

    /**
     * Detect if a deadlock scenario and advise
     * If a deadlock scenario is found then the list of thread stated will be
     * included in the returned monitor
     */
    @PostMapping("/threads/deadlock")
    public void createdDeadLock() {

        service.createDeadlock();

    }


}
