package com.salimatech.zazie.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@WebMvcTest(value = WordCountController.class, secure = false)
public class WordCountControllerTest {



    @Before
    public void setup() {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void countWords() {
    }
}