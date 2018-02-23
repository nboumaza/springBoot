package com.salimatech.zazie.controller;

import com.salimatech.zazie.service.ThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@RestController
public class ThreadController {

    @Autowired
    private ThreadServiceImpl service;

    @GetMapping("/threads/status")
    public  void retrieveDeadLockStatus() throws TimeoutException {
        try {
            service.createDeadlock();
        } catch (TimeoutException e) {
            throw new TimeoutException();
        }
    }


    @PostMapping("/threads/deadlock")
    public  String createdDeadLock() {
        return "";
    }



}
