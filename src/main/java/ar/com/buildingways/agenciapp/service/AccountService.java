package ar.com.buildingways.agenciapp.service;

import ar.com.buildingways.agenciapp.model.Account;

public interface AccountService {
	
	public Account findByAccountNumber(int accountNumber);
	
}
