package com.formacionbdi.microservicios.app.cuotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.formacionbdi.microservicios.generic.cuotas.models.entity"})
public class MicroserviciosCuotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCuotasApplication.class, args);
	}

}
