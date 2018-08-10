package ar.com.buildingways.agenciapp.service;

import ar.com.buildingways.agenciapp.model.Account;
import ar.com.buildingways.agenciapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findByAccountNumber(int accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}

}