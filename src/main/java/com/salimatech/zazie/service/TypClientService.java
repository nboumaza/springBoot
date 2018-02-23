package com.salimatech.zazie.service;

import com.salimatech.zazie.model.json.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TypClientService {

    /**
     * fetch all user posts
     * @return list of all  posts
     */
    CompletableFuture<ResponseEntity<List<Post>>> fetchAllPosts();

    /**
     * find post by id
     * @param id id of the post to fetch
     * @return found post - null otherwise
     */
    CompletableFuture<Post> findPostById(String id);


    /**
     * fetch a user posts
     * @param userId  userId to fetch posts for
     * @return list of all posts associated with a given userId
     */
    CompletableFuture<ResponseEntity<List<Post>>> fetchPostsByUserId(long userId);


}
