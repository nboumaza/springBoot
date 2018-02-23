package com.salimatech.zazie.controller;

import com.salimatech.zazie.service.ThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Threads REST controller
 *
 * 1. Simulates threads that become deadlocked with each other.
 * 2. Provides a function to m detect the deadlock
 *
 * Please refer to the following swagger api for more detail on the available
 * operations and corresponding response status
 * https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/
 */
@RestController
public class ThreadController {

    @Autowired
    private ThreadServiceImpl service;

    /**
     * Iteration 1: log deadlock info
     * Iteration 2: return json status
     * retrieve threads status
     */
    @GetMapping("/threads/status")
    public void retrieveDeadLockStatus() {

        service.findDeadlock();
    }


    /**
     * setup up deadlock scenario between two threads
     *
     */
    @PostMapping("/threads/deadlock")
    public void createdDeadLock()  {

        service.createDeadlock();

    }


}
