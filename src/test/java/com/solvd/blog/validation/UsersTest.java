package com.solvd.blog.validation;

import com.solvd.blog.exception.ResourceAlreadyExistsException;
import com.solvd.blog.fake.FkUser;
import com.solvd.blog.model.User;
import com.solvd.blog.model.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UsersTest {

    @Mock
    private Users users;

    @InjectMocks
    private VdUsers vdUsers;

    @Test
    public void verifiesIterate() {
        Mockito.when(this.users.iterate())
                .thenReturn(List.of(new FkUser("Name", "Email")));
        List<User> result = this.vdUsers.iterate();
        Assertions.assertNotNull(result);
        Mockito.verify(this.users, Mockito.times(1))
                .iterate();
    }

    @Test
    public void verifiesUser() {
        User user = new FkUser("Name", "Email");
        Mockito.when(this.users.user(user.id()))
                .thenReturn(user);
        User result = this.vdUsers.user(user.id());
        Assertions.assertEquals(user.name(), result.name());
        Assertions.assertEquals(user.email(), result.email());
        Mockito.verify(this.users, Mockito.times(1))
                .user(user.id());
    }

    @Test
    public void verifiesAdd() {
        User user = new FkUser("Name", "New email");
        Mockito.when(this.users.add(user))
                .thenReturn(user);
        User result = this.vdUsers.add(user);
        Assertions.assertEquals(user.name(), result.name());
        Assertions.assertEquals(user.email(), result.email());
        Mockito.verify(this.users, Mockito.times(1))
                .add(user);
    }

    @Test
    public void verifiesAddThrowsResourceAlreadyExistsException() {
        User user = new FkUser("Name", "Email");
        Mockito.when(this.users.exists(user.email()))
                .thenReturn(true);
        Assertions.assertThrows(
                ResourceAlreadyExistsException.class,
                () -> this.vdUsers.add(user)
        );
        Mockito.verify(this.users, Mockito.times(1))
                .exists(user.email());
    }

    @Test
    public void verifiesUpdate() {
        User expected = new FkUser("Updated name", "Email");
        Mockito.when(this.users.update(expected))
                .thenReturn(expected);
        User result = this.vdUsers.update(expected);
        Assertions.assertEquals(expected.name(), result.name());
        Assertions.assertEquals(expected.email(), result.email());
        Mockito.verify(this.users, Mockito.times(1))
                .update(expected);
    }

    @Test
    public void verifiesIsExists() {
        Mockito.when(this.users.exists("Email"))
                .thenReturn(true);
        Assertions.assertTrue(
                this.users.exists("Email")
        );
        Mockito.verify(this.users, Mockito.times(1))
                .exists("Email");
    }

}
