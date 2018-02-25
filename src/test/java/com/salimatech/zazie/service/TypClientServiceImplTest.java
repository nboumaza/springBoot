package com.salimatech.zazie.service;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


/*
For testing such a REST client built with RestTemplateBuilder,
you may use a SpringRunner-executed test class annotated with @RestClientTest.
This annotation disables full auto-configuration and only applies configuration
relevant to REST client tests, i.e. Jackson or JSON auto-configuration and @JsonComponent beans,
but not regular @Component beans.

@RestClientTest ensures that Jackson and GSON support is auto-configured,
and also adds pre-configured RestTemplateBuilder and MockRestServiceServer instances to the context.
The bean under test is specified with value or components attribute of the
@RestClientTest annotation
*/

@RunWith(SpringRunner.class)
//@RestClientTest(TypClientServiceImpl.class)
public class TypClientServiceImplTest {

    //@Autowired
    //private TypClientServiceImpl client;

    //@Autowired
    //private MockRestServiceServer server;

    //@Autowired
    //private ObjectMapper objectMapper;

    @Before
    public void setUp()  {

    }

    @After
    public void tearDown()  {
    }

    @Test
    public void fetchAllPosts() {
    }

    @Test
    public void fetchPostsByUserId() {
    }

    @Test
    public void findPostById() {
    }
}