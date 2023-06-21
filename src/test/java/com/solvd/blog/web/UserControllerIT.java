package com.solvd.blog.web;

import integration.Neo4jIntegration;
import integration.PropertyOf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
public class UserControllerIT extends Neo4jIntegration {

    private static final String URL = "/api/v1/users";

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        new PropertyOf(
                registry,
                "spring.neo4j.uri",
                container.getBoltUrl()
        ).set();
    }

    @Test
    public void verifiesIterate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void verifiesUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL + "/0"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void verifiesUserThrowsNoSuchRecordException() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL + "/5"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void verifiesCreate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name" : "Name",
                          "email" : "Test email"
                        }
                        """
                )
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void verifiesCreateThrowsResourceAlreadyExistsException()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name" : "Name",
                          "email" : "Email"
                        }
                        """
                )
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void verifiesCreateThrowsMethodArgumentNotValidException()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "id" : 0,
                          "name" : "Name",
                          "email" : "Email"
                        }
                        """
                )
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void verifiesUpdate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put(URL + "/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "id" : 0,
                          "name" : "Name",
                          "email" : "Email"
                        }
                        """
                )
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void verifiesUpdateThrowsMethodArgumentNotValidException()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put(URL + "/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name" : "Name",
                          "email" : "Email"
                        }
                        """
                )
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void verifiesAddPost()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(URL + "/0/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "title" : "Title",
                          "content" : "Content"
                        }
                        """
                )
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void verifiesPosts()
            throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL + "/0/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
