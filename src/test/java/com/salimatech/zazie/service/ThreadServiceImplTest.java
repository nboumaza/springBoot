package com.salimatech.zazie.service;

import com.salimatech.zazie.model.json.DeadlockMonitor;
import com.salimatech.zazie.model.json.ThreadStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class ThreadServiceImplTest {

    private DeadlockMonitor dm = null;

    @Autowired
    private ThreadService threadService;

    @Before
    public void setup() {
    }

    @Test
    public void createDeadlock() {

        threadService.createDeadlock();
    }

    @Test
    public void findDeadlock() throws InterruptedException {

        //setup for deadlock scenario
        threadService.createDeadlock();

        TimeUnit.MILLISECONDS.sleep(50);
        dm = threadService.findDeadlock();
        assertNotNull(dm);

        List<ThreadStatus> threadStatus = dm.getDeadlockInfo();
        assertNotNull(threadStatus);


    }

    @TestConfiguration
    static class ThreadServiceImplTestContextConfiguration {

        @Bean
        public ThreadService threadService() {
            return new ThreadServiceImpl();
        }
    }
}