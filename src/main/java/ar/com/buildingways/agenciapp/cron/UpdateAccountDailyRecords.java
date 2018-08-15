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
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /** Recupera diariamente los movimientos de cuenta de los agencieros.
     *	Actualiza la tabla AccountDailyRecords.
     * Ejecución: de lunes a sábado a las 01:30:00 a.m.
     */
    //@Scheduled(cron = "${scheduling.dailyrecords.cron}")
    public void updateAccountDailyRecords() {
        logger.info("Cron Task | Fecha y hora de ejecución: {}", dateTimeFormatter.format(LocalDateTime.now()));
        accountDailyRecordService.updateAccountDailyRecords();
    }
}
