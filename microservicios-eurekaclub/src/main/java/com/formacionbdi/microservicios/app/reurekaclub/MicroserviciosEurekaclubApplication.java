package com.formacionbdi.microservicios.app.reurekaclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroserviciosEurekaclubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosEurekaclubApplication.class, args);
	}

}
