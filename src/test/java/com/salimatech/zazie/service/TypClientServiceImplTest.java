package com.salimatech.zazie.service;


import com.salimatech.zazie.model.json.Post;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * For testing such a REST client built with RestTemplateBuilder,
 * you may use a SpringRunner-executed test class annotated with @RestClientTest.
 * This annotation disables full auto-configuration and only applies configuration
 * relevant to REST client tests, i.e. Jackson or JSON auto-configuration and @JsonComponent beans,
 * but not regular @Component beans.
 *
 * @RestClientTest ensures that Jackson and GSON support is auto-configured,
 * and also adds pre-configured RestTemplateBuilder and MockRestServiceServer instances to the context.
 * The bean under test is specified with value or components attribute of the
 * @RestClientTest annotation
 **/

@RunWith(SpringRunner.class)
@RestClientTest(TypClientServiceImpl.class)
public class TypClientServiceImplTest {

    @Autowired
    private TypClientService client;
    @Autowired
    private MockRestServiceServer server;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void fetchAllPosts() throws ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity<List<Post>>> future = client.fetchAllPosts();

        ResponseEntity<List<Post>> posts = future.get();
        assertNotNull(posts);

    }

    @Test
    public void fetchPostsByUserId() throws ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity<List<Post>>> future = client.fetchPostsByUserId(1);

        ResponseEntity<List<Post>> posts = future.get();
        assertNotNull(posts);

    }

    @Test
    public void fetchPostsByNonExistingUserId() throws ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity<List<Post>>> future = client.fetchPostsByUserId(999999);

        ResponseEntity<List<Post>> posts = future.get();
        assertTrue(posts.getStatusCode() == HttpStatus.OK);
        assertNotNull(posts);

    }

    @Test
    public void findPostById() throws ExecutionException, InterruptedException {
        CompletableFuture<Post> future = client.findPostById(1);

        Post post = future.get();
        assertNotNull(post);
    }

    @Test(expected = ExecutionException.class)
    public void findPostByBogusPostId() throws ExecutionException, InterruptedException {
        CompletableFuture<Post> future = client.findPostById(99999);
        Post post = future.get();
    }

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}