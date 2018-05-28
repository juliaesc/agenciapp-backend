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

import ar.com.buildingways.agenciapp.model.AccountActivity;

@Service("accountActivityService")
public class AccountActivityServiceImpl implements AccountActivityService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Collection<AccountActivity> getAccountActivity(int username, DateTime currentDate) {
		Collection<AccountActivity> accountActivities = new ArrayList<AccountActivity>();
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
			AccountActivity ac = new AccountActivity();
			BigDecimal debt = (BigDecimal) item[3];
			ac.setDebt(debt.doubleValue());
			BigDecimal credit = (BigDecimal)item[4];
			ac.setCredit(credit.doubleValue());
			BigDecimal interest = (BigDecimal)item[5];
			ac.setInterest(interest.doubleValue());
			Timestamp dueDate = (Timestamp) item[0];
			ac.setDueDate(new DateTime(dueDate.getTime()));
			accountActivities.add(ac);
		}
		return accountActivities;
	}

}
