package com.bridzelabz.fundoonotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication /* (exclude = SecurityAutoConfiguration.class) */
@EnableSwagger2
public class FundooNotesApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FundooNotesApiApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(FundooNotesApiApplication.class);
//	}
}
