package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;

public interface AccountDailyRecordService {
		
	public void updateAccountDailyRecords();
	
	public Collection<AccountDailyRecord> getGlobalAccountDailyRecord();
	
	public Collection<AccountDailyRecord> getItemizedAccountDailyRecord();

}
