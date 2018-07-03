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
	
	/** Actualiza diariamente la BD de registros de movimientos de cuenta corriente
	 * recuperando los movimientos por medio de un SP que apunta a la BD CCorrientes.
	 */
	@Override
	public void updateAccountDailyRecords() {
		accountDailyRecordDaoImpl.deletePastAccountDailyRecords();
		Collection<Object[]> accountDailyRecordsFromDB = accountDailyRecordDaoImpl.loadAccountDailyRecordsFromDB();
		accountDailyRecordDaoImpl.insertAccountDailyRecords(accountDailyRecordsFromDB);	
	}
	
	/** Recupera los movimientos de cuenta del agenciero logueado desglosado por juego y evento 
	 *	y la fecha de vencimiento para su pago.
	 *	Consulta la tabla AccountDailyRecords.
	 */
	@Override
	public Collection<AccountDailyRecord> getAccountDailyRecords(User user) {
		return accountDailyRecordDaoImpl.getAccountDailyRecords(user);
	}
	
	/** Recupera el cálculo del monto a liquidarse de la cuenta del agenciero.
	 *	Consulta la tabla AccountDailyRecords.
	 */
	public double getAccountDailySettlement(User user) {
		return accountDailyRecordDaoImpl.calculateAccountDailySettlement(user);
	}

}
