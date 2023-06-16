package com.solvd.blog.model;

import java.util.List;

public interface User {

    String id();

    String name();

    String email();

    List<Post> posts();

}
