package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;
import ar.com.buildingways.agenciapp.model.User;

public interface AccountDailyRecordService {
		
	public void updateAccountDailyRecords();
	
	public Collection<AccountDailyRecord> getAccountDailyRecords(User user);
	
	public double getAccountDailySettlement(User user);

}
