package com.solvd.blog.model;

import java.time.LocalDate;

public interface Post {

    /**
     * Id.
     *
     * @return id
     */
    Long id();

    /**
     * Title.
     *
     * @return title
     */
    String title();

    /**
     * Content.
     *
     * @return content
     */
    String content();

    /**
     * Date.
     *
     * @return date
     */
    LocalDate date();

}
