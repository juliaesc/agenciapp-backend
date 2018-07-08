package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.dao.AccountDailyRecordDaoImpl;
import ar.com.buildingways.agenciapp.model.AccountDailyRecord;
import ar.com.buildingways.agenciapp.model.User;

@Service("accountActivityService")
public class AccountDailyRecordServiceImpl implements AccountDailyRecordService {

	@Autowired
	@Qualifier("accountDailyRecordDaoImpl")
	private AccountDailyRecordDaoImpl accountDailyRecordDaoImpl;
	
	/** Recupera diariamente los movimientos de cuenta de los agencieros.
	 *	Actualiza la tabla AccountDailyRecords.
	 */
	@Override
	public void updateAccountDailyRecords() {
		accountDailyRecordDaoImpl.deletePastAccountDailyRecords();
		Collection<Object[]> accountDailyRecordsFromDB = accountDailyRecordDaoImpl.loadAccountDailyRecordsFromDB();
		accountDailyRecordDaoImpl.insertAccountDailyRecords(accountDailyRecordsFromDB);	
	}
	
	/** Recupera los movimientos de cuenta desglosados por juego y evento del agenciero logueado 
	 *  y la fecha de vencimiento para su pago.
	 *  Consulta la tabla AccountDailyRecords.
	 */
	@Override
	public Collection<AccountDailyRecord> getAccountDailyRecords(User user) {
		return accountDailyRecordDaoImpl.getAccountDailyRecords(user);
	}
	
	/** Recupera el monto diario a liquidarse de la cuenta del agenciero.
	 *  Consulta la tabla AccountDailyRecords.
	 */
	@Override
	public double getAccountDailySettlement(User user) {
		return accountDailyRecordDaoImpl.calculateAccountDailySettlement(user);
	}

}
