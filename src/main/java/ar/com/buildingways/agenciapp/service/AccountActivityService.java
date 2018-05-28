package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import org.joda.time.DateTime;

import ar.com.buildingways.agenciapp.model.AccountActivity;

public interface AccountActivityService {
	
	public Collection<AccountActivity> getAccountActivity(int username, DateTime currentDate);

}
