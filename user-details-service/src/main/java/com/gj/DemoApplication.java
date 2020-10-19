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

	@Value("${profile:test}")
	private String profile;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(UserRepository toDoRepository) {
		return (args) -> {
			if(StringUtils.hasText(profile) && profile.equals("test")) {
			toDoRepository.save(new User("fname", "lname", "address", "userid", "mobileNumber", "email@email.com"));
			toDoRepository.save(new User("ganesh", "jadhav", "ganesh address", "ganesh-1", "999", "ganesh@ganesh.com"));
			toDoRepository.save(new User("sidh", "nirwane", "sidh address", "sidh-1", "998", "sidh@sidh.com"));
			toDoRepository.save(new User("aarvi", "jadhav", "aarvi address", "aarvi-1", "997", "aarvi@aarvi.com"));
			logger.info("The sample data has been generated");
			}
		};
	}
}
