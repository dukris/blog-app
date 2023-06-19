package com.solvd.blog.web;

import com.solvd.blog.model.Post;
import com.solvd.blog.model.Posts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final Posts posts;

    /**
     * Constructor.
     *
     * @param posts TxPosts
     */
    public PostController(@Qualifier("txPosts") final Posts posts) {
        this.posts = posts;
    }

    /**
     * Get post by id.
     *
     * @param id id
     * @return Post
     */
    @GetMapping("/{id}")
    public Post post(@PathVariable final Long id) {
        return this.posts.post(id);
    }

    /**
     * Get all posts.
     *
     * @return List of posts
     */
    @GetMapping
    public List<Post> iterate() {
        return this.posts.iterate();
    }
}
