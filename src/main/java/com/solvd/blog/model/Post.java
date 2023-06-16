package com.solvd.blog.model;

import java.time.LocalDate;

public interface Post {

    String id();

    String title();

    String content();

    LocalDate date();

}
