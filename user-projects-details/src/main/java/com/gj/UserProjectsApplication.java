package com.gj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gj.model.UserProject;
import com.gj.repository.UserProjectsRepository;

@SpringBootApplication
public class UserProjectsApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProjectsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserProjectsApplication.class, args);
	}
	    
	
	@Bean
	public CommandLineRunner setup(UserProjectsRepository userProjectsRepository) {
		return (args) -> {
			userProjectsRepository.save(new UserProject("Remove unused imports", true));
			userProjectsRepository.save(new UserProject("Clean the code", true));
			userProjectsRepository.save(new UserProject("Build the artifacts", false));
			userProjectsRepository.save(new UserProject("Deploy the jar file", true));
			logger.info("The sample data has been generated");
		};
	}
}
