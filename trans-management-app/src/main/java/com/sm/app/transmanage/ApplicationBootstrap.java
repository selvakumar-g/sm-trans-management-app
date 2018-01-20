package com.sm.app.transmanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sm.app.transmanage.sequencegen.business.SchemaSetUpManager;

@SpringBootApplication
public class ApplicationBootstrap implements ApplicationRunner {

	@Autowired
	SchemaSetUpManager schemaSetUpManager;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApplicationBootstrap.class);
		app.setWebEnvironment(true);
		app.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (args.containsOption("clearschema"))
			schemaSetUpManager.clearSchema();
		schemaSetUpManager.initiateSetup();
	}

}
