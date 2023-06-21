package com.solvd.blog.transaction;

import com.solvd.blog.model.Post;
import com.solvd.blog.model.Posts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@Component
public class TxPosts implements Posts {

    private final Posts posts;

    /**
     * Constructor.
     *
     * @param posts Posts
     */
    public TxPosts(@Qualifier("njPosts") final Posts posts) {
        this.posts = posts;
    }

    @Transactional
    @Override
    public Post add(final Post post, final Long user) {
        return this.posts.add(post, user);
    }

    @Transactional(readOnly = true)
    @Override
    public Post post(final Long id) {
        return this.posts.post(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> iterate(final Long user) {
        return this.posts.iterate(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> iterate() {
        return this.posts.iterate();
    }
}
