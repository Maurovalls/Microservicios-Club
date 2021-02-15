package com.formacionbdi.microservicios.app.actividades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.formacionbdi.microservicios.generic.actividades.models.entity"})
public class MicroserviciosActividadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosActividadesApplication.class, args);
	}

}
