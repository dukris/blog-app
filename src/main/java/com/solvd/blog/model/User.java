package com.solvd.blog.model;

import java.util.List;

public interface User {

    Long id();

    String name();

    String email();

    List<Post> posts();

}
