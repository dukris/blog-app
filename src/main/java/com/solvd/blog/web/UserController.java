package com.solvd.blog.web;

import com.solvd.blog.group.OnCreate;
import com.solvd.blog.group.OnUpdate;
import com.solvd.blog.model.Post;
import com.solvd.blog.model.Posts;
import com.solvd.blog.model.User;
import com.solvd.blog.model.Users;
import com.solvd.blog.request.RqPost;
import com.solvd.blog.request.RqUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public final class UserController {

    private final Users users;
    private final Posts posts;

    /**
     * Constructor.
     *
     * @param users TxUsers
     * @param posts TxPosts
     */
    public UserController(
            @Qualifier("uqUsers") final Users users,
            @Qualifier("txPosts") final Posts posts
    ) {
        this.users = users;
        this.posts = posts;
    }

    /**
     * Get all users.
     *
     * @return List of users
     */
    @GetMapping
    public List<User> iterate() {
        return this.users.iterate();
    }

    /**
     * Get user by id.
     *
     * @param id is
     * @return User
     */
    @GetMapping("/{id}")
    public User user(@PathVariable final Long id) {
        return this.users.user(id);
    }

    /**
     * Create new user.
     *
     * @param user RqUser
     * @return User
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody
                       @Validated(OnCreate.class) final RqUser user) {
        return this.users.add(user);
    }

    /**
     * Update user.
     *
     * @param user RqUser
     * @return User
     */
    @PutMapping("/{id}")
    public User update(@RequestBody
                       @Validated(OnUpdate.class) final RqUser user) {
        return this.users.update(user);
    }

    /**
     * Add new post.
     *
     * @param id id
     * @param request RqPost
     * @return Post
     */
    @PostMapping("/{id}/posts")
    public Post addPost(
            @PathVariable final Long id,
            @RequestBody final RqPost request
    ) {
        return this.posts.add(request, id);
    }

    /**
     * Get posts be user's id.
     *
     * @param id id
     * @return List of posts
     */
    @GetMapping("/{id}/posts")
    public List<Post> posts(
            @PathVariable final Long id
    ) {
        return this.posts.iterate(id);
    }

}
