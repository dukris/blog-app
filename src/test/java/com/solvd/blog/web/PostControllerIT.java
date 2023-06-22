package com.solvd.blog.web;

import integration.Neo4jIntegration;
import integration.PropertyOf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SuppressWarnings({
        "JTCOP.RuleAllTestsHaveProductionClass",
        "JTCOP.RuleAssertionMessage"
})
public class PostControllerIT extends Neo4jIntegration {

    private static final String URL = "/api/v1/posts";

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
    public void verifiesPost() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void verifiesPostThrowsNoSuchRecordException() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL + "/5"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
