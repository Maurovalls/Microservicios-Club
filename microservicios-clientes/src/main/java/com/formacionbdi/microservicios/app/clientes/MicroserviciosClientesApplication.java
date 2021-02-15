package com.formacionbdi.microservicios.app.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.formacionbdi.microservicios.generic.cuotas.models.entity",
	"com.formacionbdi.microservicios.generic.actividades.models.entity",
	"com.formacionbdi.microservicios.app.clientes.models.entity"})
public class MicroserviciosClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosClientesApplication.class, args);
	}

}
