package ar.com.buildingways.iplycbackend.service;

import java.util.Collection;

import org.joda.time.DateTime;

import ar.com.buildingways.iplycbackend.model.AccountActivity;

public interface AccountActivityService {
	
	public Collection<AccountActivity> getAccountActivity(int username, DateTime currentDate);

}
