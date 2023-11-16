package com.vms.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApplicationRun extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRun.class, args);
	}

}
