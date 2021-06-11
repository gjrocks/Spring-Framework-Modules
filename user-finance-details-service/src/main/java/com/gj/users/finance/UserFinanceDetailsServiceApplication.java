package com.gj.users.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.gj.users.finance.config")
public class UserFinanceDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserFinanceDetailsServiceApplication.class, args);
	}

}
