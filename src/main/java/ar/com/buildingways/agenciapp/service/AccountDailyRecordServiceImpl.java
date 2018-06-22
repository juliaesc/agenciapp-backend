package ar.com.buildingways.agenciapp.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;
import ar.com.buildingways.agenciapp.repository.AccountDailyRecordRepository;
import ar.com.buildingways.agenciapp.repository.AccountRepository;
import ar.com.buildingways.agenciapp.util.SQLQueries;

@Service("accountActivityService")
public class AccountDailyRecordServiceImpl implements AccountDailyRecordService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountDailyRecordRepository accountDailyRecordRepository;
	
	@Override
	public void updateAccountDailyRecords() {
		Collection<Object[]> accountDailyRecords = loadAccountDailyRecords();
		insertAccountDailyRecords(accountDailyRecords);	
	}
	
	private Collection<Object[]> loadAccountDailyRecords() {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(SQLQueries.LOAD_ACCOUNT_DAILY_RECORDS);
		String param = "fecha_actual";
		storedProcedure.registerStoredProcedureParameter(param, Date.class, ParameterMode.IN);
		// Se fija esta fecha para pruebas por los datos disponibles en la BD CCorrientes en Desarrollo.
		// En Producción, se va a pasar la fecha actual.
		DateTime currentDate = new DateTime(2015, 12, 17, 0, 0, 0);
		Date currentSQLDate = new Date(currentDate.toDateTime().getMillis());
		storedProcedure.setParameter(param, currentSQLDate);
		@SuppressWarnings("unchecked")
		Collection<Object[]> storedProcedureResults = storedProcedure.getResultList();
		return storedProcedureResults;
	}
	
	private void insertAccountDailyRecords(Collection<Object[]> storedProcedureResults) {
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		Iterator<Object[]> it = storedProcedureResults.iterator();
		while (it.hasNext()) {
			Object[] item = (Object[])it.next();
			AccountDailyRecord ac = new AccountDailyRecord(accountRepository.findByAccountNumber((Integer) item[1]),
					(String) item[2],(Integer) item[3],new DateTime((Timestamp) item[4]),((BigDecimal) item[5]).doubleValue(),
					((BigDecimal) item[6]).doubleValue(),((BigDecimal) item[7]).doubleValue(),
					(String) item[8],(String) item[9],(String) item[10]);
			accountDailyRecords.add(ac);
		}
		accountDailyRecordRepository.saveAll(accountDailyRecords);		

	}
	
	@Override
	public Collection<AccountDailyRecord> getGlobalAccountDailyRecord() {
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		// Implementación pendiente.
		return accountDailyRecords;
	}
	
	@Override
	public Collection<AccountDailyRecord> getItemizedAccountDailyRecord() {
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		// Implementación pendiente.
		return accountDailyRecords;
	}

}
