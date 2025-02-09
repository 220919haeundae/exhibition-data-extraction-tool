package com.kh.preparationtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.kh.preparationtools.service.DataExtractionService;

@EnableScheduling
@SpringBootApplication
public class PreparationtoolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreparationtoolsApplication.class, args);
		SpringApplication.run(DataExtractionService.class, args);
	}

}
