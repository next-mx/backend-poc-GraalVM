package com.bbva.test.graalvm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class GraalVmSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraalVmSpringBootApplication.class, args);
	}

}
