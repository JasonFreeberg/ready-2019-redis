package com.example.controllers;

import com.example.exceptions.PostNotFoundException;
import com.example.model.Post;
import com.example.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public Post getPostByID(@PathVariable String id) throws PostNotFoundException {
        log.info("get post with id {}", id);
        return postService.getPostByID(id);
    }

    @PutMapping("/update")
    public Post updatePostByID(@RequestBody Post post) throws PostNotFoundException {
        log.info("update post with id {}", post.getId());
        postService.updatePost(post);
        return post;
    }

    @DeleteMapping("/delete/{id}")
    public void deletePostByID(@PathVariable String id) throws PostNotFoundException {
        log.info("delete post with id {}", id);
        postService.deletePost(id);
    }

    @GetMapping("/top")
    public List<Post> getTopPosts() {
        return postService.getTopPosts();
    }

    @GetMapping("/top/evict")
    public void evictTopPosts() {
        log.info("Evict post-top");
    }
}

