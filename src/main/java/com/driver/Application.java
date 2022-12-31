package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	//You are the class monitor and you want to maintain the structure of the classroom
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


	}
}
