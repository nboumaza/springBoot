package com.salimatech.zazie.controller;

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

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class TypicodeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void fetchAllPosts() throws Exception {

        ResultActions result = this.mvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(content().string(notNullValue()));
        //can't rely on the posts text value to always be the same...
    }

    @Test
    public void fetchPostsByExistingUserId() throws Exception {

        ResultActions result = this.mvc.perform(get("/posts?userId=1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(content().string(notNullValue()));
        //can't rely on the posts text value to always be the same...

    }

    @Test
    public void fetchPostsByNonExistingUserId() throws Exception {

        ResultActions result = this.mvc.perform(get("/posts?userId=99999")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(content().string(notNullValue()));
        //can't rely on the posts text value to always be the same...

    }

    @Test
    public void findPostByExistingId() throws Exception {

        ResultActions result = this.mvc.perform(get("/posts/1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(content().string(notNullValue()));
        //can't rely on the post text value to always be the same...

    }

    @Test
    public void findPostByNonExistingId() throws Exception {

        ResultActions result = this.mvc.perform(get("/posts/99999")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
        result.andExpect(content().string(notNullValue()));
        //can't rely on the post text value to always be the same...

    }
}