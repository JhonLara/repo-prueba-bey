package com.prueba.bey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase encargada de iniciar la app
 * 
 * @author Jhon Lara
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.prueba.bey")
public class BeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeyApplication.class, args);
	}

}
