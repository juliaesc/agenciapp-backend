package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;
import ar.com.buildingways.agenciapp.model.User;

public interface AccountDailyRecordService {
		
	public void updateAccountDailyRecords();
	
	public Collection<AccountDailyRecord> getGlobalAccountDailyRecord();
	
	public Collection<AccountDailyRecord> getItemizedAccountDailyRecords(User user);

}
