package com.example.demo.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.example")
public class StarterTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarterTemplateApplication.class, args);
	}

}
