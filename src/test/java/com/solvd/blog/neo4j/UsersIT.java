package com.solvd.blog.neo4j;

import com.solvd.blog.exception.ResourceAlreadyExistsException;
import com.solvd.blog.fake.FkUser;
import com.solvd.blog.model.User;
import com.solvd.blog.model.Users;
import integration.Neo4jIntegration;
import integration.PropertyOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public class UsersIT extends Neo4jIntegration {

    @Autowired
    @Qualifier("uqUsers")
    private Users users;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        new PropertyOf(
                registry,
                "spring.neo4j.uri",
                container.getBoltUrl()
        ).set();
    }

    @Test
    public void verifiesIterate() {
        Assertions.assertNotNull(this.users.iterate());
    }

    @Test
    public void verifiesUser() {
        User user = new FkUser("Name", "Email");
        User result = this.users.user(user.id());
        Assertions.assertEquals(user.name(), result.name());
        Assertions.assertEquals(user.email(), result.email());
    }

    @Test
    public void verifiesUserThrowsNoSuchRecordException() {
        Assertions.assertThrows(
                NoSuchRecordException.class,
                () -> this.users.user(5L)
        );
    }

    @Test
    public void verifiesAdd() {
        User user = new FkUser("Name", "New email");
        User result = this.users.add(user);
        Assertions.assertEquals(user.name(), result.name());
        Assertions.assertEquals(user.email(), result.email());
    }

    @Test
    public void verifiesAddThrowsResourceAlreadyExistsException() {
        Assertions.assertThrows(
                ResourceAlreadyExistsException.class,
                () -> this.users.add(new FkUser("Name", "Email"))
        );
    }

    @Test
    public void verifiesUpdate() {
        User expected = new FkUser("Updated name", "Email");
        User result = this.users.update(expected);
        Assertions.assertEquals(expected.name(), result.name());
        Assertions.assertEquals(expected.email(), result.email());
    }

    @Test
    public void verifiesIsExists() {
        Assertions.assertTrue(
                this.users.exists("Email")
        );
    }

}
