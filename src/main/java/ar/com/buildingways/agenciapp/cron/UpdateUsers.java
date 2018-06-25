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
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	/** Actualiza diariamente la BD de usuarios recuperando las agencias activas y sus datos
	 * por medio de una vista que apunta a varias BD de Lotería.
	 * Ejecución: de lunes a sábado a las 01:00:00 a.m.
	 */
	@Scheduled(cron = "0 1 * * 1-6")
	public void updateUsers() {
	    logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	    userService.updateUsers();	    
	}

}
