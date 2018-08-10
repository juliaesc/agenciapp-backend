package ar.com.buildingways.agenciapp.repository;

import ar.com.buildingways.agenciapp.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("accountRepository")
public interface AccountRepository extends CrudRepository<Account, Integer> {

	Account findByAccountNumber(int accountNumber);

}