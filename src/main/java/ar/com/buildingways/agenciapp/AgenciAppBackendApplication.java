package ar.com.buildingways.agenciapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackageClasses = {
		AgenciAppBackendApplication.class,
})
public class AgenciAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgenciAppBackendApplication.class, args);
	}

}
