package com.sm.app.transmanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sm.app.transmanage.sequencegen.business.SchemaSetUpManager;


@SpringBootApplication
public class ApplicationBootstrap implements CommandLineRunner{

	@Autowired
	SchemaSetUpManager schemaSetUpManager;
	
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApplicationBootstrap.class);
		app.setWebEnvironment(true);
		app.run(args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		schemaSetUpManager.initiateSetup();		
	}

}
