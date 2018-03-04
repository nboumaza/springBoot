package com.salimatech.zazie.controller;

import com.salimatech.zazie.exception.EntityNotFoundException;
import com.salimatech.zazie.exception.ServiceException;
import com.salimatech.zazie.model.json.Post;
import com.salimatech.zazie.service.TypClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Posts REST controller which itself is a client to a subset of the following
 * endpoint: https://jsonplaceholder.typicode.com
 * <p>
 * Please refer to the following swagger api for more detail on the available
 * operations and corresponding response status
 * https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/
 */
@RestController
public class TypicodeController {

    private static Logger logger = LogManager.getLogger(TypClientService.class);
    private final TypClientService service;

    @Autowired
    public TypicodeController(TypClientService service) {
        this.service = service;
    }

    /**
     * @return list of all posts
     */
    @GetMapping("/posts")
    public List<Post> fetchAllPosts() throws ServiceException {


        CompletableFuture<ResponseEntity<List<Post>>> future = service.fetchAllPosts();

        ResponseEntity<List<Post>> posts;

        try {
            posts = future.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getStackTrace());
            throw new ServiceException("encountered an error while retrieving all posts");
        }
        return posts.getBody();
    }


    /**
     * @param userId user id to fetch the posts for
     * @return list of all posts
     * @throws ServiceException if an upstream exception is encountered
     */
    @GetMapping(value = "/posts", params = {"userId"})
    public List<Post> fetchPostsByUserId(@RequestParam("userId") long userId) throws ServiceException {

        CompletableFuture<ResponseEntity<List<Post>>> future = service.fetchPostsByUserId(userId);

        ResponseEntity<List<Post>> posts;

        try {
            posts = future.get();
        } catch (InterruptedException e) {

            logger.error(e.getStackTrace());
            throw new ServiceException("encountered an error while retrieving list for post userId=" + userId);
        } catch (ExecutionException e) {

            logger.error(e.getStackTrace());
            throw new ServiceException("encountered an error while retrieving the list for post userId=" + userId);
        }

        return posts.getBody();

    }


    /**
     * /**
     *
     * @param id id of the post to fetch
     * @return post result
     * @throws ServiceException        if an upstream exception is encountered
     * @throws EntityNotFoundException if not post with such id is found
     */
    @GetMapping("/posts/{id}")
    public Post findPostById(@PathVariable("id") long id) throws ServiceException, EntityNotFoundException {

        CompletableFuture<Post> future = service.findPostById(id);


        Post post;

        try {
            post = future.get();

        } catch (InterruptedException e) {
            logger.error(e.getStackTrace());
            throw new ServiceException("encountered an error while retrieving list for post id=" + id);
        } catch (ExecutionException e) {

            if (e.getCause().toString().contains(HttpStatus.NOT_FOUND.toString())) {
                logger.debug("cound not find a post with id=" + id);
                throw new EntityNotFoundException("could not find a post with id=" + id);
            }
            logger.error(e.getStackTrace());
            throw new ServiceException("encountered an error while retrieving the list for post id=" + id);
        }

        return post;

    }


}
