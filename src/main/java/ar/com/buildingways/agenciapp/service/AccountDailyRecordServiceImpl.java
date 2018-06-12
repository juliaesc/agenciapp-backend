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
import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;

@Service("accountActivityService")
public class AccountDailyRecordServiceImpl implements AccountDailyRecordService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Collection<AccountDailyRecord> getAccountDailyRecord(int username, DateTime currentDate) {
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("CC_ConsultarEstadoCuentaCorrientePorLegajo");
		String firstParam = "legajo";
		String secondParam = "fecha_actual";
		storedProcedure.registerStoredProcedureParameter(firstParam, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(secondParam, Date.class, ParameterMode.IN);
		storedProcedure.setParameter(firstParam, username);
		Date currentSQLDate = new Date(currentDate.toDateTime().getMillis());
		storedProcedure.setParameter(secondParam, currentSQLDate);
		@SuppressWarnings("unchecked")
		Collection<Object[]> storedProcedureResults = storedProcedure.getResultList();
		Iterator<Object[]> it = storedProcedureResults.iterator();
		while (it.hasNext()) {
			Object[] item = (Object[])it.next();
			Timestamp dueDate = (Timestamp) item[0];
			String state = (String) item[1];
			String currency = (String) item[2];
			BigDecimal debt = (BigDecimal) item[3];
			BigDecimal credit = (BigDecimal)item[4];
			BigDecimal interest = (BigDecimal)item[5];
			AccountDailyRecord ac = new AccountDailyRecord();
			ac.setCurrency(currency);
			ac.setDebt(debt.doubleValue());
			ac.setCredit(credit.doubleValue());
			ac.setInterest(interest.doubleValue());
			ac.setDueDate(new DateTime(dueDate.getTime()));
			ac.setState(state);
			accountDailyRecords.add(ac);
		}
		return accountDailyRecords;
	}
	
	@Override
	public Collection<AccountDailyRecord> getAccountDailyRecordByGame(int username, DateTime currentDate) {
		Collection<AccountDailyRecord> accountDailyRecords = new ArrayList<AccountDailyRecord>();
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("CC_ConsultarEstadoCuentaCorrientePorLegajoPorJuego");
		String firstParam = "legajo";
		String secondParam = "fecha_actual";
		storedProcedure.registerStoredProcedureParameter(firstParam, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(secondParam, Date.class, ParameterMode.IN);
		storedProcedure.setParameter(firstParam, username);
		Date currentSQLDate = new Date(currentDate.toDateTime().getMillis());
		storedProcedure.setParameter(secondParam, currentSQLDate);
		@SuppressWarnings("unchecked")
		Collection<Object[]> storedProcedureResults = storedProcedure.getResultList();
		Iterator<Object[]> it = storedProcedureResults.iterator();
		while (it.hasNext()) {
			Object[] item = (Object[])it.next();
			String game = (String) item[1];
			int drawNumber = (int) item[2];
			String currency = (String) item[3];
			String state = (String) item[4];
			Timestamp dueDate = (Timestamp) item[5];
			BigDecimal debt = (BigDecimal) item[6];
			BigDecimal interest = (BigDecimal)item[7];
			if (interest==null) {
				interest = BigDecimal.ZERO;
			}
			AccountDailyRecord ac = new AccountDailyRecord();
			ac.setCurrency(currency);
			ac.setDebt(debt.doubleValue());
			ac.setInterest(interest.doubleValue());
			ac.setDueDate(new DateTime(dueDate.getTime()));
			ac.setState(state);
			ac.setGame(game);
			ac.setDrawNumber(drawNumber);
			accountDailyRecords.add(ac);
		}
		return accountDailyRecords;
	}

}
