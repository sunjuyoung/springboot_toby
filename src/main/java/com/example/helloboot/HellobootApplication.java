package com.example.helloboot;

import org.springframework.boot.SpringApplication;

@MySpringBootApplication
public class HellobootApplication {
	public static void main(String[] args) {
		//스프링컨테이너 사용
		SpringApplication.run(HellobootApplication.class,args);
	}

}
