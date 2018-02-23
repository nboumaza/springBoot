package com.salimatech.zazie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter;

    @Autowired
    Executor asyncExecutor;

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
        assertThat(objectMapper).isNotNull();
        assertThat(mappingJacksonHttpMessageConverter).isNotNull();
        assertThat(asyncExecutor).isNotNull();


    }

}
