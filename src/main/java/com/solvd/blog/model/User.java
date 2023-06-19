package com.solvd.blog.model;

import java.util.List;

public interface User {

    /**
     * Id.
     *
     * @return id
     */
    Long id();

    /**
     * Name.
     *
     * @return name
     */
    String name();

    /**
     * Email.
     *
     * @return email
     */
    String email();

    /**
     * Posts.
     *
     * @return list of posts
     */
    List<Post> posts();

}
