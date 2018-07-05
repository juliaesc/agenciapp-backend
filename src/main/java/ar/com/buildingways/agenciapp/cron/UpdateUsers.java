package ar.com.buildingways.agenciapp.cron;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ar.com.buildingways.agenciapp.service.UserService;

@Component
public class UpdateUsers {
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateUsers.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	/** Actualiza diariamente los datos de los usuarios habilitados para usar la aplicación
	 *	que se encuentran en las tablas Users, UserDetails y Accounts.
	 * Ejecución: de lunes a sábado a las 01:00:00 a.m.
	 */
	//@Scheduled(cron = "${scheduling.users.cron}")
	public void updateUsers() {
	    logger.info("Cron Task | Fecha y hora de ejecución: {}", dateTimeFormatter.format(LocalDateTime.now()));
	    userService.updateUsers();	    
	}

}
