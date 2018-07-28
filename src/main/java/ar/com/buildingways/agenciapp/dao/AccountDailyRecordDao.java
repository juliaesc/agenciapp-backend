package ar.com.buildingways.agenciapp.dao;

import java.util.Collection;

import ar.com.buildingways.agenciapp.model.User;

public interface AccountDailyRecordDao {
	
	Collection<Object[]> loadAccountDailyRecordsFromDB();
	
	void insertAccountDailyRecords(Collection<Object[]> accountDailyRecordsFromDB);
	
	void deletePastAccountDailyRecords();
	
	double calculateAccountDailySettlement(User user);

}
