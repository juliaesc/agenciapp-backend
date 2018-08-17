package ar.com.buildingways.agenciapp.service;

import ar.com.buildingways.agenciapp.model.Account;
import ar.com.buildingways.agenciapp.model.AccountDailyRecord;
import ar.com.buildingways.agenciapp.repository.AccountDailyRecordRepository;
import ar.com.buildingways.agenciapp.repository.AccountRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.Iterator;

@Service("accountDailyRecordService")
public class AccountDailyRecordServiceImpl implements AccountDailyRecordService {

    @Qualifier("accountDailyRecordRepository")
    @Autowired
    private AccountDailyRecordRepository accountDailyRecordRepository;

    @Qualifier("accountRepository")
    @Autowired
    private AccountRepository accountRepository;

	/** Recupera diariamente los movimientos de cuenta de los agencieros.
	 *	Actualiza la tabla AccountDailyRecords.
	 */
	@Override
	public void updateAccountDailyRecords() {
		accountDailyRecordRepository.deleteAll();
		// Se fija esta fecha para pruebas por los datos disponibles en la BD CCorrientes en Desarrollo.
		// En Producción, se va a pasar la fecha actual.
		DateTime currentDate = new DateTime(2015, 12, 17, 0, 0, 0);
		Date currentSQLDate = new Date(currentDate.toDateTime().getMillis());
		Collection<Object[]> recordsFromDB = accountDailyRecordRepository.loadAccountDailyRecordsFromDB(currentSQLDate);
		Iterator<Object[]> it = recordsFromDB.iterator();
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		while (it.hasNext()) {
			Object[] item = (Object[])it.next();
			AccountDailyRecord ac = new AccountDailyRecord(accountRepository.findByAccountNumber((Integer) item[1]),
					(String) item[2],(Integer) item[3],new DateTime((Timestamp) item[4]),((BigDecimal) item[5]).doubleValue(),
					((BigDecimal) item[6]).doubleValue(),((BigDecimal) item[7]).doubleValue(),
					(String) item[8],(String) item[9],(String) item[10], new DateTime());
			accountDailyRecords.add(ac);
		}
		accountDailyRecordRepository.saveAll(accountDailyRecords);
	}
	
//	/** Recupera el monto diario a liquidarse de la cuenta del agenciero.
//	 *  Consulta la tabla AccountDailyRecords.
//	 */
//	@Override
//	public double getAccountDailySettlement(User user) {
//		return accountDailyRecordDaoImpl.calculateAccountDailySettlement(user);
//	}
//
//	@Override
//	public double calculateAccountDailySettlement(User user) {
//		Query query = entityManager.createNativeQuery(SQLQueries.SELECT_ACCOUNT_SETTLEMENT);
//		query.setParameter(1, user.getId());
//		double dailySettlement = ((BigDecimal) query.getSingleResult()).doubleValue();
//		return dailySettlement;
//	}

}
