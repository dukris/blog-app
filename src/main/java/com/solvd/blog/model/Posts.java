package com.solvd.blog.model;

import java.util.List;

/**
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public interface Posts {

    Post add(Post post, Long user);

    Post post(Long id);

    List<Post> iterate(Long user);

    List<Post> iterate();
}
