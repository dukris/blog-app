package com.solvd.blog.model;

import java.time.LocalDate;

public interface Post {

    Long id();

    String title();

    String content();

    LocalDate date();

}
