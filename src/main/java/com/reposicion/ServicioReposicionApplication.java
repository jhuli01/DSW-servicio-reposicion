package com.reposicion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioReposicionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioReposicionApplication.class, args);
	}

}
