package com.pizzaiolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients("com.pizzaiolo.application.proxies")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
