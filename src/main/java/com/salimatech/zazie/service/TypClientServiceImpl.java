package com.salimatech.zazie.service;

import com.salimatech.zazie.model.json.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 *  REST client service for the follwing endpoint
 *  https://jsonplaceholder.typicode.com/post
 */

@Service
public class TypClientServiceImpl implements TypClientService {

    @Autowired
    private RestTemplate restTemplate;


    //TODO get from config
    private String postsUrl = "https://jsonplaceholder.typicode.com/posts";


    @Async
    @Override
    public CompletableFuture<ResponseEntity<List<Post>>> fetchAllPosts() {

        ResponseEntity<List<Post>> response = restTemplate.exchange(postsUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Post>>() {
                });

        return CompletableFuture.<ResponseEntity<List<Post>>>completedFuture(response);
    }

    @Async
    @Override
    public CompletableFuture<ResponseEntity<List<Post>>> fetchPostsByUserId(long userId) {
        String url = String.format(postsUrl+"?userId=%s", userId);

        ResponseEntity<List<Post>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Post>>() {
                });

        return CompletableFuture.<ResponseEntity<List<Post>>>completedFuture(response);
    }

    @Async
    @Override
    public CompletableFuture<Post> findPostById(String id) {

     String url = String.format(postsUrl+"/%s", id);

     Post response = restTemplate.getForObject(url, Post.class);

     return completedFuture(response);

    }


}