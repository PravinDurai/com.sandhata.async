package com.sandhata.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.sandhata.async.constant.AsyncConstants;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = { AsyncConstants.BASE_PACKAGE })
@OpenAPIDefinition
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
