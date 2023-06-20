package com.solvd.blog.neo4j;

import com.solvd.blog.fake.FkPost;
import com.solvd.blog.model.Post;
import com.solvd.blog.model.Posts;
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
public class PostsIT extends Neo4jIntegration {

    @Autowired
    @Qualifier("txPosts")
    private Posts posts;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        new PropertyOf(
                registry,
                "spring.neo4j.uri",
                container.getBoltUrl()
        ).set();
    }

    @Test
    public void verifiesIterate(){
        this.posts.add(new FkPost(), 0L);
        Assertions.assertNotNull(this.posts.iterate());
    }

    @Test
    public void verifiesIterateByUserId(){
        this.posts.add(new FkPost(), 0L);
        Assertions.assertNotNull(this.posts.iterate(0L));
    }

    @Test
    public void verifiesPost(){
        Post post = this.posts.add(new FkPost(), 0L);
        Post result = this.posts.post(post.id());
        Assertions.assertEquals(post.title(), result.title());
        Assertions.assertEquals(post.content(), result.content());
    }

    @Test
    public void verifiesPostThrowsNoSuchRecordException() {
        Assertions.assertThrows(
                NoSuchRecordException.class,
                () -> this.posts.post(5L)
        );
    }

    @Test
    public void verifiesAdd(){
        Post post = new FkPost();
        Post result = this.posts.add(post, 0L);
        Assertions.assertEquals(post.title(), result.title());
        Assertions.assertEquals(post.content(), result.content());
    }

}
