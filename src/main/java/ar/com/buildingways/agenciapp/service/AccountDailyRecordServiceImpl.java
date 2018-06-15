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
import ar.com.buildingways.agenciapp.utils.SQLQueries;

@Service("accountActivityService")
public class AccountDailyRecordServiceImpl implements AccountDailyRecordService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountDailyRecordRepository accountDailyRecordRepository;
	
	@Override
	public Collection<AccountDailyRecord> loadAccountDailyRecords(DateTime currentDate) {
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(SQLQueries.LOAD_ACCOUNT_DAILY_RECORDS);
		String param = "fecha_actual";
		storedProcedure.registerStoredProcedureParameter(param, Date.class, ParameterMode.IN);
		Date currentSQLDate = new Date(currentDate.toDateTime().getMillis());
		storedProcedure.setParameter(param, currentSQLDate);
		@SuppressWarnings("unchecked")
		Collection<Object[]> storedProcedureResults = storedProcedure.getResultList();
		Iterator<Object[]> it = storedProcedureResults.iterator();
		while (it.hasNext()) {
			Object[] item = (Object[])it.next();
			AccountDailyRecord ac = new AccountDailyRecord();
			ac.setAccount(accountRepository.findByAccountNumber((Integer) item[1]));
			ac.setGame((String) item[2]);
			ac.setDrawNumber((Integer) item[3]);
			ac.setDueDate(new DateTime((Timestamp) item[4]));
			ac.setDebt(((BigDecimal) item[5]).doubleValue());
			ac.setCredit(((BigDecimal) item[6]).doubleValue());
			ac.setInterest(((BigDecimal) item[7]).doubleValue());
			ac.setState((String) item[8]);
			ac.setCurrency((String) item[9]);
			ac.setType((String) item[10]);
			accountDailyRecords.add(ac);
		}
		accountDailyRecordRepository.saveAll(accountDailyRecords);		
		return accountDailyRecords;
	}
	
	@Override
	public Collection<AccountDailyRecord> getGlobalAccountDailyRecord(int username, DateTime currentDate) {
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		return accountDailyRecords;
	}
	
	@Override
	public Collection<AccountDailyRecord> getItemizedAccountDailyRecord(int username, DateTime currentDate) {
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		return accountDailyRecords;
	}

}
