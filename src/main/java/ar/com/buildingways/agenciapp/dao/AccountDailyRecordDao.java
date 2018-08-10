package ar.com.buildingways.agenciapp.dao;

import ar.com.buildingways.agenciapp.model.User;

import java.util.Collection;

public interface AccountDailyRecordDao {
	
	Collection<Object[]> loadAccountDailyRecordsFromDB();
	
	void insertAccountDailyRecords(Collection<Object[]> accountDailyRecordsFromDB);
	
	void deletePastAccountDailyRecords();
	
	double calculateAccountDailySettlement(User user);

}
