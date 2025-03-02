package com.akash.emailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.akash.controller")
public class EmailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailerApplication.class, args);
		System.out.println("Hello");
	}

}
