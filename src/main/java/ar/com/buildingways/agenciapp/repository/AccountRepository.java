package ar.com.buildingways.agenciapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.buildingways.agenciapp.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends CrudRepository<Account, Integer> {

	Account findByAccountNumber(int accountNumber);

}