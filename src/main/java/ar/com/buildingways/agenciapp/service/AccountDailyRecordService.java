package ar.com.buildingways.agenciapp.service;

import ar.com.buildingways.agenciapp.model.User;

public interface AccountDailyRecordService {
		
	public void updateAccountDailyRecords();
	
	public double getAccountDailySettlement(User user);

}
