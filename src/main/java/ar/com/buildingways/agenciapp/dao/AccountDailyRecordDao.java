package ar.com.buildingways.agenciapp.dao;

import java.util.Collection;

public interface AccountDailyRecordDao {
	
	Collection<Object[]> loadAccountDailyRecordsFromDB();
	
	void insertAccountDailyRecords(Collection<Object[]> accountDailyRecordsFromDB);
	
	void deletePastAccountDailyRecords();

}
