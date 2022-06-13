package com.sofkaU.backEndreactivechallenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Don Raul's Hardware Store Reactive API", version = "1.0", description = "API Documentation v1.0"))
public class BackEndReactiveChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndReactiveChallengeApplication.class, args);
	}

}
