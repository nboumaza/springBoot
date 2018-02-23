package com.salimatech.zazie.controller;

import com.salimatech.zazie.exceptions.EntityNotFoundException;
import com.salimatech.zazie.model.json.Post;
import com.salimatech.zazie.service.TypClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *   Posts REST controller which itself is a client to a subset of the following
 *   endpoint: https://jsonplaceholder.typicode.com
 *
 *   Please refer to the following swagger api for more detail on the available
 *   operations and corresponding response status
 *   https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/
 */
@RestController
public class TypicodeController {

    @Autowired
    private TypClientService service;

    private static Logger logger = LogManager.getLogger(TypClientService.class);


    /**
     * @return list of all posts
     */
    @GetMapping("/posts")
    public List<Post> fetchAllPosts() {


        CompletableFuture<ResponseEntity<List<Post>>> future = service.fetchAllPosts();

        ResponseEntity<List<Post>> posts = null;

        try {
            posts =  future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return posts.getBody();
    }


    /**
     * @param userId user id to fetch the posts for
     * @return list of all posts
     */
    @GetMapping("/posts?userId={userId}")
    public List<Post> fetchPostsByUserId(@RequestParam("userId") long userId) {

        CompletableFuture<ResponseEntity<List<Post>>> future = service.fetchPostsByUserId(userId);

        ResponseEntity<List<Post>> posts = null;

        try {
            posts =  future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return posts.getBody();

    }


    /**
     * @param id id of the post to fetch
     * @return post result
     * @throws EntityNotFoundException if no post was found
     */
    @GetMapping("/posts/{id}")
    public Post findPostById(@PathVariable("id") String id)  throws EntityNotFoundException {

        CompletableFuture<Post> future = service.findPostById(id);


        Post post = null;

        try {
            post =  future.get();
            /* if (!post.isPresent()) {
            String message = "could not find a post account with id = "+id;
            logger.debug(message);
            throw new EntityNotFoundException(message);
        }
        */
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return post;

    }


}
