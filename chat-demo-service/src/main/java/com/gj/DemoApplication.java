package com.gj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import com.gj.model.User;
import com.gj.repository.UserRepository;

@SpringBootApplication
public class DemoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Value("${profile}")
	private String profile;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(UserRepository toDoRepository) {
		return (args) -> {
			if(StringUtils.hasText(profile) && profile.equals("test")) {
			toDoRepository.save(new User("Remove unused imports", true));
			toDoRepository.save(new User("Clean the code", true));
			toDoRepository.save(new User("Build the artifacts", false));
			toDoRepository.save(new User("Deploy the jar file", true));
			logger.info("The sample data has been generated");
			}
		};
	}
}
