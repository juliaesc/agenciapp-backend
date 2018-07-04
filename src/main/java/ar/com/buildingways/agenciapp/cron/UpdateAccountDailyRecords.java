package ar.com.buildingways.agenciapp.cron;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ar.com.buildingways.agenciapp.service.AccountDailyRecordService;

@Component
public class UpdateAccountDailyRecords {
	
	@Autowired
	AccountDailyRecordService accountDailyRecordService;
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateUsers.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	/** Actualiza diariamente los movimientos de cuenta de los agencieros.
	 *	Actualiza la tabla AccountDailyRecords.
	 * Ejecución: de lunes a sábado a las 01:00:00 a.m.
	 * Para pruebas: cada 5 minutos.
	 */
	//@Scheduled(cron = "0 0 01 * * MON-FRI")
	//@Scheduled(cron = "0 */3 * * * *")
	public void updateAccountDailyRecords() {
	    logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	    accountDailyRecordService.updateAccountDailyRecords();	    
	}
}
