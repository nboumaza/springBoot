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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class ThreadControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void retrieveDeadLockStatus() throws Exception {

        String expected = "{'deadlockFound': true,\n" +
                "            'deadlockInfo': [\n" +
                "        {\n" +
                "            'threadName': 'Thread-17',\n" +
                "                'threadState': 'BLOCKED',\n" +
                "                'lockName': 'Thread-18',\n" +
                "                'lockOwnerName': 'Thread-18',\n" +
                "                'native': false,\n" +
                "                'suspended': false\n" +
                "        },\n" +
                "        {\n" +
                "            'threadName': 'Thread-18',\n" +
                "                'threadState': 'BLOCKED',\n" +
                "                'lockName': 'Thread-17',\n" +
                "                'lockOwnerName': 'Thread-17',\n" +
                "                'native': false,\n" +
                "                'suspended': false\n" +
                "        }\n" +
                "  ]\n" +
                "    }";

        this.createdDeadLock();
        this.mvc.perform(post("/threads/deadlock").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    private void createdDeadLock() throws Exception {
        this.mvc.perform(get("/threads/status").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //TODO add expectation
    }
}