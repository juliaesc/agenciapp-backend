package ar.com.buildingways.agenciapp.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.dao.AccountDailyRecordDaoImpl;
import ar.com.buildingways.agenciapp.model.AccountDailyRecord;

@Service("accountActivityService")
public class AccountDailyRecordServiceImpl implements AccountDailyRecordService {

	@Autowired
	@Qualifier("accountDailyRecordDaoImpl")
	private AccountDailyRecordDaoImpl accountDailyRecordDaoImpl;
	
	/** Actualiza diariamente la BD de registros de movimientos de cuenta corriente
	 * recuperando los movimientos por medio de un SP que apunta a la BD CCorrientes.
	 */
	@Override
	public void updateAccountDailyRecords() {
		accountDailyRecordDaoImpl.deletePastAccountDailyRecords();
		Collection<Object[]> accountDailyRecordsFromDB = accountDailyRecordDaoImpl.loadAccountDailyRecordsFromDB();
		accountDailyRecordDaoImpl.insertAccountDailyRecords(accountDailyRecordsFromDB);	
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
