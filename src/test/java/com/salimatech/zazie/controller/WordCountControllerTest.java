package com.salimatech.zazie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salimatech.zazie.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class WordCountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void countWordsWithNoBody() throws Exception {

        this.mvc.perform(post("/countWords").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void countWords() throws Exception {

        ResultActions result = this.mvc.perform(post("/countWords")
                .contentType(MediaType.TEXT_PLAIN)
                .content(om.writeValueAsString("ccc!; aaa, ddd; aaa, ddd, aaa")));

        result.andExpect(status().isOk());
        result.andExpect(content().string("{\"aaa\":3,\"ccc\":1,\"ddd\":2}"));
    }


}