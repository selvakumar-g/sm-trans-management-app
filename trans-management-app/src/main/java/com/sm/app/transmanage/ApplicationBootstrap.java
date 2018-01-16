package com.sm.app.transmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApplicationBootstrap {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApplicationBootstrap.class);
		app.setWebEnvironment(true);
		app.run(args);
	}

}
