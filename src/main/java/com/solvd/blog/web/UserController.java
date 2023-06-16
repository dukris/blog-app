package com.solvd.blog.web;

import com.solvd.blog.group.OnCreate;
import com.solvd.blog.group.OnUpdate;
import com.solvd.blog.model.User;
import com.solvd.blog.model.Users;
import com.solvd.blog.request.RqUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public final class UserController {

    private final Users users;

    @GetMapping
    public List<User> iterate() {
        return this.users.iterate();
    }

    @GetMapping("/{id}")
    public User user(@PathVariable final Long id) {
        return this.users.user(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody
                       @Validated(OnCreate.class) final RqUser user) {
        return this.users.add(user);
    }

    @PutMapping
    public User update(@RequestBody
                       @Validated(OnUpdate.class) final RqUser user) {
        return this.users.update(user);
    }

}
