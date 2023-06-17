package com.solvd.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
public class BlogApplication {

	public static void main(final String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
