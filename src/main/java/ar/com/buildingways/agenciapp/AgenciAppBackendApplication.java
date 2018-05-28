package ar.com.buildingways.agenciapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AgenciAppBackendApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AgenciAppBackendApplication.class, args);
	}
}
