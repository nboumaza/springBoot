package com.salimatech.zazie.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FibonacciController.class, secure = false)
public class FibonacciControllerTest {


    long[] expected;

    @Before
    public void setup() {
        expected = new long[]{0, 1, 1};
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fibonacciRecursive() {

    }

}