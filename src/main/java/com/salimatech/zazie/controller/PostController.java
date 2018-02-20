package com.salimatech.zazie.controller;

import com.salimatech.zazie.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class PostController {
    @GetMapping("/posts")
    public List<Post> retrieveAllUsers() {
       return new ArrayList<Post>();
    }

    @GetMapping("/posts/{userId}")
    public Post retrievePostByUserId(@PathVariable String userId) {
        return new Post();
    }



}
