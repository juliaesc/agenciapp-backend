package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import org.joda.time.DateTime;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;

public interface AccountDailyRecordService {
	
	public Collection<AccountDailyRecord> getAccountDailyRecord(int username, DateTime currentDate);
	
	public Collection<AccountDailyRecord> getAccountDailyRecordByGame(int username, DateTime currentDate);

}
