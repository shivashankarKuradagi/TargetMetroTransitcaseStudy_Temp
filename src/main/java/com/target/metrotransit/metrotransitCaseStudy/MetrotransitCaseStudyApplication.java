package com.target.metrotransit.metrotransitCaseStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.target.metrotransit.metrotransitCaseStudy.*")
public class MetrotransitCaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetrotransitCaseStudyApplication.class, args);
	}
}
