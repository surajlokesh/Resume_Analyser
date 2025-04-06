package com.resumeanalyser.resumeanalyser;

import org.springframework.boot.SpringApplication;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;

@SpringBootApplication(exclude = { ContextFunctionCatalogAutoConfiguration.class })
public class ResumeanalyserApplication {

	public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));
		SpringApplication.run(ResumeanalyserApplication.class, args); 
	}

}
