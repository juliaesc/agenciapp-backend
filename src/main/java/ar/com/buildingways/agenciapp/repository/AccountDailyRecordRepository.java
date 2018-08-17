package ar.com.buildingways.agenciapp.repository;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;

@Repository("accountDailyRecordRepository")
public interface AccountDailyRecordRepository extends CrudRepository<AccountDailyRecord, Integer> {

    @Query(nativeQuery = true, value = "exec CC_LiquidacionPorJuegoEvento :fecha_actual")
    Collection<Object[]> loadAccountDailyRecordsFromDB(@Param("fecha_actual") Date currentDate);

}