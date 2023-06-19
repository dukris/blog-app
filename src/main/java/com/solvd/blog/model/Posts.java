package com.solvd.blog.model;

import java.util.List;

/**
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public interface Posts {

    /**
     * Add new post.
     *
     * @param post Post
     * @param user User's id
     * @return Post
     */
    Post add(Post post, Long user);

    /**
     * Get post by id.
     *
     * @param id Id
     * @return Post
     */
    Post post(Long id);

    /**
     * Get all posts by user's id.
     *
     * @param user User's id
     * @return List of users
     */
    List<Post> iterate(Long user);

    /**
     * Get all posts.
     *
     * @return List of posts
     */
    List<Post> iterate();

}
